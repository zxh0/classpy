package com.github.zxh.classpy.dexfile;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class StringDataItem extends DexComponent {

    private ULEB128 utf16Size;
    private Utf8String data;
    
    @Override
    protected void readContent(DexReader reader) {
        utf16Size = reader.readULEB128();
        data = reader.readUtf8String();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(utf16Size, data);
    }
    
}
