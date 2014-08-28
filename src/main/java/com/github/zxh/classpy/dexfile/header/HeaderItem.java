package com.github.zxh.classpy.dexfile.header;

import com.github.zxh.classpy.dexfile.datatype.Hex;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class HeaderItem extends DexComponent {

    private FileMagic magic;
    private UInt checksum;
    private Hex signature;
    private UInt fileSize;
    private UInt headerSize;
    private UIntHex endianTag;
    private UInt linkSize;
    private UIntHex linkOff;
    private UIntHex mapOff;
    private UInt stringIdsSize;
    private UIntHex stringIdsOff;
    private UInt typeIdsSize;
    private UIntHex typeIdsOff;
    private UInt protoIdsSize;
    private UIntHex protoIdsOff;
    private UInt fieldIdsSize;
    private UIntHex fieldIdsOff;
    private UInt methodIdsSize;
    private UIntHex methodIdsOff;
    private UInt classDefsSize;
    private UIntHex classDefsOff;
    private UInt dataSize;
    private UIntHex dataOff;

    // Getters
    public UIntHex getMapOff() {return mapOff;}
    public UInt getStringIdsSize() {return stringIdsSize;}
    public UIntHex getStringIdsOff() {return stringIdsOff;}
    public UInt getTypeIdsSize() {return typeIdsSize;}
    public UIntHex getTypeIdsOff() {return typeIdsOff;}
    public UInt getProtoIdsSize() {return protoIdsSize;}
    public UIntHex getProtoIdsOff() {return protoIdsOff;}
    public UInt getFieldIdsSize() {return fieldIdsSize;}
    public UIntHex getFieldIdsOff() {return fieldIdsOff;}
    public UInt getMethodIdsSize() {return methodIdsSize;}
    public UIntHex getMethodIdsOff() {return methodIdsOff;}
    public UInt getClassDefsSize() {return classDefsSize;}
    public UIntHex getClassDefsOff() {return classDefsOff;}
    
    @Override
    protected void readContent(DexReader reader) {
        magic = new FileMagic();
        magic.read(reader);
        checksum = reader.readUInt();
        signature = reader.readHex(20);
        fileSize = reader.readUInt();
        headerSize = reader.readUInt();
        endianTag = reader.readUIntHex();
        linkSize = reader.readUInt();
        linkOff = reader.readUIntHex();
        mapOff = reader.readUIntHex();
        stringIdsSize = reader.readUInt();
        stringIdsOff = reader.readUIntHex();
        typeIdsSize = reader.readUInt();
        typeIdsOff = reader.readUIntHex();
        protoIdsSize = reader.readUInt();
        protoIdsOff = reader.readUIntHex();
        fieldIdsSize = reader.readUInt();
        fieldIdsOff = reader.readUIntHex();
        methodIdsSize = reader.readUInt();
        methodIdsOff = reader.readUIntHex();
        classDefsSize = reader.readUInt();
        classDefsOff = reader.readUIntHex();
        dataSize = reader.readUInt();
        dataOff = reader.readUIntHex();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(magic, checksum, signature,
                fileSize, headerSize, endianTag, linkSize, linkOff, mapOff,
                stringIdsSize, stringIdsOff, typeIdsSize, typeIdsOff,
                protoIdsSize, protoIdsOff, fieldIdsSize, fieldIdsOff,
                methodIdsSize, methodIdsOff, classDefsSize, classDefsOff,
                dataSize, dataOff);
    }
    
}
