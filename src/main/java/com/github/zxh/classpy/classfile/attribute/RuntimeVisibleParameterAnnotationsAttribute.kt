package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;

/*
RuntimeVisibleParameterAnnotations_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 num_parameters;
    {   u2         num_annotations;
        annotation annotations[num_annotations];
    } parameter_annotations[num_parameters];
}
 */
class RuntimeVisibleParameterAnnotationsAttribute : AttributeInfo() {

    init {
        u1   ("num_parameters");
        table("parameter_annotations", ParameterAnnotationInfo::class.java);
    }
    
}

class ParameterAnnotationInfo : ClassComponent() {

    init {
        u2   ("num_annotations");
        table("annotations", AnnotationInfo::class.java);
    }

}