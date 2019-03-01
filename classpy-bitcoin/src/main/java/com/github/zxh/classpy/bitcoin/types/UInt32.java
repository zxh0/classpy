package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockPart;
import com.github.zxh.classpy.bitcoin.BlockReader;

public class UInt32 extends BlockPart {

    @Override
    protected void readContent(BlockReader reader) {
        long value = reader.readUnsignedInt();
        setDesc(Long.toString(value));
    }

}
