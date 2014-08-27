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
public class CodeItem extends DexComponent {

    private UShort registersSize;
    private UShort insSize;
    private UShort outsSize;
    private UShort triesSize;
    private UInt debugInfoOff;
    private UInt insnsSize;
    
    @Override
    protected void readContent(DexReader reader) {
        registersSize = reader.readUShort();
        insSize = reader.readUShort();
        outsSize = reader.readUShort();
        triesSize = reader.readUShort();
        debugInfoOff = reader.readUInt();
        insnsSize = reader.readUInt();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(registersSize, insSize, outsSize, triesSize,
                debugInfoOff, insnsSize);
    }
    
}
