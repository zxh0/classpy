package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;

/**
 *
 * @author zxh
 */
public class AnnotationOffItem extends DexComponent {

    private UIntHex annotationOff;

    public UIntHex getAnnotationOff() {
        return annotationOff;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        annotationOff = reader.readUIntHex();
    }
    
    
    public static class AnnotationSetItem extends SizeHeaderList<AnnotationOffItem> {

        public AnnotationSetItem() {
            super(AnnotationOffItem::new);
        }
        
    }
    
}
