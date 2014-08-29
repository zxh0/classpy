package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.common.Util;

/**
 *
 * @author zxh
 */
public class U4Hex extends ClassComponent {

    @Override
    protected void readContent(ClassReader reader) {
        int value = reader.getByteBuffer().getInt();
        setDesc(Util.toHexString(value));
    }
    
}
