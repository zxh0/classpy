package com.github.zxh.classpy.dexfile.body.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zxh
 */
public class StringIdItem extends DexComponent {

    private UIntHex stringDataOff;
    
    public UIntHex getStringDataOff() {
        return stringDataOff;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        stringDataOff = reader.readUIntHex();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Collections.singletonList(stringDataOff);
    }
    
}
