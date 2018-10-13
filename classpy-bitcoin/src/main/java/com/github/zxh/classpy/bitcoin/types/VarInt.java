package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockComponent;
import com.github.zxh.classpy.bitcoin.BlockReader;

// https://en.bitcoin.it/wiki/Protocol_documentation#Variable_length_integer
public class VarInt extends BlockComponent {

    private long value;

    public long getValue() {
        return value;
    }

    @Override
    protected void readContent(BlockReader reader) {
        value = reader.readVarInt();
        setDesc(Long.toString(value));
    }

}
