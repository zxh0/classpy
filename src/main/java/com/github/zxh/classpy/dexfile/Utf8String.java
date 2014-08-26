package com.github.zxh.classpy.dexfile;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author zxh
 */
public class Utf8String extends DexComponent {

    private String value;
    
    @Override
    protected void readContent(DexReader reader) {
        byte[] data = reader.readMutf8Bytes();
        value = new String(data, StandardCharsets.UTF_8);
        setDesc(value);
    }
    
}
