package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ConstantPool extends ClassComponent {
    
    private final int cpCount;
    private final List<ConstantInfo> constants;

    public ConstantPool(int cpCount) {
        this.cpCount = cpCount;
        constants = new ArrayList<>(cpCount);
    }
    
    @Override
    public void readContent(ClassReader reader) {
        for (int i = 0; i < cpCount; i++) {
            constants.add(readConstantInfo(reader));
        }
    }
    
    private static ConstantInfo readConstantInfo(ClassReader reader) {
        // todo
        return null;
    }
    
}
