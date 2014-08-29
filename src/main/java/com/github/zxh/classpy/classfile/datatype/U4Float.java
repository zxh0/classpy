package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;

/**
 *
 * @author zxh
 */
public class U4Float extends ClassComponent {

    @Override
    protected void readContent(ClassReader reader) {
        float value = reader.getByteBuffer().getFloat();
        setDesc(String.valueOf(value));
    }
    
}
