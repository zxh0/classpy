package com.github.zxh.classpy.dexfile.header;

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
    private Signature signature;
    private UInt fileSize;
    private UInt headerSize;
    
    @Override
    protected void readContent(DexReader reader) {
        magic = new FileMagic();
        magic.read(reader);
        checksum = reader.readUInt();
        signature = new Signature();
        signature.read(reader);
        fileSize = reader.readUInt();
        headerSize = reader.readUInt();
        // todo
    }
    
}
