package com.github.zxh.classpy.pecoff.header.file;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import java.util.Arrays;

/**
 *
 * @author zxh
 */
public class Signature extends PeComponent {

    private static final byte[] SIGNATURE = {'P', 'E', 0, 0};
    
    @Override
    protected void readContent(PeReader reader) {
        byte[] signature = reader.readBytes(4);
        if (! Arrays.equals(signature, SIGNATURE)) {
            throw new FileParseException("Not PE/COFF file!");
        } else {
            setDesc("PE\\0\\0");
        }
    }
    
}
