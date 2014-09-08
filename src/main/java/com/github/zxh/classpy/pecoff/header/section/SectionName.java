package com.github.zxh.classpy.pecoff.header.section;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author zxh
 */
public class SectionName extends PeComponent {

    @Override
    protected void readContent(PeReader reader) {
        // todo
        byte[] bytes = reader.readBytes(8);
        int nonZeroBytes = countNonZeroBytes(bytes);
        String name = new String(bytes, 0, nonZeroBytes, StandardCharsets.UTF_8);
        setDesc(name);
    }
    
    private static int countNonZeroBytes(byte[] bytes) {
        int count = 0;
        for (byte b : bytes) {
            if (b != 0) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
    
}
