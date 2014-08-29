package com.github.zxh.classpy.pecoff.header;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class CoffHeader extends PeComponent {

    private UInt16Hex machine;
    
    @Override
    protected void readContent(PeReader reader) {
        machine = reader.readUInt16Hex();
    }

    @Override
    public List<? extends PeComponent> getSubComponents() {
        return Arrays.asList(machine);
    }
    
}
