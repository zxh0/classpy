package com.github.zxh.classpy.classfile.reader

import java.nio.ByteBuffer
import java.nio.ByteOrder

open class BytesReader(bytes: ByteArray) {
    
    private val buf: ByteBuffer = ByteBuffer.wrap(bytes)
            .asReadOnlyBuffer()
            .order(ByteOrder.BIG_ENDIAN)

    val position: Int
        get() = buf.position()

    fun getByte(index: Int): Byte  = buf.get(index)
    fun getShort(index: Int): Short  = buf.getShort(index)
    
    fun readByte(): Byte = buf.get()
    fun readUnsignedByte(): Int = java.lang.Byte.toUnsignedInt(buf.get())
    fun readShort(): Short = buf.getShort()
    fun readUnsignedShort(): Int = java.lang.Short.toUnsignedInt(buf.getShort())
    fun readInt(): Int = buf.getInt()

    fun readBytes(n: Int): ByteArray {
        val bytes = ByteArray(n);
        buf.get(bytes);
        return bytes;
    }

    fun skipBytes(n: Int) {
        for (i in 0..n-1) {
            buf.get();
        }
    }

}
