package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
RuntimeVisibleTypeAnnotations_attribute {
    u2              attribute_name_index;
    u4              attribute_length;
    u2              num_annotations;
    type_annotation annotations[num_annotations];
}
 */
public class RuntimeVisibleTypeAnnotationsAttribute extends AttributeInfo {

    private U2 numAnnotations;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numAnnotations = reader.readU2();
    }
    
}
