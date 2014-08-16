package com.github.zxh.classpy.gui.hex;

import com.github.zxh.classpy.classfile.ClassFile;

/**
 *
 * @author zxh
 */
public class ClassHex0 {
    
    private final int bytesPerRow = 16; // todo
    private String hexString;
    private String asciiString;
    
    public ClassHex0(ClassFile cf) {
        initHexString(cf.getBytes());
        initAsciiString(cf.getBytes());
    }
    
    private void initHexString(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        
        for (int i = 0; i < bytes.length; i++) {
            int unsignedByte = Byte.toUnsignedInt(bytes[i]);
            if (unsignedByte < 16) {
                buf.append('0');
            }
            buf.append(Integer.toHexString(unsignedByte).toUpperCase());
            if ((i + 1) % bytesPerRow == 0) {
                buf.append('\n');
            } else {
                buf.append(' ');
            }
        }
        
        hexString = buf.toString();
    }
    
    private void initAsciiString(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        
        for (int i = 0; i < bytes.length; i++) {
            char c = (char) bytes[i];
            if (c >= '!' && c <= '~') {
                buf.append(c);
            } else {
                buf.append('.');
            }
            if ((i + 1) % bytesPerRow == 0) {
                buf.append('\n');
            }
        }
        
        asciiString = buf.toString();
    }

    public String getHexString() {
        return hexString;
    }

    public String getAsciiString() {
        return asciiString;
    }
    
    
}
