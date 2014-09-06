package com.github.zxh.classpy.pecoff.datatype;

/**
 *
 * @author zxh
 */
public class UInt64Hex extends UInt64 {

    @Override
    protected void describe(long value) {
        setDesc("0x" + Long.toHexString(value));
    }
    
}
