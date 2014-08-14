package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attr.AttributeInfo;
import com.github.zxh.classpy.classfile.cp.ConstantPool;
import java.util.Arrays;
import java.util.List;

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
    
    private U4 magic;
    private U2 minorVersion;
    private U2 majorVersion;
    private U2 constantPoolCount;
    private ConstantPool constantPool;
    private U2 accessFlags;
    private U2 thisClass;
    private U2 superClass;
    private U2 interfacesCount;
    private Table<U2> interfaces;
    private U2 fieldsCount;
    private Table<FieldInfo> fields;
    private U2 methodsCount;
    private Table<MethodInfo> methods;
    private U2 attributesCount;
    private Table<AttributeInfo> attributes;
    
    public U4 getMagic() {return magic;}
    public U2 getMinorVersion() {return minorVersion;}
    public U2 getMajorVersion() {return majorVersion;}
    public U2 getConstantPoolCount() {return constantPoolCount;}
    public U2 getAccessFlags() {return accessFlags;}
    public U2 getThisClass() {return thisClass;}
    public U2 getSuperClass() {return superClass;}
    public U2 getInterfacesCount() {return interfacesCount;}
    public U2 getFieldsCount() {return fieldsCount;}
    public U2 getMethodsCount() {return methodsCount;}
    public U2 getAttributesCount() {return attributesCount;}
    
    @Override
    protected void readContent(ClassReader reader) {
        magic = reader.readU4();
        magic.useHexDesc();
        minorVersion = reader.readU2();
        majorVersion = reader.readU2();
        constantPoolCount = reader.readU2();
        constantPool = reader.readConstantPool(constantPoolCount.getValue());
        accessFlags = reader.readU2();
        thisClass = reader.readU2();
        superClass = reader.readU2();
        interfacesCount = reader.readU2();
        interfaces = reader.readTable(U2.class, interfacesCount.getValue());
        fieldsCount = reader.readU2();
        fields = reader.readTable(FieldInfo.class, fieldsCount.getValue());
        methodsCount = reader.readU2();
        methods = reader.readTable(MethodInfo.class, methodsCount.getValue());
        attributesCount = reader.readU2();
        attributes = reader.readTable(AttributeInfo.class, attributesCount.getValue());
        
        // todo
        interfaces.getSubComponents().forEach(u2 -> {
            u2.setDesc(constantPool.getClassInfo(u2.getValue()).getDesc());
        });
    }

    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(magic,
                minorVersion, majorVersion,
                constantPoolCount, constantPool,
                accessFlags, thisClass, superClass,
                interfacesCount, interfaces,
                fieldsCount, fields,
                methodsCount, methods,
                attributesCount, attributes);
    }
    
    @Override
    public String toString() {
        return "ClassFile"; // todo
    }
    
}
