package com.github.zxh.classpy.pecoff.datatype;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.common.Util;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;

/**
 *
 * @author zxh
 */
public class UInt16Hex extends PeComponent implements IntValue {
    
    private int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    protected void readContent(PeReader reader) {
        value = Short.toUnsignedInt(reader.getByteBuffer().getShort());
        setDesc(Util.toHexString(value));
    }
    
}
