package com.github.zxh.classpy.dexfile.body.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UShort;
import com.github.zxh.classpy.dexfile.datatype.UIntStringIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UShortProtoIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UShortTypeIdIndex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class MethodIdItem extends DexComponent {

    private UShortTypeIdIndex classIdx;
    private UShortProtoIdIndex protoIdx;
    private UIntStringIdIndex nameIdx;

    public UShort getClassIdx() {return classIdx;}
    public UShort getProtoIdx() {return protoIdx;}
    public UInt getNameIdx() {return nameIdx;}
    
    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUShortTypeIdIndex();
        protoIdx = reader.readUShortProtoIdIndex();
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
