package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.U1;
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
        private U2 typeIndex;
        private U2 numElementValuePairs;
        
        @Override
        protected void readContent(ClassReader reader) {
            targetType = reader.readU1();
            targetInfo = new TargetInfo(targetType.getValue());
            // todo
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

        public TargetInfo(int targetType) {
            this.targetType = targetType;
        }
        
        @Override
        protected void readContent(ClassReader reader) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public static class TypePath extends ClassComponent {

        @Override
        protected void readContent(ClassReader reader) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    
}
