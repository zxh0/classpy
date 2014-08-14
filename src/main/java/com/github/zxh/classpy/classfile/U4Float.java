package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class U4Float extends ClassComponent {

    private float value;

    public float getValue() {
        return value;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        value = reader.getByteBuffer().getFloat();
        setDesc(String.valueOf(value));
    }
    
}
