package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.list.SizeHeaderList;

/**
 *
 * @author zxh
 */
public class TypeList extends SizeHeaderList<TypeItem> {

    public TypeList() {
        super(TypeItem::new);
    }
    
}
