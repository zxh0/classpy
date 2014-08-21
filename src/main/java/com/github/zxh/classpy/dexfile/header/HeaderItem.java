package com.github.zxh.classpy.dexfile.header;

import com.github.zxh.classpy.dexfile.Hex;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;

/**
 *
 * @author zxh
 */
public class HeaderItem extends DexComponent {

    private FileMagic magic;
    private UInt checksum;
    private Hex signature;
    private UInt fileSize;
    private UInt headerSize;
    private Hex endianTag;
    
    @Override
    protected void readContent(DexReader reader) {
        magic = new FileMagic();
        magic.read(reader);
        checksum = reader.readUInt();
        signature = reader.readHex(20);
        fileSize = reader.readUInt();
        headerSize = reader.readUInt();
        endianTag = reader.readHex(4);
        // todo
    }
    
}
