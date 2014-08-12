package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class U1 extends ClassComponent {

    private byte value;
    
    @Override
    public void readContent(ClassReader reader) {
        value = reader.getByteBuffer().get();
    }
    
}
