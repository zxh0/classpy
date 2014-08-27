package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import com.github.zxh.classpy.dexfile.index.UIntStringIndex;
import com.github.zxh.classpy.dexfile.index.UIntTypeIdIndex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ProtoIdItem extends DexComponent {

    private UIntStringIndex shortyIdx;
    private UIntTypeIdIndex returnTypeIdx;
    private UInt parametersOff; // todo type_list

    @Override
    protected void readContent(DexReader reader) {
        shortyIdx = reader.readUIntStringIndex();
        returnTypeIdx = reader.readUIntTypeIdIndex();
        parametersOff = reader.readUInt();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        super.postRead(dexFile);
        setDesc(dexFile.getString(shortyIdx));
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(shortyIdx, returnTypeIdx, parametersOff);
    }
    
}
