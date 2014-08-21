package com.github.zxh.classpy.common;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 *
 * @author zxh
 */
public class FileComponentHelper {
    
    // todo
    public static void inferSubComponentName(FileComponent fcObj)
            throws ReflectiveOperationException {
        
        for (Class<?> fcClass = fcObj.getClass(); fcClass != null; fcClass = fcClass.getSuperclass()) {
            for (Field field : fcClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (isFileComponentType(field)) {
                    // field is FileComponent
                    FileComponent fcFieldVal = (FileComponent) field.get(fcObj);
                    if (fcFieldVal != null) {
                        fcFieldVal.setName(field.getName());
                        inferSubComponentName(fcFieldVal);
                    }
                } else if (isFileComponentArrayType(field)) {
                    // field is FileComponent[]
                    Object arrFieldVal = field.get(fcObj);
                    if (arrFieldVal != null) {
                        int length = Array.getLength(arrFieldVal);
                        for (int i = 0; i < length; i++) {
                            FileComponent arrItem = (FileComponent) Array.get(arrFieldVal, i);
                            if (arrItem != null) {
                                inferSubComponentName(arrItem);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static boolean isFileComponentType(Field field) {
        return FileComponent.class.isAssignableFrom(field.getType());
    }
    
    private static boolean isFileComponentArrayType(Field field) {
        if (!field.getType().isArray()) {
            return false;
        }
        
        return FileComponent.class.isAssignableFrom(
                field.getType().getComponentType());
    }
    
}
