package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.datatype.UIntStringIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UIntTypeIdIndex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ProtoIdItem extends DexComponent {

    private UIntStringIdIndex shortyIdx;
    private UIntTypeIdIndex returnTypeIdx;
    private UIntHex parametersOff; // todo type_list

    public UIntHex getParametersOff() {
        return parametersOff;
    }

    @Override
    protected void readContent(DexReader reader) {
        shortyIdx = reader.readUIntStringIdIndex();
        returnTypeIdx = reader.readUIntTypeIdIndex();
        parametersOff = reader.readUIntHex();
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
