package com.github.zxh.classpy.dexfile.header;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 *
 * @author zxh
 */
public class HeaderItem extends DexComponent {

    private FileMagic magic;
    
    @Override
    protected void readContent(DexReader reader) {
        magic = new FileMagic();
        magic.read(reader);
        // todo
    }
    
}
