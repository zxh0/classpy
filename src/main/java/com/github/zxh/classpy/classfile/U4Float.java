package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class U4Float extends ClassComponent {

    private float value;
    
    @Override
    public void readContent(ClassReader reader) {
        value = reader.getByteBuffer().getFloat();
    }
    
}
