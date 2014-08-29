package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.common.Util;

/**
 *
 * @author zxh
 */
public class UIntHex extends UInt {

    @Override
    protected void describe(int value) {
        setDesc(Util.toHexString(value));
    }
    
}
