package com.github.zxh.classpy.dexfile.datatype;

import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.body.ids.ProtoIdItem;

/**
 *
 * @author zxh
 */
public class UShortProtoIdIndex extends UShort {

    @Override
    protected void postRead(DexFile dexFile) {
        int index = getValue();
        if (index >= 0) {
            ProtoIdItem protoId = dexFile.getProtoIdItem(index);
            String protoDesc = dexFile.getString(protoId.getShortyIdx());

            setDesc(index + "->" + protoDesc);
        }
    }
    
}
