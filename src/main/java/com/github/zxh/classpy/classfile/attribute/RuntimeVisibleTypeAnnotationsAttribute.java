package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.ClassParseException;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.attribute.RuntimeVisibleAnnotationsAttribute.AnnotationInfo;
import com.github.zxh.classpy.Util;

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
    private Table<TypeAnnotationInfo> annotations;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numAnnotations = reader.readU2();
        annotations = reader.readTable(TypeAnnotationInfo.class, numAnnotations);
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
    public static class TypeAnnotationInfo extends ClassComponent {

        private U1 targetType;
        private TargetInfo targetInfo;
        private TypePath targetPath;
        private AnnotationInfo annotation;
        
        @Override
        protected void readContent(ClassReader reader) {
            targetType = reader.readU1();
            targetType.setDesc(Util.toHexString(targetType.getValue()));
            targetInfo = new TargetInfo(targetType.getValue());
            targetInfo.read(reader);
            targetPath = new TypePath();
            targetPath.read(reader);
            annotation = new AnnotationInfo();
            annotation.read(reader);
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
    public static class TargetInfo extends ClassComponent {

        private final int targetType;
        private U1 typeParameterIndex; // type_parameter_target & type_parameter_bound_target
        private U2 supertypeIndex; // supertype_target
        private U1 boundIndex; // type_parameter_bound_target
        private U1 formalParameterIndex; // formal_parameter_target
        private U2 throwsTypeIndex; // throws_target
        private U2 tableLength; // localvar_target
        private Table<LocalVarInfo> table; // localvar_target
        private U2 exceptionTableIndex; // catch_target
        private U2 offset; // offset_target & type_argument_target
        private U1 typeArgumentIndex; // type_argument_target

        public TargetInfo(int targetType) {
            this.targetType = targetType;
        }
        
        @Override
        protected void readContent(ClassReader reader) {
            switch (targetType) {
                case 0x00:
                case 0x01:
                    typeParameterIndex = reader.readU1();
                    break;
                case 0x10:
                    supertypeIndex = reader.readU2();
                    break;
                case 0x11:
                case 0x12:
                    typeParameterIndex = reader.readU1();
                    boundIndex = reader.readU1();
                    break;
                case 0x13:
                case 0x14:
                case 0x15:
                    break;
                case 0x16:
                    formalParameterIndex = reader.readU1();
                    break;
                case 0x17:
                    throwsTypeIndex = reader.readU2();
                    break;
                case 0x40:
                case 0x41:
                    tableLength = reader.readU2();
                    table = reader.readTable(LocalVarInfo.class, tableLength);
                    break;
                case 0x42:
                    exceptionTableIndex = reader.readU2();
                    break;
                case 0x43:
                case 0x44:
                case 0x45:
                case 0x46:
                    offset = reader.readU2();
                    break;
                case 0x47:
                case 0x48:
                case 0x49:
                case 0x4A:
                case 0x4B:
                    offset = reader.readU2();
                    typeArgumentIndex = reader.readU1();
                    break;
                default: throw new ClassParseException("Invalid target_type: " + targetType);
            }
        }
        
    }
    
    public static class LocalVarInfo extends ClassComponent {
        
        private U2 startPc;
        private U2 length;
        private U2 index;
        
        @Override
        protected void readContent(ClassReader reader) {
            startPc = reader.readU2();
            length = reader.readU2();
            index = reader.readU2();
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
    public static class TypePath extends ClassComponent {

        private U1 pathLength;
        private Table<PathInfo> path;
        
        @Override
        protected void readContent(ClassReader reader) {
            pathLength = reader.readU1();
            path = reader.readTable(PathInfo.class, pathLength);
        }
        
    }
    
    public static class PathInfo extends ClassComponent {

        private U1 typePathKind;
        private U1 typeArgumentIndex;
        
        @Override
        protected void readContent(ClassReader reader) {
            typePathKind = reader.readU1();
            typeArgumentIndex = reader.readU1();
        }
        
    }
    
}
