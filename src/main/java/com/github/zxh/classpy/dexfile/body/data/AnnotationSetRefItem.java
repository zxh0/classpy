package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;

/**
 *
 * @author zxh
 */
public class AnnotationSetRefItem extends DexComponent{

    private UIntHex annotationsOff; // -> annotation_set_item

    public UIntHex getAnnotationsOff() {
        return annotationsOff;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        annotationsOff = reader.readUIntHex();
    }
    
    
    public static class AnnotationSetRefList extends SizeHeaderList<AnnotationSetRefItem> {

        public AnnotationSetRefList() {
            super(AnnotationSetRefItem::new);
        }
        
    }
    
}
