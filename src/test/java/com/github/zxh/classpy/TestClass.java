package com.github.zxh.classpy;

import java.io.IOException;

public class TestClass implements Runnable, Comparable<TestClass> {

    public static final String CONST_STRING = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
    public static final int CONST_INT1 = 65535;
    public static final int CONST_INT2 = -8;
    public static final float CONST_FLOAT = 3.14f;
    public static final long CONST_LONG1 = Long.MAX_VALUE;
    public static final long CONST_LONG2 = -1;
    public static final double CONST_DOUBLE = -2.718d;
    
    public int x;
    private float y;
    
    @Override
    public void run() {
        
    }

    @Override
    public int compareTo(TestClass o) {
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
