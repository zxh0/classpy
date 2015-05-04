package com.github.zxh.classpy;

import com.github.zxh.classpy.classfile.ClassComponent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ClassComponentHelper {
    
    // todo
    public static List<ClassComponent> findSubComponents(ClassComponent fcObj)
            throws ReflectiveOperationException {
        
        List<ClassComponent> subComponents = new ArrayList<>();
        
        for (Class<?> fcClass = fcObj.getClass(); fcClass != null; fcClass = fcClass.getSuperclass()) {
            for (Field field : fcClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object fieldVal = field.get(fcObj);
                if (fieldVal instanceof ClassComponent) {
                    ClassComponent fcSub = (ClassComponent) fieldVal;
                    fcSub.setName(field.getName());
                    subComponents.add(fcSub);
                }
            }
        }
        
        subComponents.sort((a, b) -> {
            return Integer.compare(a.getOffset(), b.getOffset());
        });
        
        return subComponents;
    }
//    
//    // todo
//    public static void inferSubComponentName(ClassComponent fcObj)
//            throws ReflectiveOperationException {
//        
//        for (Class<?> fcClass = fcObj.getClass(); fcClass != null; fcClass = fcClass.getSuperclass()) {
//            for (Field field : fcClass.getDeclaredFields()) {
//                field.setAccessible(true);
//                if (isClassComponentType(field)) {
//                    // field is ClassComponent
//                    ClassComponent fcFieldVal = (ClassComponent) field.get(fcObj);
//                    if (fcFieldVal != null) {
//                        fcFieldVal.setName(field.getName());
//                        inferSubComponentName(fcFieldVal);
//                    }
//                } else if (isClassComponentArrayType(field)) {
//                    // field is ClassComponent[]
//                    Object arrFieldVal = field.get(fcObj);
//                    if (arrFieldVal != null) {
//                        int length = Array.getLength(arrFieldVal);
//                        for (int i = 0; i < length; i++) {
//                            ClassComponent arrItem = (ClassComponent) Array.get(arrFieldVal, i);
//                            if (arrItem != null) {
//                                inferSubComponentName(arrItem);
//                            }
//                        }
//                    }
//                } else {
//                    Object fcFieldVal = field.get(fcObj);
//                    if (isClassComponentList(fcFieldVal)) {
//                        // field is List<ClassComponent>
//                        @SuppressWarnings("unchecked")
//                        List<ClassComponent> list = (List<ClassComponent>) fcFieldVal;
//                        for (ClassComponent item : list) {
//                            inferSubComponentName(item);
//                        }
//                    }
//                }
//            }
//        }
//    }
//    
//    private static boolean isClassComponentType(Field field) {
//        return ClassComponent.class.isAssignableFrom(field.getType());
//    }
//    
//    private static boolean isClassComponentArrayType(Field field) {
//        if (! field.getType().isArray()) {
//            return false;
//        }
//        
//        return ClassComponent.class.isAssignableFrom(
//                field.getType().getComponentType());
//    }
//    
//    private static boolean isClassComponentList(Object obj) {
//        if (! (obj instanceof List)) {
//            return false;
//        }
//        
//        List<?> list = (List<?>) obj;
//        if (list.isEmpty()) {
//            return false;
//        }
//        
//        return list.get(0) instanceof ClassComponent;
//    }
    
}
