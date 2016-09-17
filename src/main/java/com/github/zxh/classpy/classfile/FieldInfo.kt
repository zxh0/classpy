package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
open class FieldInfo : ClassComponent() {

    init {
        u2   ("access_flags");
        u2cp ("name_index");
        u2cp ("descriptor_index");
        u2   ("attributes_count");
        table("attributes", AttributeInfo::class.java);
    }

    override fun afterRead(cp: ConstantPool) {
        val nameIndex = super.getInt("name_index");
        if (nameIndex > 0) {
            // todo fix loading java.lang.String from rt.jar
            desc = cp.getUtf8String(nameIndex);
        }

        AccessFlags.describeFieldFlags(
                super.get("access_flags") as U2);
    }
    
}
