package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.body.data.EncodedArrayItem.EncodedAnnotation;
import com.github.zxh.classpy.dexfile.datatype.UByte;

/**
 *
 * @author zxh
 */
public class AnnotationItem extends DexComponent {

    private UByte visibility;
    private EncodedAnnotation annotation;
    
    @Override
    protected void readContent(DexReader reader) {
        visibility = reader.readUByte();
        annotation = new EncodedAnnotation();
        annotation.read(reader);
    }
    
}
