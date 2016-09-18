package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

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
class ClassFile : ClassComponent() {

    init {
        var cpCount = U2();

        u4hex("magic");
        u2   ("minor_version");
        u2   ("major_version");
        add  ("constant_pool_count", cpCount);
        add  ("constant_pool", ConstantPool(cpCount));
        u2   ("access_flags");
        u2cp ("this_class");
        u2cp ("super_class");
        u2   ("interfaces_count");
        table("interfaces", U2CpIndex::class.java);
        u2   ("fields_count");
        table("fields", FieldInfo::class.java);
        u2   ("methods_count");
        table("methods", MethodInfo::class.java);
        u2   ("attributes_count");
        table("attributes", AttributeInfo::class.java);
    }

    fun getConstantPool(): ConstantPool {
        return super.get("constant_pool") as ConstantPool;
    }

    override fun afterRead(cp: ConstantPool) {
        AccessFlags.describeClassFlags(
                super.get("access_flags") as U2);
    }

}
