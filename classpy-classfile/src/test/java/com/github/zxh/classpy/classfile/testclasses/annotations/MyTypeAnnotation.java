package com.github.zxh.classpy.classfile.testclasses.annotations;

import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.TYPE_USE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE_USE})
@Retention(RetentionPolicy.CLASS)
public @interface MyTypeAnnotation {
    
    int intValue() default 1;
    String strValue() default "str";
    ElementType enumValue() default ElementType.TYPE;
    Class<?> classValue() default Object.class;
    Target annotationValue() default @Target({TYPE_USE});
    String[] arrayValue() default {"A", "B", "C"};
    
}
