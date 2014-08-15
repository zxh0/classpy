package com.github.zxh.classpy;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class TypeAnnotatedClass<@MyTypeAnnotation(strValue = "targetType0x00") T extends @MyTypeAnnotation(strValue = "targetType0x11") Object> extends 
        @MyTypeAnnotation(strValue = "targetType0x10") ArrayList<T>  {
    
    @MyTypeAnnotation(strValue = "targetType0x13")
    public int targetType0x13;
    
    public <@MyTypeAnnotation(strValue = "targetType0x01") T> void targetType0x01() {
        
    }
    
    public <T extends@MyTypeAnnotation(strValue = "targetType0x12") Object> void targetType0x12() {
        
    }
    
}
