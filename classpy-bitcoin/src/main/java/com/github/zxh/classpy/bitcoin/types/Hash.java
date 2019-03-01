package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockPart;
import com.github.zxh.classpy.bitcoin.BlockReader;

public class Hash extends BlockPart {

    @Override
    protected void readContent(BlockReader reader) {
        byte[] bytes = reader.readBytes(32);

        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            sb.append(Integer.toHexString(bytes[i] & 0xFF));
        }
        setDesc(sb.toString());
    }

}
