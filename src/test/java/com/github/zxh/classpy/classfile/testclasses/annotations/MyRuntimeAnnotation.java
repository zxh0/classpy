package com.github.zxh.classpy.classfile.testclasses.annotations;

import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRuntimeAnnotation {
    
    int intValue() default 1;
    String strValue() default "str";
    ElementType enumValue() default ElementType.TYPE;
    Class<?> classValue() default Object.class;
    Target annotationValue() default @Target({TYPE});
    String[] arrayValue() default {"A", "B", "C"};
    
}
