package com.github.zxh.classpy.dexfile.header;

import com.github.zxh.classpy.dexfile.Hex;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
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
    private UInt endianTag;
    private UInt linkSize;
    private UInt linkOff;
    private UInt mapOff;
    private UInt stringIdsSize;
    private UInt stringIdsOff;
    private UInt typeIdsSize;
    private UInt typeIdsOff;
    private UInt protoIdsSize;
    private UInt protoIdsOff;
    private UInt fieldIdsSize;
    private UInt fieldIdsOff;
    private UInt methodIdsSize;
    private UInt methodIdsOff;
    private UInt classDefsSize;
    private UInt classDefsOff;
    private UInt dataSize;
    private UInt dataOff;

    // Getters
    public UInt getMapOff() {return mapOff;}
    public UInt getStringIdsSize() {return stringIdsSize;}
    public UInt getStringIdsOff() {return stringIdsOff;}
    public UInt getTypeIdsSize() {return typeIdsSize;}
    public UInt getTypeIdsOff() {return typeIdsOff;}
    public UInt getProtoIdsSize() {return protoIdsSize;}
    public UInt getProtoIdsOff() {return protoIdsOff;}
    public UInt getFieldIdsSize() {return fieldIdsSize;}
    public UInt getFieldIdsOff() {return fieldIdsOff;}
    public UInt getMethodIdsSize() {return methodIdsSize;}
    public UInt getMethodIdsOff() {return methodIdsOff;}
    public UInt getClassDefsSize() {return classDefsSize;}
    public UInt getClassDefsOff() {return classDefsOff;}
    
    @Override
    protected void readContent(DexReader reader) {
        magic = new FileMagic();
        magic.read(reader);
        checksum = reader.readUInt();
        signature = reader.readHex(20);
        fileSize = reader.readUInt();
        headerSize = reader.readUInt();
        endianTag = reader.readUInt();
        endianTag.setDesc(endianTag.toHexString());
        linkSize = reader.readUInt();
        linkOff = reader.readUInt();
        mapOff = reader.readUInt();
        stringIdsSize = reader.readUInt();
        stringIdsOff = reader.readUInt();
        typeIdsSize = reader.readUInt();
        typeIdsOff = reader.readUInt();
        protoIdsSize = reader.readUInt();
        protoIdsOff = reader.readUInt();
        fieldIdsSize = reader.readUInt();
        fieldIdsOff = reader.readUInt();
        methodIdsSize = reader.readUInt();
        methodIdsOff = reader.readUInt();
        classDefsSize = reader.readUInt();
        classDefsOff = reader.readUInt();
        dataSize = reader.readUInt();
        dataOff = reader.readUInt();
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
