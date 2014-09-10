package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;

/**
 *
 * @author zxh
 */
public class AnnotationSetRefItem extends DexComponent{

    private UInt annotationsOff; // -> annotation_set_item

    public UInt getAnnotationsOff() {
        return annotationsOff;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        annotationsOff = reader.readUInt();
    }
    
    
    public static class AnnotationSetRefList extends SizeHeaderList<AnnotationSetRefItem> {

        public AnnotationSetRefList() {
            super(AnnotationSetRefItem::new);
        }
        
    }
    
}
