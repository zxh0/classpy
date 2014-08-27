package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zxh
 */
public class TypeIdItem extends DexComponent {

    private UInt descriptorIdx;

    @Override
    protected void readContent(DexReader reader) {
        descriptorIdx = reader.readUInt();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        setDesc(dexFile.getString(descriptorIdx));
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Collections.singletonList(descriptorIdx);
    }
    
}
