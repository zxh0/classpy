package com.github.zxh.classpy.pecoff.datatype;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;

/**
 *
 * @author zxh
 */
public class UInt64 extends PeComponent {
    
    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(PeReader reader) {
        value = reader.readLong();
        describe(value);
    }
    
    protected void describe(long value) {
        setDesc(value);
    }
    
}
