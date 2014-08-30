package com.github.zxh.classpy.dexfile.body.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UShort;
import com.github.zxh.classpy.dexfile.datatype.UIntStringIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UShortTypeIdIndex;

/**
 *
 * @author zxh
 */
public class FieldIdItem extends DexComponent {

    private UShortTypeIdIndex classIdx;
    private UShortTypeIdIndex typeIdx;
    private UIntStringIdIndex nameIdx;

    public UShort getClassIdx() {return classIdx;}
    public UShort getTypeIdx() {return typeIdx;}
    public UInt getNameIdx() {return nameIdx;}
    
    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUShortTypeIdIndex();
        typeIdx = reader.readUShortTypeIdIndex();
        nameIdx = reader.readUIntStringIdIndex();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        super.postRead(dexFile);
        setDesc(dexFile.getString(nameIdx));
    }
    
}
