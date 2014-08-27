package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import com.github.zxh.classpy.dexfile.UShort;
import com.github.zxh.classpy.dexfile.index.UIntStringIdIndex;
import com.github.zxh.classpy.dexfile.index.UShortTypeIdIndex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class MethodIdItem extends DexComponent {

    private UShortTypeIdIndex classIdx;
    private UShort protoIdx; // todo
    private UIntStringIdIndex nameIdx;

    public UShort getClassIdx() {return classIdx;}
    public UShort getProtoIdx() {return protoIdx;}
    public UInt getNameIdx() {return nameIdx;}
    
    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUShortTypeIdIndex();
        protoIdx = reader.readUShort();
        nameIdx = reader.readUIntStringIdIndex();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        super.postRead(dexFile);
        setDesc(dexFile.getString(nameIdx));
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classIdx, protoIdx, nameIdx);
    }
    
}
