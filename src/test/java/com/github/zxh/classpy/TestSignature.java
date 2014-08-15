package com.github.zxh.classpy;

import java.util.List;

public class TestSignature {
    
    public static <T extends Comparable<T>> void testGeneric(List<T> list) {
        //list.sort((a, b) -> 1);
    }
    
}
