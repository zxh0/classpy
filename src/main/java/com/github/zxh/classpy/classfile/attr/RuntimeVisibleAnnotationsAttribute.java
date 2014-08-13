package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1;
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
        annotations = reader.readArray(AnnotationInfo.class,
                numAnnotations.getValue());
    }
    
    public static class AnnotationInfo extends ClassComponent {
    
        private U2 typeIndex;
        private U2 numElementValuePairs;
        private ElementValuePair[] elementValuePairs;
    
        @Override
        protected void readContent(ClassReader reader) {
            typeIndex = reader.readU2();
            numElementValuePairs = reader.readU2();
            elementValuePairs = reader.readArray(ElementValuePair.class,
                    numElementValuePairs.getValue());
        }
        
    }
    
    public static class ElementValuePair extends ClassComponent {
        
        private U2 elementNameIndex;
        private ElementValue value;

        @Override
        protected void readContent(ClassReader reader) {
            elementNameIndex = reader.readU2();
            value = new ElementValue();
            value.read(reader);
        }
        
    }
    
    public static class ElementValue extends ClassComponent {

        private U1 tag;
        
        // tag=B,C,D,F,I,J,S,Z,s
        private U2 constValueIndex;

        // tag=e
        // enum_const_value;
        private U2 typeNameIndex;
        private U2 constNameIndex;

        // tag=c
        private U2 classInfoIndex;

        // tag=@
        private AnnotationInfo annotationValue;

        // tag=[
        // array_value;
        private U2 numValues;
        private ElementValue values[];
        
        @Override
        protected void readContent(ClassReader reader) {
            tag = reader.readU1();
            switch (tag.getValue()) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                case 's': 
                    constValueIndex = reader.readU2();
                    break;
                case 'e': 
                    typeNameIndex = reader.readU2();
                    constNameIndex = reader.readU2();
                    break;
                case 'c':
                    classInfoIndex = reader.readU2();
                    break;
                case '[':
                    numValues = reader.readU2();
                    values = reader.readArray(ElementValue.class, numValues.getValue());
                    break;
                // todo
            }
        }
        
    }
    
}
