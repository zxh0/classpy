package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
class MethodInfo : FieldInfo() {
    
    override fun afterRead(cp: ConstantPool) {
        super.afterRead(cp);
        AccessFlags.describeMethodFlags(
                super.get("access_flags") as U2);
    }
    
}
