package com.github.zxh.classpy.classfile;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 *
 * @author zxh
 */
public class ClassParser {
    
    public static ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        cf.setBytes(bytes);
        
        try {
            setNameForClassComponentFields(cf);
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
        
        return cf;
    }

    // todo
    private static void setNameForClassComponentFields(ClassComponent ccObj)
            throws ReflectiveOperationException {
        
        for (Class<?> ccClass = ccObj.getClass(); ccClass != null; ccClass = ccClass.getSuperclass()) {
            for (Field field : ccClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (isClassComponentType(field)) {
                    // field is ClassComponent
                    ClassComponent ccFieldVal = (ClassComponent) field.get(ccObj);
                    if (ccFieldVal != null) {
                        ccFieldVal.setName(field.getName());
                        setNameForClassComponentFields(ccFieldVal);
                    }
                } else if (isClassComponentArrayType(field)) {
                    // field is ClassComponent[]
                    Object arrFieldVal = field.get(ccObj);
                    if (arrFieldVal != null) {
                        int length = Array.getLength(arrFieldVal);
                        for (int i = 0; i < length; i++) {
                            ClassComponent arrItem = (ClassComponent) Array.get(arrFieldVal, i);
                            if (arrItem != null) {
                                setNameForClassComponentFields(arrItem);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static boolean isClassComponentType(Field field) {
        return ClassComponent.class.isAssignableFrom(field.getType());
    }
    
    private static boolean isClassComponentArrayType(Field field) {
        return field.getType().isArray()
                && ClassComponent.class.isAssignableFrom(field.getType().getComponentType());
    }
    
}
