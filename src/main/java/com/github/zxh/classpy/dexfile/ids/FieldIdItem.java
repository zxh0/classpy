package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import com.github.zxh.classpy.dexfile.UShort;
import com.github.zxh.classpy.dexfile.index.UIntStringIndex;
import com.github.zxh.classpy.dexfile.index.UShortTypeIdIndex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class FieldIdItem extends DexComponent {

    private UShortTypeIdIndex classIdx;
    private UShortTypeIdIndex typeIdx;
    private UIntStringIndex nameIdx;

    public UShort getClassIdx() {return classIdx;}
    public UShort getTypeIdx() {return typeIdx;}
    public UInt getNameIdx() {return nameIdx;}
    
    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUShortTypeIdIndex();
        typeIdx = reader.readUShortTypeIdIndex();
        nameIdx = reader.readUIntStringIndex();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        super.postRead(dexFile);
        setDesc(dexFile.getString(nameIdx));
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classIdx, typeIdx, nameIdx);
    }
    
}
