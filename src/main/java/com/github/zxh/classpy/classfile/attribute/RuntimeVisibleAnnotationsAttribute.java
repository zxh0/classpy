package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
RuntimeVisibleAnnotations_attribute {
    u2         attribute_name_index;
    u4         attribute_length;
    u2         num_annotations;
    annotation annotations[num_annotations];
}
 */
public class RuntimeVisibleAnnotationsAttribute extends AttributeInfo {

    {
        u2   ("num_annotations");
        table("annotations", AnnotationInfo.class);
    }
    
    /*
    annotation {
        u2 type_index;
        u2 num_element_value_pairs;
        {   u2            element_name_index;
            element_value value;
        } element_value_pairs[num_element_value_pairs];
    }
    */
    public static class AnnotationInfo extends ClassComponent {

        {
            u2cp ("type_index");
            u2   ("num_element_value_pairs");
            table("element_value_pairs", ElementValuePair.class);
        }

        
        @Override
        protected void afterRead(ConstantPool cp) {
            int typeIndex = ((U2) super.get("type_index")).getValue();
            setDesc(cp.getUtf8String(typeIndex));
        }
        
    }
    
    public static class ElementValuePair extends ClassComponent {

        {
            u2cp("element_name_index");
            add ("value", new ElementValue());
        }

        @Override
        protected void afterRead(ConstantPool cp) {
            int elementNameIndex = super.getUInt("element_name_index");
            setDesc(cp.getUtf8String(elementNameIndex));
        }
        
    }
    
    /*
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
    public static class ElementValue extends ClassComponent {

        private U1 tag;
        
        // tag=B,C,D,F,I,J,S,Z,s
        private U2CpIndex constValueIndex;

        // tag=e
        private EnumConstValue enumConstValue;

        // tag=c
        private U2CpIndex classInfoIndex;

        // tag=@
        private AnnotationInfo annotationValue;

        // tag=[
        private ArrayValue arrayValue;
        
        @Override
        protected void readContent(ClassReader reader) {
            tag = reader.readU1();
            tag.setDesc(Character.toString((char) tag.getValue()));
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
                    constValueIndex = reader.readU2CpIndex();
                    break;
                case 'e': 
                    enumConstValue = new EnumConstValue();
                    enumConstValue.read(reader);
                    break;
                case 'c':
                    classInfoIndex = reader.readU2CpIndex();
                    break;
                case '@':
                    annotationValue = new AnnotationInfo();
                    annotationValue.read(reader);
                    break;
                case '[':
                    arrayValue = new ArrayValue();
                    arrayValue.read(reader);
                    break;
                default: throw new ClassParseException("Invalid element_value tag: " + tag.getDesc());
            }
        }
        
    }
    
    public static class EnumConstValue extends ClassComponent {

        {
            u2cp("type_name_index");
            u2cp("const_name_index");
        }
        
    }
    
    public static class ArrayValue extends  ClassComponent {

        {
            u2   ("num_values");
            table("values", ElementValue.class);
        }
        
    }
    
}
