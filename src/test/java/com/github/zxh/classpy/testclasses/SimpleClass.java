package com.github.zxh.classpy.testclasses;

import java.io.IOException;

public class SimpleClass implements Runnable, Comparable<SimpleClass> {

    public int x;
    private float y;
    
    @Override
    public void run() {
        
    }

    @Override
    public int compareTo(SimpleClass o) {
        return 0;
    }
    
    @Deprecated
    public void testDeprecatedAttribute() {
        
    }
    
    public void testExceptionsAttribute() throws IOException, RuntimeException {
        
    }
    
    public void testInvokeDynamic() {
        Runnable r = () -> {};
    }
    
    public int m2() {
        return x + 1;
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
