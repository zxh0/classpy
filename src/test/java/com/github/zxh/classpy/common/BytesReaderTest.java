package com.github.zxh.classpy.common;

import java.nio.ByteOrder;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author zxh
 */
public class BytesReaderTest {
    
    @Test
    public void order() {
        Stream.of(true, false).forEach(readOnly -> {
            BytesReader be = new BytesReader(new byte[] {0x12, 0x34}, ByteOrder.BIG_ENDIAN, readOnly);
            assertEquals(0x1234, be.getByteBuffer().getShort());

            BytesReader le = new BytesReader(new byte[] {0x12, 0x34}, ByteOrder.LITTLE_ENDIAN, readOnly);
            assertEquals(0x3412, le.getByteBuffer().getShort());
        });
    }
    
}
