package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class U4Hex extends ClassComponent {

    @Override
    protected void readContent(ClassReader reader) {
        int value = reader.getByteBuffer().getInt();
        setDesc("0x" + Integer.toHexString(value).toUpperCase());
    }
    
}
