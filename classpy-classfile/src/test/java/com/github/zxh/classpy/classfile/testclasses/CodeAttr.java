package com.github.zxh.classpy.classfile.testclasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CodeAttr {
    
    public void simpleMethod(int x, Object y) {
        int a = 1;
        int b = 2;
        try {
            int c = a / b;
        } catch (Exception e) {
            //
        }
    }
    
    public void localVariableTypeTableAttribute() {
        List<String> ss = new ArrayList<>();
        Comparator<Long> c = null;
        int a = 1;
        int b = 1;
        int d = a + b;
    }
    
}
