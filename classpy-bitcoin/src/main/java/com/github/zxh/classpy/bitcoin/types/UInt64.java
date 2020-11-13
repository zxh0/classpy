package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockPart;
import com.github.zxh.classpy.bitcoin.BlockReader;

public class UInt64 extends BlockPart {

    @Override
    protected void readContent(BlockReader reader) {
        long value = reader.readFixedI64();
        setDesc(Long.toString(value));
    }

}
