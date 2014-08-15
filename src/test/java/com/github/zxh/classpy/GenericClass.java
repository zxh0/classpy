package com.github.zxh.classpy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenericClass<T> {
    
    private final List<? extends T> listOfT = new ArrayList<>();
    
    public static <T extends Comparable<T>> void m1(List<T> list) {
        //list.sort((a, b) -> 1);
    }
    
    public void testLocalVariableTypeTableAttribute() {
        List<String> ss = new ArrayList<>();
        Comparator<Long> c = null;
        int a = 1;
        int b = 1;
        int d = a + b;
    }
    
}
