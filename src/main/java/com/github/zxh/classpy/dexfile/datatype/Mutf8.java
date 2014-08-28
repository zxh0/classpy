package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.Mutf8Decoder;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
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
            value = Mutf8Decoder.decodeMutf8(data);
            setDesc(value);
        } catch (IOException e) {
            throw new FileParseException(e);
        }
    }
    
}
