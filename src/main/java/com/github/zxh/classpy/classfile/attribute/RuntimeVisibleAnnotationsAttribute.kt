package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
RuntimeVisibleAnnotations_attribute {
    u2         attribute_name_index;
    u4         attribute_length;
    u2         num_annotations;
    annotation annotations[num_annotations];
}
 */
class RuntimeVisibleAnnotationsAttribute : AttributeInfo() {

    init {
        u2   ("num_annotations");
        table("annotations", AnnotationInfo::class.java);
    }
    
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
class AnnotationInfo : ClassComponent() {

    init {
        u2cp ("type_index");
        u2   ("num_element_value_pairs");
        table("element_value_pairs", ElementValuePair::class.java);
    }


    override fun afterRead(cp: ConstantPool) {
        val typeIndex = super.getInt("type_index")
        setDesc(cp.getUtf8String(typeIndex));
    }

}

class ElementValuePair : ClassComponent() {

    init {
        u2cp("element_name_index");
        add ("value", ElementValue());
    }

    override fun afterRead(cp: ConstantPool) {
        val elementNameIndex = super.getInt("element_name_index");
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
class ElementValue : ClassComponent() {

    override fun readContent(reader: ClassReader) {
        val tag = reader.getByte(reader.position);
        preRead(tag);
        super.readContent(reader);
    }

    private fun preRead(tag: Byte) {
        u1("tag");
        when (tag.toChar()) {
            'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z', 's' -> u2cp("const_value_index");
            'e' -> add("enum_const_value", EnumConstValue());
            'c' -> u2cp("class_info_index");
            '@' -> add("annotation_value", AnnotationInfo());
            '[' -> add("array_value", ArrayValue());
            else -> throw ClassParseException("Invalid element_value tag: " + tag);
        }
    }

    override fun  afterRead(cp: ConstantPool) {
        val tag = super.get("tag") as U1;
        tag.desc = tag.value.toChar().toString();
    }

}

class EnumConstValue : ClassComponent() {

    init {
        u2cp("type_name_index");
        u2cp("const_name_index");
    }

}

class ArrayValue : ClassComponent() {

    init {
        u2   ("num_values");
        table("values", ElementValue::class.java);
    }

}