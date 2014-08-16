package com.github.zxh.classpy.gui.hex;

import com.github.zxh.classpy.classfile.ClassFile;

/**
 *
 * @author zxh
 */
public class ClassHex {
    
    private final int bytesPerRow = 16; // todo
    private String hexString;
    
    public ClassHex(ClassFile cf) {
        initHexString(cf.getBytes());
    }
    
    private void initHexString(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        
        for (int i = 0; i < bytes.length; i += bytesPerRow) {
            buf.append(String.format("%08X", i));
            buf.append('|').append(' ');
            rowToHex(bytes, i, buf);
            buf.append('|');
            rowToAscii(bytes, i, buf);
            buf.append('\n');
        }
        
        hexString = buf.toString();
    }
    
    private void rowToHex(byte[] bytes, int offset, StringBuilder buf) {
        for (int i = 0; i < bytesPerRow; i++) {
            if (offset + i < bytes.length) {
                byte b = bytes[offset + i];
                int unsignedByte = Byte.toUnsignedInt(b);
                if (unsignedByte < 16) {
                    buf.append('0');
                }
                buf.append(Integer.toHexString(unsignedByte).toUpperCase());
                buf.append(' ');
            } else {
                buf.append("   ");
            }
        }
    }
    
    private void rowToAscii(byte[] bytes, int offset, StringBuilder buf) {
        for (int i = 0; i < bytesPerRow; i++) {
            if (offset + i < bytes.length) {
                char c = (char) bytes[offset + i];
                if (c >= '!' && c <= '~') {
                    buf.append(c);
                } else {
                    buf.append('.');
                }
            }
        }
    }

    public String getHexString() {
        return hexString;
    }

    
}
