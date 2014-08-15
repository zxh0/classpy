package com.github.zxh.classpy;

import java.util.ArrayList;
import java.util.Random;

// todo targetType0x15
@SuppressWarnings("serial")
public class TypeAnnotatedClass<@MyTypeAnnotation(strValue = "targetType0x00") T extends @MyTypeAnnotation(strValue = "targetType0x11") Object> extends 
        @MyTypeAnnotation(strValue = "targetType0x10") ArrayList<T>  {
    
    @MyTypeAnnotation(strValue = "targetType0x13")
    public int targetType0x13;
    
    public <@MyTypeAnnotation(strValue = "targetType0x01") T> void targetType0x01() {
        
    }
    
    public <T extends@MyTypeAnnotation(strValue = "targetType0x12") Object> void targetType0x12() {
        
    }
    
    public @MyTypeAnnotation(strValue = "targetType0x14") int targetType0x14() {
        return 1;
    }
    
    public void targetType0x16(@MyTypeAnnotation(strValue = "targetType0x16") int a) {
        
    }
    
    public void targetType0x17() throws @MyTypeAnnotation(strValue = "targetType0x17") Exception {
        
    }
    
    public void targetType0x40() {
        int x = 0, y = 0, z = 0;
        @MyTypeAnnotation(strValue = "targetType0x40") int a;
        a = x + y + z;
    }
    
    public void targetType0x41() throws Exception {
        try (@MyTypeAnnotation(strValue = "targetType0x41") AutoCloseable x = (AutoCloseable) null) {
            
        }
    }
    
    public void targetType0x42() {
        try {
            System.out.println("123");
        } catch (@MyTypeAnnotation(strValue = "targetType0x42") Exception e) {
            
        }
    }
    
    public void targetType0x43() {
        if ("" instanceof @MyTypeAnnotation(strValue = "targetType0x43") String) {
            
        }
    }
    
    public void targetType0x44() {
        Random r = new @MyTypeAnnotation(strValue = "targetType0x44") Random();
    }
    
}
