package com.github.zxh.classpy.testclasses;

import java.io.IOException;

public class AttrSimple {
    
    @Deprecated
    public void testDeprecatedAttribute() {
        
    }
    
    public void testExceptionsAttribute() throws IOException, RuntimeException {
        
    }
    
    public void testInvokeDynamic() {
        Runnable r = () -> {};
    }
    
    public void testEnclosingMethodAttribute() {
        new Runnable() {

            @Override
            public void run() {
                //
            }
            
        }.run();
    }
    
    public void testCodeAttribute() {
        int a = 1;
        int b = 2;
        try {
            int c = a / b;
        } catch (Exception e) {
            //
        }
    }
    
}
