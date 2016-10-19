package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.classfile.jvm.AccessFlagType;

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

    {
        U2 cpCount = new U2();

        u4hex("magic");
        u2   ("minor_version");
        u2   ("major_version");
        add  ("constant_pool_count", cpCount);
        add  ("constant_pool", new ConstantPool(cpCount));
        u2af ("access_flags", AccessFlagType.AF_CLASS);
        u2cp ("this_class");
        u2cp ("super_class");
        u2   ("interfaces_count");
        table("interfaces", U2CpIndex.class);
        u2   ("fields_count");
        table("fields", FieldInfo.class);
        u2   ("methods_count");
        table("methods", MethodInfo.class);
        u2   ("attributes_count");
        table("attributes", AttributeInfo.class);
    }

    public ConstantPool getConstantPool() {
        return (ConstantPool) super.get("constant_pool");
    }

}
