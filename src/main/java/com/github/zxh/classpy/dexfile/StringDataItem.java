package com.github.zxh.classpy.dexfile;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class StringDataItem extends DexComponent {

    private UInt classIdx;
    private UInt typeIdx;
    private UInt nameIdx;
    
    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUInt();
        typeIdx = reader.readUInt();
        nameIdx = reader.readUInt();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classIdx, typeIdx, nameIdx);
    }
    
}
