package com.github.zxh.classpy.protobuf;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class PbParser implements FileParser {

    @Override
    public FileComponent parse(byte[] bytes) {
        PbFile pb = new PbFile();
        pb.read(new PbReader(bytes));
        return pb;
    }
    
}
