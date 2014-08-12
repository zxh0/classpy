package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1;

/**
 *
 * @author zxh
 */
public abstract class ConstantInfo extends ClassComponent {

//    private ConstantType tag;
    private U1 tag;
    
    @Override
    public final void readContent(ClassReader reader) {
        tag = reader.readU1();
        readInfo(reader);
    }
    
    public abstract void readInfo(ClassReader reader);
    
}
