package com.github.zxh.classpy;

import java.util.List;

public class TestClass implements Runnable, Comparable<TestClass> {

    public static final String STR = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
    public static final int INT1 = 65535;
    public static final int INT2 = -8;
    public static final float FLOAT = 3.14f;
    public static final long LONG1 = Long.MAX_VALUE;
//    public static final long LONG2 = -1;
//    public static final double DOUBLE = 2.718d;
    
    public int x;
    private float y;
    
    @Override
    public void run() {
        Runnable r = () -> {};
    }

    @Override
    public int compareTo(TestClass o) {
        return 0;
    }
    
    @Deprecated
    public static <T extends Comparable<T>> void m1(List<T> list) throws RuntimeException {
        list.sort((a, b) -> 1);
    }
    
    public void testEnclosingMethodAttribute() {
        new Runnable() {

            @Override
            public void run() {
                //
            }
            
        }.run();
    }
    
}
