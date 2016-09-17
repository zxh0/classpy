package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.datatype.U1Hex;
import com.github.zxh.classpy.classfile.datatype.IntType;
import com.github.zxh.classpy.classfile.reader.ClassReader;

/*
RuntimeVisibleTypeAnnotations_attribute {
    u2              attribute_name_index;
    u4              attribute_length;
    u2              num_annotations;
    type_annotation annotations[num_annotations];
}
 */
class RuntimeVisibleTypeAnnotationsAttribute : AttributeInfo() {

    init {
        u2   ("num_annotations");
        table("annotations", TypeAnnotationInfo::class.java);
    }
    
}

/*
type_annotation {
    u1 target_type;
    union {
        type_parameter_target;
        supertype_target;
        type_parameter_bound_target;
        empty_target;
        method_formal_parameter_target;
        throws_target;
        localvar_target;
        catch_target;
        offset_target;
        type_argument_target;
    } target_info;
    type_path target_path;
    u2        type_index;
    u2        num_element_value_pairs;
    {   u2            element_name_index;
        element_value value;
    } element_value_pairs[num_element_value_pairs];
}
*/
class TypeAnnotationInfo : ClassComponent() {

    init {
        val targetType = U1Hex();

        add("target_type", targetType);
        add("target_info", TargetInfo(targetType));
        add("target_path", TypePath());
        add("annotation", AnnotationInfo());
    }

}

/*
type_parameter_target {
    u1 type_parameter_index;
}
supertype_target {
    u2 supertype_index;
}
type_parameter_bound_target {
    u1 type_parameter_index;
    u1 bound_index;
}
empty_target {
}
formal_parameter_target {
    u1 formal_parameter_index;
}
throws_target {
    u2 throws_type_index;
}
localvar_target {
    u2 table_length;
    {   u2 start_pc;
        u2 length;
        u2 index;
    } table[table_length];
}
catch_target {
    u2 exception_table_index;
}
offset_target {
    u2 offset;
}
type_argument_target {
    u2 offset;
    u1 type_argument_index;
}
*/
class TargetInfo(val targetType: IntType) : ClassComponent() {

    override fun readContent(reader: ClassReader) {
        when (targetType.value) {
            0x00,
            0x01 -> u1("typeParameterIndex");
            0x10 -> u2("supertypeIndex");
            0x11,
            0x12 -> {
                u1("typeParameterIndex");
                u1("boundIndex");
            }
            0x13,
            0x14,
            0x15 -> {} // todo
            0x16 -> u1("formalParameterIndex");
            0x17 -> u2("throwsTypeIndex");
            0x40,
            0x41 -> {
                u2("tableLength");
                table("table", LocalVarInfo::class.java);
            }
            0x42 -> u2("exceptionTableIndex");
            0x43,
            0x44,
            0x45,
            0x46 -> u2("offset");
            0x47,
            0x48,
            0x49,
            0x4A,
            0x4B -> {
                u2("offset");
                u1("typeArgumentIndex");
            }
            else -> throw ClassParseException("Invalid target_type: " + targetType.value);
        }
        super.readContent(reader);
    }

}

class LocalVarInfo : ClassComponent() {

    init {
        u2("start_pc");
        u2("length");
        u2("index");
    }

}

/*
type_path {
    u1 path_length;
    {   u1 type_path_kind;
        u1 type_argument_index;
    } path[path_length];
}
*/
class TypePath : ClassComponent() {

    init {
        u1   ("path_length");
        table("path", PathInfo::class.java);
    }

}

class PathInfo : ClassComponent() {

    init {
        u1("type_path_kind");
        u1("type_argument_index");
    }

}
