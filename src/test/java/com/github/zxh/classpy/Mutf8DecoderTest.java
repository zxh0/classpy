package com.github.zxh.classpy;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import static com.github.zxh.classpy.classfile.jvm.Mutf8Decoder.decodeMutf8;
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author zxh
 */
public class Mutf8DecoderTest {
    
    @Test
    public void ascii() throws IOException {
        String str = "abcdefg";
        byte[] bytes = encodeMutf8(str);
        assertEquals(7, bytes.length);
        assertEquals(str, decodeMutf8(bytes));
    }
    
    @Test
    public void u0000() throws IOException {
        String str = "\u0000";
        byte[] bytes = encodeMutf8(str);
        assertEquals(2, bytes.length);
        assertEquals(str, decodeMutf8(bytes));
    }
    
    @Test
    public void bmp() throws IOException {
        String str = "汉字";
        byte[] bytes = encodeMutf8(str);
        assertEquals(6, bytes.length);
        assertEquals(str, decodeMutf8(bytes));
    }
    
    @Test
    public void surrogatePair() throws IOException {
        String str = "\ud801\udc00";
        byte[] bytes = encodeMutf8(str);
        assertEquals(6, bytes.length);
        assertEquals(str, decodeMutf8(bytes));
    }
    
    private static byte[] encodeMutf8(String str) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput out = new DataOutputStream(baos);
        out.writeUTF(str);
        byte[] bytes = baos.toByteArray();
        // remove length
        return Arrays.copyOfRange(bytes, 2, bytes.length);
    }
    
}
