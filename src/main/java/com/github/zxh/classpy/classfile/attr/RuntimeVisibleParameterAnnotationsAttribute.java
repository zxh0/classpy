package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.U1;
import com.github.zxh.classpy.classfile.U2;
import com.github.zxh.classpy.classfile.attr.RuntimeVisibleAnnotationsAttribute.AnnotationInfo;
import java.util.Arrays;
import java.util.List;

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
public class RuntimeVisibleParameterAnnotationsAttribute extends AttributeInfo {

    private U1 numParameters;
    private Table<ParameterAnnotationInfo> parameterAnnotations;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numParameters = reader.readU1();
        parameterAnnotations = reader.readTable(ParameterAnnotationInfo.class,
                numParameters.getValue());
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength,
                numParameters, parameterAnnotations);
    }
    
    
    public static class ParameterAnnotationInfo extends ClassComponent {
        
        private U2 numAnnotations;
        private Table<AnnotationInfo> annotations;
        
        @Override
        protected void readContent(ClassReader reader) {
            numAnnotations = reader.readU2();
            annotations = reader.readTable(AnnotationInfo.class, numAnnotations);
        }
        
        @Override
        public List<ClassComponent> getSubComponents() {
            return Arrays.asList(numAnnotations, annotations);
        }
        
    }
    
}
