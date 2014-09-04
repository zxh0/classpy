package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.helper.EncodedValueDecoder;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author zxh
 */
public class EncodedValueDecoderTest {
    
    @Test
    public void decode() throws IOException {
        assertEquals((byte)8, new EncodedValueDecoder(new byte[] {0x08}, 1, true).readByte());
        assertEquals((byte)-1, new EncodedValueDecoder(new byte[] {(byte)0xff}, 1, true).readByte());
        
        assertEquals((short)0xbbaa, new EncodedValueDecoder(new byte[] {(byte)0xaa, (byte)0xbb}, 2, true).readShort());
        assertEquals((short)0xffff, new EncodedValueDecoder(new byte[] {(byte)0xff}, 2, true).readShort());
    }
    
}
