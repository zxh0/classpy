package com.github.zxh.classpy;

public class TestClass implements Runnable, Comparable<TestClass> {

    public int x;
    private float y;
    
    @Override
    public void run() {
        Runnable x = () -> {};
    }

    @Override
    public int compareTo(TestClass o) {
        return 0;
    }
    
}
