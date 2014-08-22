package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.header.HeaderItem;
import com.github.zxh.classpy.dexfile.ids.StringIdItem;
import java.util.Arrays;
import java.util.List;

/**
 *
 * http://source.android.com/devices/tech/dalvik/dex-format.html
 * @author zxh
 */
public class DexFile extends DexComponent {
    
    private HeaderItem header;
    private DcList<StringIdItem> stringIds;

    @Override
    protected void readContent(DexReader reader) {
        header = new HeaderItem();
        header.read(reader);
        int stringIdSize = header.getStringIdsSize().getValue();
        stringIds = reader.readList(stringIdSize, StringIdItem::new);
        // todo
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header, stringIds);
    }
    
}
