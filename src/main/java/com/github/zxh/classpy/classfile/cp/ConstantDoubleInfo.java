package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U4;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_Double_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}
*/
public class ConstantDoubleInfo extends ConstantInfo {

    private U4 highBytes;
    private U4 lowBytes;
    private double value;
    
    @Override
    protected void readInfo(ClassReader reader) {
        value = reader.getByteBuffer().getDouble(reader.getPosition());
        highBytes = reader.readU4();
        lowBytes = reader.readU4();
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(getTag()/*todo*/);
    }
    
}
