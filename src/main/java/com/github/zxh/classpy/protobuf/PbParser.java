package com.github.zxh.classpy.protobuf;

import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class PbParser implements FileParser {

    @Override
    public PbMessage parse(byte[] bytes) {
        PbMessage msg = new PbMessage();
        msg.read(new PbReader(bytes));
        return msg;
    }
    
}
