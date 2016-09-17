package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.AccessFlags;
import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
InnerClasses_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_classes;
    {   u2 inner_class_info_index;
        u2 outer_class_info_index;
        u2 inner_name_index;
        u2 inner_class_access_flags;
    } classes[number_of_classes];
}
 */
class InnerClassesAttribute : AttributeInfo() {

    init {
        u2   ("number_of_classes");
        table("classes", InnerClassInfo::class.java);
    }
    
}

class InnerClassInfo : ClassComponent() {

    init {
        u2cp("inner_class_info_index");
        u2cp("outer_class_info_index");
        u2cp("inner_name_index");
        u2  ("inner_class_access_flags");
    }

    override fun afterRead(cp: ConstantPool) {
        AccessFlags.describeInnerClassFlags(
                super.get("inner_class_access_flags") as U2);
    }

}
