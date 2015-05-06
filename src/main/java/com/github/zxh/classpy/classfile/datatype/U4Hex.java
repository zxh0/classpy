package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.helper.StringUtil;

public class U4Hex extends ClassComponent {

    @Override
    protected void readContent(ClassReader reader) {
        int value = reader.readInt();
        setDesc(StringUtil.toHexString(value));
    }
    
}
