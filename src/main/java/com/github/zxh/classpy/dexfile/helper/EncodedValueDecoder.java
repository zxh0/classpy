package com.github.zxh.classpy.dexfile.helper;

import com.github.zxh.classpy.dexfile.datatype.ByteArray;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Arrays;

/**
 *
 * @author pc
 */
public class EncodedValueDecoder extends DataInputStream {
    
    public EncodedValueDecoder(ByteArray buf, int extendedByteCount, boolean signExtend) {
        this(buf.getBytes(), extendedByteCount, signExtend);
    }
    
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
            if (signExtend && extendedBuf[buf.length - 1] < 0) {
                Arrays.fill(extendedBuf, buf.length, extendedBuf.length, (byte) 0xFF);
            }
        }

        // little-endian to big-endian
        for (int i = 0; i < extendedBuf.length / 2; i++) {
            int j = extendedBuf.length -1 - i;
            byte tmp = extendedBuf[i];
            extendedBuf[i] = extendedBuf[j];
            extendedBuf[j] = tmp;
        }

        return extendedBuf;
    }

}
