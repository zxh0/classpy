package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntFieldIdIndex;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.datatype.UIntMethodIdIndex;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;

/**
 *
 * @author zxh
 */
public class AnnotationsDirectoryItem extends DexComponent {

    private UIntHex classAnnotationsOff; // -> annotation_set_item
    private UInt annotatedFieldsSize;
    private UInt annotatedMethodsSize;
    private UInt annotatedParametersSize;
    private SizeKnownList<FieldAnnotation> fieldAnnotations;
    private SizeKnownList<MethodAnnotation> methodAnnotations;
    private SizeKnownList<ParameterAnnotation> parameterAnnotations;
    
    @Override
    protected void readContent(DexReader reader) {
        classAnnotationsOff = reader.readUIntHex();
        annotatedFieldsSize = reader.readUInt();
        annotatedMethodsSize = reader.readUInt();
        annotatedParametersSize = reader.readUInt();
        fieldAnnotations = reader.readSizeKnownList(annotatedFieldsSize, FieldAnnotation::new);
        methodAnnotations = reader.readSizeKnownList(annotatedMethodsSize, MethodAnnotation::new);
        parameterAnnotations = reader.readSizeKnownList(annotatedParametersSize, ParameterAnnotation::new);
    }
    
    
    public static class FieldAnnotation extends DexComponent {

        private UIntFieldIdIndex fieldIdx;
        private UIntHex annotationsOff; // -> annotation_set_item
        
        @Override
        protected void readContent(DexReader reader) {
            fieldIdx = reader.readUIntFieldIdIndex();
            annotationsOff = reader.readUIntHex();
        }
        
    }
    
    public static class MethodAnnotation extends DexComponent {

        private UIntMethodIdIndex methodIdx;
        private UIntHex annotationsOff; // -> annotation_set_item
        
        @Override
        protected void readContent(DexReader reader) {
            methodIdx = reader.readUIntMethodIdIndex();
            annotationsOff = reader.readUIntHex();
        }
        
    }
    
    public static class ParameterAnnotation extends MethodAnnotation {
        
    }
    
}
