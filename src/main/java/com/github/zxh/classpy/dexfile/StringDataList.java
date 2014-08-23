package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.ids.StringIdItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxh
 */
public class StringDataList extends DexComponent {

    private final DcList<StringIdItem> stringIds;
    private final List<Utf8String> strings = new ArrayList<>();

    public StringDataList(DcList<StringIdItem> stringIds) {
        this.stringIds = stringIds;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        for (StringIdItem stringId : stringIds.getSubComponents()) {
            reader.setPosition(stringId.getStringDataOff());
            strings.add(reader.readUtf8String());
        }
    }

    @Override
    public List<Utf8String> getSubComponents() {
        return strings;
    }
    
}
