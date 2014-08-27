package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.Util;
import java.io.IOException;

/**
 *
 * @author zxh
 */
public class Mutf8 extends DexComponent {

    private String value;

    public String getValue() {
        return value;
    }
    
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
