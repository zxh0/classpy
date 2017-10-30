package com.github.zxh.classpy.classfile.jvm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Mutf8Decoder {

    /**
     * Decode modified UTF-8 string from byte[].
     * todo: optimize
     *
     * @param bytes
     * @return
     * @throws java.io.IOException
     */
    public static String decodeMutf8(byte[] bytes) throws IOException {
        byte[] data = new byte[bytes.length + 2];
        data[0] = (byte) ((bytes.length >>> 8) & 0xFF);
        data[1] = (byte) (bytes.length & 0xFF);
        System.arraycopy(
                bytes, 0,
                data, 2,
                bytes.length
        );

        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);
        return dis.readUTF();
    }

}
