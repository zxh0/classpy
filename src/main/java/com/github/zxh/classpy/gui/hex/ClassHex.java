package com.github.zxh.classpy.gui.hex;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassFile;

/**
 *
 * @author zxh
 */
public class ClassHex {
    
    private final int bytesPerRow = 16; // todo
    private String hexString; // hexText
    
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
    
    // todo
    public Selection calcSelection(ClassComponent cc) {
        Selection selection = new Selection();
        selection.startPosition = calcTextPosition(cc.getOffset());
        selection.endPosition = calcTextPosition(cc.getOffset() + cc.getLength()) - 1;
        return selection;
    }
    
    private int calcTextPosition(int byteOffset) {
        int rowIndex = byteOffset / bytesPerRow;
        int colIndex = byteOffset % bytesPerRow;
        return (75 * rowIndex) + 10 + (colIndex * 3);
    }

    
    public static class Selection {
        
        private int startPosition;
        private int endPosition;

        public int getStartPosition() {
            return startPosition;
        }

        public int getEndPosition() {
            return endPosition;
        }
        
    }
    
}
