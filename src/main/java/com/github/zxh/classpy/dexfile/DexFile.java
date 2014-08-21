package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.header.HeaderItem;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header);
    }
    
}
