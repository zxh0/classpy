package com.github.zxh.classpy.dexfile;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ClassDefItem extends DexComponent {

    private UInt classIdx;
    private UInt accessFlags;
    private UInt superclassIdx;
    private UInt interfacesOff;
    private UInt sourceFileIdx;
    private UInt annotationsOff;
    private UInt classDataOff;
    private UInt staticValuesOff;

    public UInt getClassDataOff() {
        return classDataOff;
    }

    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUInt();
        accessFlags = reader.readUInt();
        superclassIdx = reader.readUInt();
        interfacesOff = reader.readUInt();
        sourceFileIdx = reader.readUInt();
        annotationsOff = reader.readUInt();
        classDataOff = reader.readUInt();
        staticValuesOff = reader.readUInt();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classIdx, accessFlags, superclassIdx,
                interfacesOff, sourceFileIdx, annotationsOff, classDataOff,
                staticValuesOff);
    }
    
}
