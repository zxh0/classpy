package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

/**
 * string literal.
 *
 * @see /lua/src/ldump.c#DumpLiteral
 */
public class Literal extends LuacOutComponent {

    private final int bytesCount;

    public Literal(int bytesCount) {
        this.bytesCount = bytesCount;
    }

    @Override
    protected void readContent(LuacOutReader reader) {
        byte[] bytes = reader.readBytes(bytesCount);
        setDesc(toLuaLiteral(bytes));
    }

    private static String toLuaLiteral(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        for (byte b : bytes) {
            char c = (char) b;
            if (c >= '0' && c <= '9'
                    || c >= 'a' && c <= 'z'
                    || c >= 'A' && c <= 'Z') {
                sb.append(c);
            } else if (c == '\r') {
                sb.append("\\r");
            } else if (c == '\n') {
                sb.append("\\n");
            } else {
                sb.append(String.format("\\x%02x", b));
            }
        }
        sb.append('"');
        return sb.toString();
    }

}
