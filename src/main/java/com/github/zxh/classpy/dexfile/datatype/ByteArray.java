package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;

/**
 *
 * @author zxh
 */
public class ByteArray extends DexComponent {

    private final int byteCount;

    public ByteArray(int byteCount) {
        this.byteCount = byteCount;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        reader.skipBytes(byteCount);
    }
    
}
