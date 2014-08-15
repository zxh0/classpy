package com.github.zxh.classpy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MyAnnotation(
        intValue = 100,
        strValue = "test",
        enumValue = ElementType.METHOD,
        classValue = String.class,
        annotationValue = @Target({}),
        arrayValue = {"X", "Y", "Z"}
)
public class AnnotatedClass {
    
    @MyAnnotation(
        intValue = 123,
        strValue = "test",
        enumValue = ElementType.METHOD,
        classValue = String.class,
        annotationValue = @Target({}),
        arrayValue = {"X", "Y", "Z"}
    )
    public int f1 = 123;
    
    @MyAnnotation(
        intValue = 456,
        strValue = "test",
        enumValue = ElementType.METHOD,
        classValue = String.class,
        annotationValue = @Target({}),
        arrayValue = {"X", "Y", "Z"}
    )
    public void m1() {
        
    }
    
}
