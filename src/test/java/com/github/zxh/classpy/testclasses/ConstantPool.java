package com.github.zxh.classpy.testclasses;

public class ConstantPool {
    
    public static final String CONST_STRING = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
    public static final int CONST_INT1 = 65535;
    public static final int CONST_INT2 = -8;
    public static final float CONST_FLOAT = 3.14f;
    public static final long CONST_LONG1 = Long.MAX_VALUE;
    public static final long CONST_LONG2 = -1;
    public static final double CONST_DOUBLE = -2.718d;
    
    private Runnable r;
    
    public void run() {
        r = () -> {};
        r.run();
    }
    
}
