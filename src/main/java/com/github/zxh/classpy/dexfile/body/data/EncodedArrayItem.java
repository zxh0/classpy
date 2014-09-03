package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;

/**
 *
 * @author zxh
 */
public class EncodedArrayItem extends DexComponent {

    private EncodedArray value;
    
    @Override
    protected void readContent(DexReader reader) {
        value = new EncodedArray();
        value.read(reader);
    }
    
    
    // todo
    public static class EncodedArray extends DexComponent {

        private Uleb128 size;
        private SizeHeaderList<EncodedValue> values;
        
        @Override
        protected void readContent(DexReader reader) {
            size = reader.readUleb128();
            values = reader.readSizeHeaderList(EncodedValue::new);
        }
        
    }
    
    public static class EncodedValue extends DexComponent {

        @Override
        protected void readContent(DexReader reader) {
            // todo
        }
        
    }
    
}
