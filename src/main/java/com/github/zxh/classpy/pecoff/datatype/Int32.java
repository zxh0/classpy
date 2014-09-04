package com.github.zxh.classpy.pecoff.datatype;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;

/**
 *
 * @author zxh
 */
public class Int32 extends PeComponent implements IntValue {
    
    private int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    protected void readContent(PeReader reader) {
        value = reader.readInt();
        describe(value);
    }
    
    protected void describe(int value) {
        setDesc(value);
    }
    
}
