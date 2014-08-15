package com.github.zxh.classpy;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class TypeAnnotatedClass<T> extends 
        @MyTypeAnnotation ArrayList<@MyTypeAnnotation T>  {
    
}
