package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 *
 * @author zxh
 */
public class UInt extends DexComponent implements IntValue {

    private int value;

    @Override
    public int getValue() {
        if (value < 0) {
            // todo
        }
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        value = reader.readInt(); // todo
        setDesc(value);
    }
    
    protected void setDesc(int value) {
        if (value < 0) {
            setDesc(Integer.toUnsignedString(value));
        } else {
            setDesc(Integer.toString(value));
        }
    }
    
}
