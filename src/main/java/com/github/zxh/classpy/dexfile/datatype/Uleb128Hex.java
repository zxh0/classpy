package com.github.zxh.classpy.dexfile.datatype;

/**
 *
 * @author zxh
 */
public class Uleb128Hex extends Uleb128 {

    @Override
    protected void describe(int value) {
        setDesc("0x" + Integer.toHexString(value));
    }
    
}
