package com.github.zxh.classpy.dexfile.header;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 *
 * DEX_FILE_MAGIC="dex\nXXX\0".
 * 
 * @author zxh
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
        setDesc(magic.toString());
    }
    
}
