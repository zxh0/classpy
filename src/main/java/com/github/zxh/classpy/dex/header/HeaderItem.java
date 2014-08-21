package com.github.zxh.classpy.dex.header;

import com.github.zxh.classpy.dex.DexComponent;
import com.github.zxh.classpy.dex.DexReader;

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
