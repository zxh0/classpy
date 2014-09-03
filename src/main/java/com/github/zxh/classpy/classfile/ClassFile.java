package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.common.java.AccessFlags;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U4Hex;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import com.github.zxh.classpy.classfile.constant.ConstantPool;

/*
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
*/
public class ClassFile extends ClassComponent {
    
    private U4Hex magic;
    private U2 minorVersion;
    private U2 majorVersion;
    private U2 constantPoolCount;
    private ConstantPool constantPool;
    private U2 accessFlags;
    private U2CpIndex thisClass;
    private U2CpIndex superClass;
    private U2 interfacesCount;
    private Table<U2CpIndex> interfaces;
    private U2 fieldsCount;
    private Table<FieldInfo> fields;
    private U2 methodsCount;
    private Table<MethodInfo> methods;
    private U2 attributesCount;
    private Table<AttributeInfo> attributes;
    
    // Getters & Setters
    public U4Hex getMagic() {return magic;}
    public U2 getMinorVersion() {return minorVersion;}
    public U2 getMajorVersion() {return majorVersion;}
    public U2 getConstantPoolCount() {return constantPoolCount;}
    public U2 getInterfacesCount() {return interfacesCount;}
    public U2 getFieldsCount() {return fieldsCount;}
    public U2 getMethodsCount() {return methodsCount;}
    public U2 getAttributesCount() {return attributesCount;}
    
    @Override
    protected void readContent(ClassReader reader) {
        magic = reader.readU4Hex();
        minorVersion = reader.readU2();
        majorVersion = reader.readU2();
        constantPoolCount = reader.readU2();
        constantPool = reader.readConstantPool(constantPoolCount.getValue());
        accessFlags = reader.readU2();
        AccessFlags.describeClassFlags(accessFlags);
        thisClass = reader.readU2CpIndex();
        superClass = reader.readU2CpIndex();
        interfacesCount = reader.readU2();
        interfaces = reader.readTable(U2CpIndex.class, interfacesCount);
        fieldsCount = reader.readU2();
        fields = reader.readTable(FieldInfo.class, fieldsCount);
        methodsCount = reader.readU2();
        methods = reader.readTable(MethodInfo.class, methodsCount);
        attributesCount = reader.readU2();
        attributes = reader.readTable(AttributeInfo.class, attributesCount);
        //setName("ClassFile"); // todo
    }
    
}
