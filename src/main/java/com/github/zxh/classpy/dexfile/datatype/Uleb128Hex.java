package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.common.Util;

/**
 *
 * @author zxh
 */
public class Uleb128Hex extends Uleb128 {

    @Override
    protected void describe(int value) {
        setDesc(Util.toHexString(value));
    }
    
}
