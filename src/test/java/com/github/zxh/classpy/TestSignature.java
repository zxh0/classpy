package com.github.zxh.classpy;

import java.util.ArrayList;
import java.util.List;

public class TestSignature<T> {
    
    private final List<? extends T> listOfT = new ArrayList<>();
    
    public static <T extends Comparable<T>> void m1(List<T> list) {
        //list.sort((a, b) -> 1);
    }
    
}
