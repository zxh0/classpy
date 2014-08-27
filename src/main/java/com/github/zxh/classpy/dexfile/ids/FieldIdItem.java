package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import com.github.zxh.classpy.dexfile.UShort;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class FieldIdItem extends DexComponent {

    private UShort classIdx;
    private UShort typeIdx;
    private UInt nameIdx;

    public UShort getClassIdx() {return classIdx;}
    public UShort getTypeIdx() {return typeIdx;}
    public UInt getNameIdx() {return nameIdx;}
    
    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUShort();
        typeIdx = reader.readUShort();
        nameIdx = reader.readUInt();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classIdx, typeIdx, nameIdx);
    }
    
}
