package com.github.zxh.classpy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MyAnnotation(
        intValue = 100,
        strValue = "test",
        enumValue = ElementType.METHOD,
        annotationValue = @Target({})
)
public class AnnotatedClass {
    

    
}
