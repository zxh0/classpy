package com.github.zxh.classpy;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class TypeAnnotatedClass<@MyTypeAnnotation/*targetType0x00*/ T> extends 
        /*@MyTypeAnnotation*/ ArrayList</*@MyTypeAnnotation*/ T>  {
    
    public <@MyTypeAnnotation T> void targetType0x01() {
        
    }
    
}
