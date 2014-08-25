package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.dexfile.DexList;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class MapList extends DexComponent {

    private UInt size;
    private DexList<MapItem> list;
    
    @Override
    protected void readContent(DexReader reader) {
        size = reader.readUInt();
        list = reader.readList(size, MapItem::new);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(size, list);
    }
    
}
