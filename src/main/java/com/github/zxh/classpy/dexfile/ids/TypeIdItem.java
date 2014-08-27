package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.index.UIntStringIndex;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zxh
 */
public class TypeIdItem extends DexComponent {

    private UIntStringIndex descriptorIdx;

    public UIntStringIndex getDescriptorIdx() {
        return descriptorIdx;
    }

    @Override
    protected void readContent(DexReader reader) {
        descriptorIdx = reader.readUIntStringIndex();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        super.postRead(dexFile);
        setDesc(dexFile.getString(descriptorIdx));
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Collections.singletonList(descriptorIdx);
    }
    
}
