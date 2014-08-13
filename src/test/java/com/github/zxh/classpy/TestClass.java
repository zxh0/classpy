package com.github.zxh.classpy;

import java.util.List;

public class TestClass implements Runnable, Comparable<TestClass> {

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
    
}
