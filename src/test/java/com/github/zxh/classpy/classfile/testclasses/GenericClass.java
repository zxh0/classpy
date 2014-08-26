package com.github.zxh.classpy.classfile.testclasses;

import java.util.ArrayList;
import java.util.List;

// SignatureAttribute
public class GenericClass<T> {
    
    // SignatureAttribute
    private final List<? extends T> listOfT = new ArrayList<>();
    
    // SignatureAttribute
    public static <T extends Comparable<T>> void m1(List<T> list) {
        //list.sort((a, b) -> 1);
    }
    
}
