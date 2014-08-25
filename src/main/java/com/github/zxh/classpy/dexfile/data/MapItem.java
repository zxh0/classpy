package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import com.github.zxh.classpy.dexfile.UShort;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class MapItem extends DexComponent {

    private UShort type;
    private UShort unused;
    private UInt size;
    private UInt offset;
    
    @Override
    protected void readContent(DexReader reader) {
        type = reader.readUShort();
        unused = reader.readUShort();
        size = reader.readUInt();
        offset = reader.readUInt();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(type, unused, size, offset);
    }
    
}
