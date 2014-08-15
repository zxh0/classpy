package com.github.zxh.classpy;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class TypeAnnotatedClass<T> extends 
        @MyRuntimeAnnotation ArrayList<@MyRuntimeAnnotation T>  {
    
}
