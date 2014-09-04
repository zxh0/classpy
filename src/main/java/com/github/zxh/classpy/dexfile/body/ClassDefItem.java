package com.github.zxh.classpy.dexfile.body;

import com.github.zxh.classpy.common.java.AccessFlags;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.body.ids.TypeIdItem;
import com.github.zxh.classpy.dexfile.datatype.UIntStringIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UIntTypeIdIndex;

/**
 *
 * @author zxh
 */
public class ClassDefItem extends DexComponent {

    private UIntTypeIdIndex classIdx;
    private UInt accessFlags;
    private UIntTypeIdIndex superclassIdx;
    private UIntHex interfacesOff; // -> type_list
    private UIntStringIdIndex sourceFileIdx;
    private UIntHex annotationsOff; // -> annotations_directory_item
    private UIntHex classDataOff; // -> class_data_item
    private UIntHex staticValuesOff; // -> encoded_array_item

    // Getters
    public UIntHex getClassDataOff() {return classDataOff;}
    public UIntHex getInterfacesOff() {return interfacesOff;}
    public UIntHex getAnnotationsOff() {return annotationsOff;}
    public UIntHex getStaticValuesOff() {return staticValuesOff;}

    @Override
    protected void readContent(DexReader reader) {
        classIdx = reader.readUIntTypeIdIndex();
        accessFlags = reader.readUInt();
        AccessFlags.describeClassOrInnerClassFlags(accessFlags);
        superclassIdx = reader.readUIntTypeIdIndex();
        interfacesOff = reader.readUIntHex();
        sourceFileIdx = reader.readUIntStringIdIndex();
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

}
