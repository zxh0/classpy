package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.Util;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.body.data.encoded.EncodedAnnotation;
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
        visibility.setDesc(Util.toHexString(visibility.getValue()) + "("
                + getVisibilityName(visibility.getValue()) + ")");
        annotation = new EncodedAnnotation();
        annotation.read(reader);
    }
    
    private static String getVisibilityName(int value) {
        if (value == 0x00) {
            return "VISIBILITY_BUILD";
        }
        if (value == 0x01) {
            return "VISIBILITY_RUNTIME";
        }
        if (value == 0x02) {
            return "VISIBILITY_SYSTEM";
        }
        throw new FileParseException("Invalid annotation visibility: " + value);
    }
    
}
