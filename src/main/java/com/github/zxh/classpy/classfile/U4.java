package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class U4 extends ClassComponent {

    private int value;
    
    @Override
    protected void readContent(ClassReader reader) {
        value = reader.getByteBuffer().getInt();
    }
    
}
