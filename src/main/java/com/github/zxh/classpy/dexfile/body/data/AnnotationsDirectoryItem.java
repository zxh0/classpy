package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
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
    // field_annotations
    // method_annotations
    // parameter_annotations
    
    
    @Override
    protected void readContent(DexReader reader) {
        classAnnotationsOff = reader.readUIntHex();
        fieldsSize = reader.readUInt();
        annotatedMethodsSize = reader.readUInt();
        annotatedParametersSize = reader.readUInt();
        // todo
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(classAnnotationsOff, fieldsSize,
                annotatedMethodsSize, annotatedParametersSize);
    }
    
}
