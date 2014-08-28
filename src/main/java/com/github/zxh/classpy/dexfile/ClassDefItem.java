package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.ids.TypeIdItem;
import com.github.zxh.classpy.dexfile.index.UIntTypeIdIndex;
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
    private UInt interfacesOff; // todo
    private UInt sourceFileIdx; // todo
    private UInt annotationsOff; // todo
    private UInt classDataOff; // todo
    private UInt staticValuesOff; // todo

    public UInt getClassDataOff() {return classDataOff;}
    public UInt getInterfacesOff() {return interfacesOff;}

    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUIntTypeIdIndex();
        accessFlags = reader.readUInt();
        superclassIdx = reader.readUIntTypeIdIndex();
        interfacesOff = reader.readUInt();
        sourceFileIdx = reader.readUInt();
        annotationsOff = reader.readUInt();
        classDataOff = reader.readUInt();
        staticValuesOff = reader.readUInt();
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
