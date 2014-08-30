package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;

/**
 *
 * @author zxh
 */
public class DebugInfoItem extends DexComponent {

    private Uleb128 lineStart;
    private Uleb128 parametersSize;
    // parameter_names todo
    
    @Override
    protected void readContent(DexReader reader) {
        lineStart = reader.readUleb128();
        parametersSize = reader.readUleb128();
    }
    
}
