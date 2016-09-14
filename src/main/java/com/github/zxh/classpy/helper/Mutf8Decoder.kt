package com.github.zxh.classpy.helper

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException

object Mutf8Decoder {
    
    /**
     * Decode modified UTF-8 string from byte[].
     * todo: optimize
     * 
     * @param bytes
     * @return 
     * @throws java.io.IOException 
     */
    @Throws(IOException::class)
    @JvmStatic
    fun decodeMutf8(bytes: ByteArray): String {
        val baos = ByteArrayOutputStream(bytes.size + 2);
        val dos = DataOutputStream(baos)
        dos.writeShort(bytes.size);
        dos.write(bytes);

        val bais = ByteArrayInputStream(baos.toByteArray());
        val dis = DataInputStream(bais);
        return dis.readUTF();
    }
    
}
