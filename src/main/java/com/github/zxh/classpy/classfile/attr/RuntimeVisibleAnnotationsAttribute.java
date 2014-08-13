package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
RuntimeVisibleAnnotations_attribute {
    u2         attribute_name_index;
    u4         attribute_length;
    u2         num_annotations;
    annotation annotations[num_annotations];
}
annotation {
    u2 type_index;
    u2 num_element_value_pairs;
    {   u2            element_name_index;
        element_value value;
    } element_value_pairs[num_element_value_pairs];
}
element_value {
    u1 tag;
    union {
        u2 const_value_index;

        {   u2 type_name_index;
            u2 const_name_index;
        } enum_const_value;

        u2 class_info_index;

        annotation annotation_value;

        {   u2            num_values;
            element_value values[num_values];
        } array_value;
    } value;
}
 */
public class RuntimeVisibleAnnotationsAttribute extends AttributeInfo {

    private U2 numAnnotations;
    private AnnotationInfo[] annotations;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numAnnotations = reader.readU2();
        annotations = new AnnotationInfo[numAnnotations.getValue()];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = new AnnotationInfo();
            annotations[i].read(reader);
        }
    }
    
    public static class AnnotationInfo extends ClassComponent {
    
        private U2 typeIndex;
        private U2 numElementValuePairs;
        private ElementValuePair[] elementValuePairs;
    
        @Override
        protected void readContent(ClassReader reader) {
            typeIndex = reader.readU2();
            numElementValuePairs = reader.readU2();
//            elementValuePairs
        }
        
    }
    
    public static class ElementValuePair extends ClassComponent {

        @Override
        protected void readContent(ClassReader reader) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
