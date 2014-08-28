package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.body.ids.TypeIdItem;
import com.github.zxh.classpy.dexfile.datatype.UIntTypeIdIndex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ClassDefItem extends DexComponent {

    private UIntTypeIdIndex classIdx;
    private UInt accessFlags; // todo
    private UIntTypeIdIndex superclassIdx;
    private UIntHex interfacesOff; // todo
    private UInt sourceFileIdx; // todo
    private UIntHex annotationsOff; // todo
    private UIntHex classDataOff; // todo
    private UIntHex staticValuesOff; // todo

    public UIntHex getClassDataOff() {return classDataOff;}
    public UIntHex getInterfacesOff() {return interfacesOff;}

    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUIntTypeIdIndex();
        accessFlags = reader.readUInt();
        superclassIdx = reader.readUIntTypeIdIndex();
        interfacesOff = reader.readUIntHex();
        sourceFileIdx = reader.readUInt();
        annotationsOff = reader.readUIntHex();
        classDataOff = reader.readUIntHex();
        staticValuesOff = reader.readUIntHex();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        super.postRead(dexFile);
        
        TypeIdItem typeId = dexFile.getTypeIdItem(classIdx);
        String typeDesc = dexFile.getString(typeId.getDescriptorIdx());
        setDesc(typeDesc);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classIdx, accessFlags, superclassIdx,
                interfacesOff, sourceFileIdx, annotationsOff, classDataOff,
                staticValuesOff);
    }
    
}
