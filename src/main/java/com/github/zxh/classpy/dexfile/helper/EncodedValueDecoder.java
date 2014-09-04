package com.github.zxh.classpy.dexfile.helper;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Arrays;

/**
 *
 * @author pc
 */
public class EncodedValueDecoder extends DataInputStream {
    
    public EncodedValueDecoder(byte[] buf, int extendedByteCount, boolean signExtend) {
        super(new ByteArrayInputStream(extend(buf, extendedByteCount, signExtend)));
    }

    private static byte[] extend(byte[] buf, int extendedByteCount, boolean signExtend) {
        if (extendedByteCount < 2) {
            return buf;
        }

        byte[] extendedBuf;
        if (buf.length >= extendedByteCount) {
            extendedBuf = buf;
        } else {
            extendedBuf = Arrays.copyOf(buf, extendedByteCount);
            if (signExtend && buf[extendedByteCount - 1] < 0) {
                Arrays.fill(extendedBuf, extendedByteCount, buf.length, (byte) 0xFF);
            }
        }

        // little-endian to big-endian
        for (int i = 0; i < buf.length / 2; i++) {
            byte tmp = buf[i];
            buf[i] = buf[buf.length - i];
            buf[buf.length - i] = tmp;
        }

        return extendedBuf;
    }

}
