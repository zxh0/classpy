package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UShortTypeIdIndex;

/**
 *
 * @author zxh
 */
public class TypeItem extends DexComponent {

    private UShortTypeIdIndex typeIdx;
    
    @Override
    protected void readContent(DexReader reader) {
        typeIdx = reader.readUShortTypeIdIndex();
    }

    @Override
    protected void postRead(DexFile dexFile) {
        super.postRead(dexFile);
        setDesc(dexFile.getTypeIdItem(typeIdx).getDesc());
    }
    
}
