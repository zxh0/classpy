package com.github.zxh.classpy.testclasses;

public class ByteCode {
    
    //public static final int CONST_INT = 2014;
    public static final String CONST_STR = "PI";
    
    public Object aconst_null_areturn() {
        return null;
    }
    
    public void iconst_x_return() {
        int x;
        x = -1;
        x = 0;
        x = 1;
        x = 2;
        x = 3;
        x = 4;
        x = 5;
    }
    
    public void lconst_x() {
        long x;
        x = 0;
        x = 1;
    }
    
    public void fconst_x() {
        float x;
        x = 0;
        x = 1;
        x = 2;
    }
    
    public void dconst_x() {
        double x;
        x = 0;
        x = 1;
    }
    
    public void bipush_sipush() {
        int x = 6;
        short y = 257;
    }
    
    public void ldc() {
        String x = CONST_STR;
    }
    
}
