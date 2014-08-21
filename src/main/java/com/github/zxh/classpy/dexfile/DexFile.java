package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.header.HeaderItem;

/**
 *
 * @author zxh
 */
public class DexFile extends DexComponent {
    
    private HeaderItem header;

    @Override
    protected void readContent(DexReader reader) {
        header = new HeaderItem();
        header.read(reader);
        // todo
    }
    
}
