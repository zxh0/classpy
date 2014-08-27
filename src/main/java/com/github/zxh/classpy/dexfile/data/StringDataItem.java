package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.Uleb128;
import com.github.zxh.classpy.dexfile.Mutf8;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class StringDataItem extends DexComponent {

    private Uleb128 utf16Size;
    private Mutf8 data;
    
    @Override
    protected void readContent(DexReader reader) {
        utf16Size = reader.readUleb128();
        data = reader.readUtf8String();
        setDesc(data.getDesc());
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(utf16Size, data);
    }
    
}
