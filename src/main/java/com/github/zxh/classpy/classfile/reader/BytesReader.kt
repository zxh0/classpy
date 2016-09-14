package com.github.zxh.classpy.classfile.reader

import java.nio.ByteBuffer
import java.nio.ByteOrder

class BytesReader(bytes: ByteArray) {
    
    private val buf: ByteBuffer = ByteBuffer.wrap(bytes)
            .asReadOnlyBuffer()
            .order(ByteOrder.BIG_ENDIAN)

    val position: Int
        get() = buf.position()

    fun getByte(index: Int): Byte  = buf.get(index)
    fun getShort(index: Int): Short  = buf.getShort(index)
    
    fun skipBytes(n: Int) {
        for (i in 0..n-1) {
            buf.get();
        }
    }
    
    // byte[]
    fun readBytes(n: Int): ByteArray {
        val bytes = ByteArray(n);
        buf.get(bytes);
        return bytes;
    }
    
    // 8-bit signed int
    fun readByte(): Byte = buf.get()

    
    // 8-bit unsigned int
    fun readUnsignedByte(): Int = java.lang.Byte.toUnsignedInt(buf.get())

    
    // 16-bit signed int
    fun readShort(): Short = buf.getShort()

    
    // 16-bit unsigned int
    fun readUnsignedShort(): Int = java.lang.Short.toUnsignedInt(buf.getShort())

    
    // 32-bit signed int
    fun readInt(): Int = buf.getInt()


}
