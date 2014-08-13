package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attr.AttributeInfo;
import com.github.zxh.classpy.classfile.cp.ConstantPool;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

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
    private Table<U2> interfaces;
    private U2 fieldsCount;
    private Table<FieldInfo> fields;
    private U2 methodsCount;
    private Table<MethodInfo> methods;
    private U2 attributesCount;
    private Table<AttributeInfo> attributes;
    
    public void read(ByteBuffer buf) {
        ClassReader reader = new ClassReader(buf);
        magic = reader.readU4();
        minorVersion = reader.readU2();
        majorVersion = reader.readU2();
        readConstantPool(reader);
        accessFlags = reader.readU2();
        thisClass = reader.readU2();
        superClass = reader.readU2();
        readInterfaces(reader);
        readFields(reader);
        readMethods(reader);
        readAttributes(reader);
    }
    
    private void readConstantPool(ClassReader reader) {
        constantPoolCount = reader.readU2();
        constantPool = new ConstantPool(constantPoolCount.getValue());
        constantPool.read(reader);
        reader.setConstantPool(constantPool);
    }
    
    private void readInterfaces(ClassReader reader) {
        interfacesCount = reader.readU2();
        interfaces = reader.readTable(U2.class, interfacesCount.getValue());
    }
    
    private void readFields(ClassReader reader) {
        fieldsCount = reader.readU2();
        fields = reader.readTable(FieldInfo.class, fieldsCount.getValue());
    }
    
    private void readMethods(ClassReader reader) {
        methodsCount = reader.readU2();
        methods = reader.readTable(MethodInfo.class, methodsCount.getValue());
    }
    
    private void readAttributes(ClassReader reader) {
        attributesCount = reader.readU2();
        attributes = reader.readTable(AttributeInfo.class, attributesCount.getValue());
    }
    
    public U4 getMagic() {return magic;}
    public U2 getMinorVersion() {return minorVersion;}
    public U2 getMajorVersion() {return majorVersion;}
    public U2 getConstantPoolCount() {return constantPoolCount;}
    public U2 getInterfacesCount() {return interfacesCount;}
    public U2 getFieldsCount() {return fieldsCount;}
    public U2 getMethodsCount() {return methodsCount;}

    
    public static ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(ByteBuffer.wrap(bytes));
        try {
            xxNames(cf);
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
        
        return cf;
    }

    // todo
    private static void xxNames(Object obj) throws ReflectiveOperationException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (ClassComponent.class.isAssignableFrom(field.getType())) {
                ClassComponent fieldVal = (ClassComponent) field.get(obj);
                if (fieldVal != null) {
                    fieldVal.setName(field.getName());
                    xxNames(fieldVal);
                }
            }
        }
    }
    
}
