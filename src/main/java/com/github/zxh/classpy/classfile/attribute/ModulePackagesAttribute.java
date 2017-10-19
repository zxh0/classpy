package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
ModulePackages_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 package_count;
    u2 package_index[package_count];
}
 */
public class ModulePackagesAttribute extends AttributeInfo {

    {
        u2("package_count");
        table("package_index", U2CpIndex.class);
    }

}
