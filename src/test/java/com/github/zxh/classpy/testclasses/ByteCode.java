package com.github.zxh.classpy.testclasses;

public class ByteCode {
    
    public static final long CONST_LONG = -1L;
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
        int x = 6; // bipush
        short y = 257; // sipush
    }
    
    public void ldc() {
        String x = CONST_STR; // ldc
        long y = CONST_LONG; // ldc_2w
    }
    
    public static void iload_istore() {
        int a = 1;
        int b = a; // iload_0 istore_1
        int c = b; // iload_1 istore_2
        int d = c; // iload_2 istore_3
        int e = d; // iload_3 istore 4
        int f = e; // iload 4 istore 5
        int g = f; // iload 5 istore 6
    }
    
    public static void lload() {
        long a = 1;
        long b = a; // lload_0
        long c = b; // lload_2
        long d = c; // lload 4
        long e = d; // lload 6
    }
    
    public static void fload() {
        float a = 3.14f;
        float b = a; // fload_0
        float c = b; // fload_1
        float d = c; // fload_2
        float e = d; // fload_3
        float f = e; // fload 4
        float g = f; // fload 5
    }
    
    public static void dload() {
        double a = 1;
        double b = a; // dload_0
        double c = b; // dload_2
        double d = c; // dload 4
        double e = d; // dload 6
    }
    
    public static void aload() {
        Object a = null;
        Object b = a; // aload_0
        Object c = b; // aload_1
        Object d = c; // aload_2
        Object e = d; // aload_3
        Object f = e; // aload 4
        Object g = f; // aload 5
    }
    
    public static void xaload(int[] a1, long[] a2, float[] a3, double[] a4,
            Object[] a5, byte[] a6, char[] a7, short[] a8) {
        
        int b1 = a1[0]; // iaload
        long b2 = a2[0]; // laload
        float b3 = a3[0]; // faload
        double b4 = a4[0]; // daload
        Object b5 = a5[0]; // aaload
        byte b6 = a6[0]; // baload
        char b7 = a7[0]; // caload
        short b8 = a8[0]; // saload
    }
    
}
