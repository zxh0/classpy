package com.github.zxh.classpy;

import com.github.zxh.classpy.common.BytesReader;
import org.junit.Test;

import java.nio.ByteOrder;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author zxh
 */
public class BytesReaderTest {
    
    @Test
    public void order() {
        BytesReader be = new BytesReader(new byte[] {0x12, 0x34},
                ByteOrder.BIG_ENDIAN);
        assertEquals(0x1234, be.readShort());
    }
    
}
