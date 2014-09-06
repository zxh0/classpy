package com.github.zxh.classpy.dexfile.datatype;

/**
 *
 * @author zxh
 */
public class Uleb128p1 extends Uleb128 {

    @Override
    public int getValue() {
        return super.getValue() - 1;
    }
    
    @Override
    protected void describe(int value) {
        setDesc(value - 1);
    }
    
}
