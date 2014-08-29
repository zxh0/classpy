package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class DebugInfoItem extends DexComponent {

    private Uleb128 lineStart;
    private Uleb128 parametersSize;
    
    @Override
    protected void readContent(DexReader reader) {
        lineStart = reader.readUleb128();
        parametersSize = reader.readUleb128();
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(lineStart, parametersSize);
    }
    
}
