package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UIntStringIdIndex;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zxh
 */
public class TypeIdItem extends DexComponent {

    private UIntStringIdIndex descriptorIdx;

    public UIntStringIdIndex getDescriptorIdx() {
        return descriptorIdx;
    }

    @Override
    protected void readContent(DexReader reader) {
        descriptorIdx = reader.readUIntStringIdIndex();
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
