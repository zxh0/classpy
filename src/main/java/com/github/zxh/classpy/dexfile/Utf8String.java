package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.Util;
import java.io.IOException;

/**
 *
 * @author zxh
 */
public class Utf8String extends DexComponent {

    private String value;
    
    @Override
    protected void readContent(DexReader reader) {
        try {
            byte[] data = reader.readMutf8Bytes();
            value = Util.decodeMutf8(data);
            setDesc(value);
        } catch (IOException e) {
            throw new FileParseException(e);
        }
    }
    
}
