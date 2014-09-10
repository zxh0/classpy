package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;

/**
 *
 * @author zxh
 */
public class AnnotationSetRefItem extends DexComponent{

    private UInt annotationsOff;
    
    @Override
    protected void readContent(DexReader reader) {
        annotationsOff = reader.readUInt();
    }
    
}
