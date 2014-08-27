package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UShort;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zxh
 */
public class TypeItem extends DexComponent {

    private UShort typeIdx;
    
    @Override
    protected void readContent(DexReader reader) {
        typeIdx = reader.readUShort();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Collections.singletonList(typeIdx);
    }
    
}
