/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.zxh.classpy.dexfile.body.data.encoded;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.body.data.EncodedArrayItem.EncodedValue;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;

/**
 *
 * @author zxh
 */
public class EncodedArray extends DexComponent {

    private Uleb128 size;
    private SizeKnownList<EncodedValue> values;

    @Override
    protected void readContent(DexReader reader) {
        size = reader.readUleb128();
        values = reader.readSizeKnownList(size, EncodedValue::new);
    }

}
