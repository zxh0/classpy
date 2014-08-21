package com.github.zxh.classpy.dex.header;

import com.github.zxh.classpy.dex.DexComponent;
import com.github.zxh.classpy.dex.DexReader;

/**
 *
 * DEX_FILE_MAGIC="dex\nXXX\0".
 * 
 * @author pc
 */
public class FileMagic extends DexComponent {

    @Override
    protected void readContent(DexReader reader) {
        reader.skipBytes(4); // todo check
        StringBuilder magic = new StringBuilder("dex\\n");
        magic.append((char) reader.readUByte());
        magic.append((char) reader.readUByte());
        magic.append((char) reader.readUByte());
        reader.skipBytes(1);
        magic.append("\\0");
    }
    
}
