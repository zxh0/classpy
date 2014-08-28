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
        ProtoIdItem protoId = dexFile.getProtoIdItem(getValue());
        String protoDesc = dexFile.getString(protoId.getShortyIdx());
        
        setDesc(getValue() + "->" + protoDesc);
    }
    
}
