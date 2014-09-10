package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;

/**
 *
 * @author zxh
 */
public class AnnotationOffItem extends DexComponent {

    private UInt annotationOff;
    
    @Override
    protected void readContent(DexReader reader) {
        annotationOff = reader.readUInt();
    }
    
    
    public static class AnnotationSetItem extends SizeHeaderList<AnnotationOffItem> {

        public AnnotationSetItem() {
            super(AnnotationOffItem::new);
        }
        
    }
    
}
