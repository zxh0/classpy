package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockComponent;
import com.github.zxh.classpy.bitcoin.BlockReader;

public class UInt32 extends BlockComponent {

    @Override
    protected void readContent(BlockReader reader) {
        long value = reader.readUnsignedInt();
        setDesc(Long.toString(value));
    }

}
