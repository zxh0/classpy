package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.helper.StringHelper;

/**
 * Displayed by HexPane.
 * <p>
 * The formatted hex string looks like this:
 * 00000000| CA FE BA BE 00 00 00 34 00 2C 0A 00 06 00 27 07 |.......4.,....'.
 * 00000010| 00 28 07 00 29 0A 00 03 00 27 07 00 2A 07 00 2B |.(..)....'..*..+
 * ...
 */
public class HexText {

    public static final int BYTES_PER_ROW = 16;

    public final String rowHeaderText;

    public final String bytesText;

    public final String asciiString;

    public HexText(byte[] bytes) {
        rowHeaderText = formatRowHeader(bytes.length);
        bytesText = formatBytesText(bytes);
        asciiString = formatAsciiText(bytes);
    }

    private String formatRowHeader(int length) {
        StringBuilder sb = new StringBuilder((length / BYTES_PER_ROW + 1) * 11);

        for (int i = 0; i < length; i += BYTES_PER_ROW) {
            sb.append(String.format("%08X", i)); // row number
            sb.append('\n');
        }
        return sb.toString();
    }

    private String formatBytesText(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i += BYTES_PER_ROW) {
            rowToHex(bytes, i, sb);
            sb.append('\n');
        }
        return sb.toString();
    }

    private String formatAsciiText(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i += BYTES_PER_ROW) {
            rowToAscii(bytes, i, sb);
            sb.append('\n');
        }
        return sb.toString();
    }

    private void rowToHex(byte[] bytes, int offset, StringBuilder buf) {
        for (int i = 0; i < BYTES_PER_ROW; i++) {
            if (offset + i < bytes.length) {
                byte b = bytes[offset + i];
                buf.append(StringHelper.toString(b));
                buf.append(' ');
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

    public Selection select(FileComponent cc) {
        Selection selection = new Selection();
        selection.startPosition = calcTextPosition(cc.getOffset());
        selection.endPosition = calcTextPosition(cc.getOffset() + cc.getLength()) - 1;
        return selection;
    }

    private int calcTextPosition(int byteOffset) {
        int rowIndex = byteOffset / BYTES_PER_ROW;
        int colIndex = byteOffset % BYTES_PER_ROW;
        return (76 * rowIndex) + 10 + (colIndex * 3);
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
