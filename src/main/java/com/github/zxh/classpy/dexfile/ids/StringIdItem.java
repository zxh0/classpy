package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import com.github.zxh.classpy.dexfile.data.DataOffset;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zxh
 */
public class StringIdItem extends DexComponent implements DataOffset {

    private UInt stringDataOff;
    
    @Override
    public int getDataOffset() {
        return stringDataOff.getValue();
    }
    
    @Override
    protected void readContent(DexReader reader) {
        stringDataOff = reader.readUInt();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Collections.singletonList(stringDataOff);
    }
    
}
