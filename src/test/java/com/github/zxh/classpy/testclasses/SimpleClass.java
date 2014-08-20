package com.github.zxh.classpy.testclasses;

public class SimpleClass implements Runnable, Comparable<SimpleClass> {

    public int x;
    private float y;
    
    @Override
    public void run() {
        
    }

    @Override
    public int compareTo(SimpleClass o) {
        return 0;
    }
    
    public int foo() {
        return x + 1;
    }
    
}
