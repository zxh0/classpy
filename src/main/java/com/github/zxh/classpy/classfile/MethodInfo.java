package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.common.java.AccessFlags;

/*
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class MethodInfo extends FieldInfo {
    
    @Override
    protected void afterRead() {
        AccessFlags.describeMethodFlags(accessFlags);
    }
    
}
