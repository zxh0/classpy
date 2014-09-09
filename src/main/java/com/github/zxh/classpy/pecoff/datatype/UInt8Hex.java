package com.github.zxh.classpy.pecoff.datatype;

import com.github.zxh.classpy.common.Util;

/**
 *
 * @author zxh
 */
public class UInt8Hex extends UInt8 {

    @Override
    protected void describe(int value) {
        setDesc(Util.toHexString(value));
    }
    
}
