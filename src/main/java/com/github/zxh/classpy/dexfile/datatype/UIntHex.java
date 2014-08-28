package com.github.zxh.classpy.dexfile.datatype;

/**
 *
 * @author zxh
 */
public class UIntHex extends UInt {

    @Override
    protected void setDesc(int value) {
        setDesc("0x" + Integer.toHexString(value));
    }
    
}
