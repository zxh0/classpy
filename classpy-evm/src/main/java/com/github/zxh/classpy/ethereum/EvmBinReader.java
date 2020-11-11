package com.github.zxh.classpy.ethereum;

import com.github.zxh.classpy.common.BytesReader;

import java.nio.ByteOrder;

public class EvmBinReader extends BytesReader {

    public EvmBinReader(byte[] data) {
        super(data, ByteOrder.BIG_ENDIAN);
    }

}
