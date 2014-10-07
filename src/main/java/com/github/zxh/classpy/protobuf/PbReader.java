package com.github.zxh.classpy.protobuf;

import com.github.zxh.classpy.common.BytesReader;
import java.nio.ByteOrder;

/**
 *
 * @author zxh
 */
public class PbReader extends BytesReader {

    public PbReader(byte[] bytes, ByteOrder order) {
        super(bytes, order, true);
    }
    
}
