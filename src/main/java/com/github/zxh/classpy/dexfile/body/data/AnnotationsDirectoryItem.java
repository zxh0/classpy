package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class AnnotationsDirectoryItem extends DexComponent {

    private UIntHex classAnnotationsOff;
    private UInt fieldsSize;
    private UInt annotatedMethodsSize;
    private UInt annotatedParametersSize;
    private SizeKnownList<FieldAnnotation> fieldAnnotations;
    private SizeKnownList<MethodAnnotation> methodAnnotations;
    private SizeKnownList<ParameterAnnotation> parameterAnnotations;
    
    @Override
    protected void readContent(DexReader reader) {
        classAnnotationsOff = reader.readUIntHex();
        fieldsSize = reader.readUInt();
        annotatedMethodsSize = reader.readUInt();
        annotatedParametersSize = reader.readUInt();
        fieldAnnotations = reader.readSizeKnownList(fieldsSize, FieldAnnotation::new);
        methodAnnotations = reader.readSizeKnownList(annotatedMethodsSize, MethodAnnotation::new);
        parameterAnnotations = reader.readSizeKnownList(annotatedParametersSize, ParameterAnnotation::new);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classAnnotationsOff, fieldsSize,
                annotatedMethodsSize, annotatedParametersSize,
                fieldAnnotations, methodAnnotations, parameterAnnotations);
    }
    
    
    public static class FieldAnnotation extends DexComponent {

        private UInt fieldIdx;
        private UInt annotationsOff;
        
        @Override
        protected void readContent(DexReader reader) {
            fieldIdx = reader.readUInt();
            annotationsOff = reader.readUInt();
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(fieldIdx, annotationsOff);
        }
        
    }
    
    public static class MethodAnnotation extends DexComponent {

        private UInt methodIdx;
        private UInt annotationsOff;
        
        @Override
        protected void readContent(DexReader reader) {
            methodIdx = reader.readUInt();
            annotationsOff = reader.readUInt();
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(methodIdx, annotationsOff);
        }
        
    }
    
    public static class ParameterAnnotation extends MethodAnnotation {
        
    }
    
}
