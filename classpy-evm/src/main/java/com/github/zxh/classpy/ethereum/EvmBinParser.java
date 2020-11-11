package com.github.zxh.classpy.ethereum;

import com.github.zxh.classpy.common.FileParser;

public class EvmBinParser implements FileParser {

    @Override
    public EvmBinFile parse(byte[] data) {
        EvmBinFile file = new EvmBinFile();
        file.readContent(new EvmBinReader(data));
        return file;
    }

}
