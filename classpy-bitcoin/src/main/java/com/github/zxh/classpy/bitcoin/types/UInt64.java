package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockComponent;
import com.github.zxh.classpy.bitcoin.BlockReader;

public class UInt64 extends BlockComponent {

    @Override
    protected void readContent(BlockReader reader) {
        long value = reader.readLong();
        setDesc(Long.toString(value));
    }

}
