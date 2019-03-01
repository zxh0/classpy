package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassFilePart;
import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.ClassFileReader;
import com.github.zxh.classpy.classfile.datatype.U1;

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
    public static class AnnotationInfo extends ClassFilePart {

        {
            u2cp ("type_index");
            u2   ("num_element_value_pairs");
            table("element_value_pairs", ElementValuePair.class);
        }

        
        @Override
        protected void postRead(ConstantPool cp) {
            int typeIndex = super.getUInt("type_index");
            setDesc(cp.getUtf8String(typeIndex));
        }
        
    }
    
    public static class ElementValuePair extends ClassFilePart {

        {
            u2cp("element_name_index");
            add ("value", new ElementValue());
        }

        @Override
        protected void postRead(ConstantPool cp) {
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
    public static class ElementValue extends ClassFilePart {
        
        @Override
        protected void readContent(ClassFileReader reader) {
            byte tag = reader.getByte(reader.getPosition());
            preRead(tag);
            super.readContent(reader);
        }

        private void preRead(byte tag) {
            u1("tag");
            switch (tag) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                case 's':
                    u2cp("const_value_index");
                    break;
                case 'e':
                    add("enum_const_value", new EnumConstValue());
                    break;
                case 'c':
                    u2cp("class_info_index");
                    break;
                case '@':
                    add("annotation_value", new AnnotationInfo());
                    break;
                case '[':
                    add("array_value", new ArrayValue());
                    break;
                default: throw new ParseException("Invalid element_value tag: " + tag);
            }
        }

        @Override
        protected void postRead(ConstantPool cp) {
            U1 tag = (U1) super.get("tag");
            tag.setDesc(Character.toString((char) tag.getValue()));
        }

    }
    
    public static class EnumConstValue extends ClassFilePart {

        {
            u2cp("type_name_index");
            u2cp("const_name_index");
        }
        
    }
    
    public static class ArrayValue extends ClassFilePart {

        {
            u2   ("num_values");
            table("values", ElementValue.class);
        }
        
    }
    
}
