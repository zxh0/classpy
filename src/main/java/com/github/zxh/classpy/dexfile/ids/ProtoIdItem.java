package com.github.zxh.classpy.dexfile.ids;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.UInt;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ProtoIdItem extends DexComponent {

    private UInt shortyIdx;
    private UInt returnTypeIdx;
    private UInt parametersOff;
    
    @Override
    protected void readContent(DexReader reader) {
        shortyIdx = reader.readUInt();
        returnTypeIdx = reader.readUInt();
        parametersOff = reader.readUInt();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(shortyIdx, returnTypeIdx, parametersOff);
    }
    
}
