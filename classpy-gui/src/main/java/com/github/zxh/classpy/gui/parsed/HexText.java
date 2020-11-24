package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.common.FilePart;

/**
 * Displayed by HexPane.
 * 
 * The formatted hex string looks like this:
 * 00000000| CA FE BA BE 00 00 00 34 00 2C 0A 00 06 00 27 07 |.......4.,....'.
 * 00000010| 00 28 07 00 29 0A 00 03 00 27 07 00 2A 07 00 2B |.(..)....'..*..+
 * ...
 */
public class HexText {
    
    private static final int BYTES_PER_ROW = 16;
    private static final int ROW_LEN = 76;
    
    private final String text;
    
    public HexText(byte[] bytes) {
        text = format(bytes);
    }
    
    private String format(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        
        for (int i = 0; i < bytes.length; i += BYTES_PER_ROW) {
            buf.append(String.format("%08X", i)); // row number
            buf.append('|').append(' ');
            rowToHex(bytes, i, buf); // hex
            buf.append('|');
            rowToAscii(bytes, i, buf); // ascii
            buf.append('\n');
        }
        
        return buf.toString();
    }
    
    private void rowToHex(byte[] bytes, int offset, StringBuilder buf) {
        for (int i = 0; i < BYTES_PER_ROW; i++) {
            if (offset + i < bytes.length) {
                byte b = bytes[offset + i];
                buf.append(String.format("%02X ", b));
            } else {
                buf.append("   ");
            }
        }
    }
    
    private void rowToAscii(byte[] bytes, int offset, StringBuilder buf) {
        for (int i = 0; i < BYTES_PER_ROW; i++) {
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

    public String getText() {
        return text;
    }
    
    // TODO
    public Selection select(FilePart fp) {
        Selection selection = new Selection();
        selection.startPosition = calcTextPosition(fp.getOffset());
        selection.endPosition = calcTextPosition(fp.getOffset() + fp.getLength()) - 1;
        if (selection.endPosition % ROW_LEN == 9) {
            selection.endPosition -= 27;
        }
        return selection;
    }
    
    private int calcTextPosition(int byteOffset) {
        int rowIndex = byteOffset / BYTES_PER_ROW;
        int colIndex = byteOffset % BYTES_PER_ROW;
        return (ROW_LEN * rowIndex) + 10 + (colIndex * 3);
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
