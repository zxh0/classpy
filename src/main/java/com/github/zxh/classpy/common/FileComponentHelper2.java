package com.github.zxh.classpy.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxh
 */
public class FileComponentHelper2 {
    
    public static List<FileComponent> getSubComponents(FileComponent fcObj)
            throws ReflectiveOperationException {
        
        List<FileComponent> subComponents = new ArrayList<>();
        
        for (Field field : fcObj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldVal = field.get(fcObj);
            if (fieldVal instanceof FileComponent) {
                FileComponent fcSub = (FileComponent) fieldVal;
                fcSub.setName(field.getName());
                subComponents.add(fcSub);
            }
        }
        
        return subComponents;
    }
//    
//    // todo
//    public static void inferSubComponentName(FileComponent fcObj)
//            throws ReflectiveOperationException {
//        
//        for (Class<?> fcClass = fcObj.getClass(); fcClass != null; fcClass = fcClass.getSuperclass()) {
//            for (Field field : fcClass.getDeclaredFields()) {
//                field.setAccessible(true);
//                if (isFileComponentType(field)) {
//                    // field is FileComponent
//                    FileComponent fcFieldVal = (FileComponent) field.get(fcObj);
//                    if (fcFieldVal != null) {
//                        fcFieldVal.setName(field.getName());
//                        inferSubComponentName(fcFieldVal);
//                    }
//                } else if (isFileComponentArrayType(field)) {
//                    // field is FileComponent[]
//                    Object arrFieldVal = field.get(fcObj);
//                    if (arrFieldVal != null) {
//                        int length = Array.getLength(arrFieldVal);
//                        for (int i = 0; i < length; i++) {
//                            FileComponent arrItem = (FileComponent) Array.get(arrFieldVal, i);
//                            if (arrItem != null) {
//                                inferSubComponentName(arrItem);
//                            }
//                        }
//                    }
//                } else {
//                    Object fcFieldVal = field.get(fcObj);
//                    if (isFileComponentList(fcFieldVal)) {
//                        // field is List<FileComponent>
//                        @SuppressWarnings("unchecked")
//                        List<FileComponent> list = (List<FileComponent>) fcFieldVal;
//                        for (FileComponent item : list) {
//                            inferSubComponentName(item);
//                        }
//                    }
//                }
//            }
//        }
//    }
//    
//    private static boolean isFileComponentType(Field field) {
//        return FileComponent.class.isAssignableFrom(field.getType());
//    }
//    
//    private static boolean isFileComponentArrayType(Field field) {
//        if (! field.getType().isArray()) {
//            return false;
//        }
//        
//        return FileComponent.class.isAssignableFrom(
//                field.getType().getComponentType());
//    }
//    
//    private static boolean isFileComponentList(Object obj) {
//        if (! (obj instanceof List)) {
//            return false;
//        }
//        
//        List<?> list = (List<?>) obj;
//        if (list.isEmpty()) {
//            return false;
//        }
//        
//        return list.get(0) instanceof FileComponent;
//    }
    
}
