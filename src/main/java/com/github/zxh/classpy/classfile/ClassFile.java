package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.cp.ConstantPool;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author zxh
 */
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
public class ClassFile {
    
    private U4 magic;
    private U2 minorVersion;
    private U2 majorVersion;
    private U2 constantPoolCount;
    private ConstantPool constantPool;
    private U2 accessFlags;
    private U2 thisClass;
    private U2 superClass;
    private U2 interfacesCount;
    private List<U2> interfaces = new ArrayList<>();
    private U2 fieldsCount;
    private List<FieldInfo> fields = new ArrayList<>();
    private U2 methodsCount;
    private List<MethodInfo> methods = new ArrayList<>();
    private U2 attributesCount;
//    attribute_info attributes[attributes_count];
    
    public void read(ByteBuffer buf) {
        ClassReader reader = new ClassReader(buf);
        magic = reader.readU4();
        minorVersion = reader.readU2();
        majorVersion = reader.readU2();
        constantPoolCount = reader.readU2();
        constantPool = new ConstantPool(constantPoolCount.getValue());
        constantPool.read(reader);
        accessFlags = reader.readU2();
        thisClass = reader.readU2();
        superClass = reader.readU2();
        interfacesCount = reader.readU2();
        for (int i = 0; i < interfacesCount.getValue(); i++) {
            interfaces.add(reader.readU2());
        }
        fieldsCount = reader.readU2();
        // todo
        methodsCount = reader.readU2();
        // todo
        attributesCount = reader.readU2();
        // todo
    }
    
}
