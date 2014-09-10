package com.github.zxh.classpy.dexfile.body.data.encoded;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.datatype.Uleb128StringIdIndex;
import com.github.zxh.classpy.dexfile.datatype.Uleb128TypeIdIndex;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;

/**
 *
 * @author zxh
 */
public class EncodedAnnotation extends DexComponent {

    private Uleb128TypeIdIndex typeIdx;
    private Uleb128 size;
    private SizeKnownList<AnnotationElement> elements;

    @Override
    protected void readContent(DexReader reader) {
        typeIdx = reader.readUleb128TypeIdIndex();
        size = reader.readUleb128();
        elements = reader.readSizeKnownList(size, AnnotationElement::new);
    }

    
    public static class AnnotationElement extends DexComponent {

        private Uleb128StringIdIndex nameIdx;
        private EncodedValue value;

        @Override
        protected void readContent(DexReader reader) {
            nameIdx = reader.readUleb128StringIdIndex();
            value = new EncodedValue();
            value.read(reader);
        }

    }
    
}
