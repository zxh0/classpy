package com.github.zxh.classpy.classfile;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 *
 * @author zxh
 */
public class ClassParser {
    
    public static ClassFile parse(byte[] bytes) {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        ClassReader reader = new ClassReader(buf);
        ClassFile cf = new ClassFile();
        cf.read(reader);
        cf.setBytes(bytes);
        
        try {
            setNameForClassComponents(cf);
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
        
        return cf;
    }

    // todo
    private static void setNameForClassComponents(Object obj) throws ReflectiveOperationException {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                if (isClassComponentType(field)) {
                    // field is ClassComponent
                    ClassComponent ccFieldVal = (ClassComponent) field.get(obj);
                    if (ccFieldVal != null) {
                        ccFieldVal.setName(field.getName());
                        setNameForClassComponents(ccFieldVal);
                    }
                } else if (isClassComponentArrayType(field)) {
                    // field is ClassComponent[]
                    Object arrFieldVal = field.get(obj);
                    if (arrFieldVal != null) {
                        int length = Array.getLength(arrFieldVal);
                        for (int i = 0; i < length; i++) {
                            ClassComponent arrItem = (ClassComponent) Array.get(arrFieldVal, i);
                            if (arrItem != null) {
                                setNameForClassComponents(arrItem);
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
