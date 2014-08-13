package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
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
    protected void readContent(ClassReader reader) {
        constants.add(null); // The constant_pool table is indexed from 1 to constant_pool_count - 1. 
        for (int i = 1; i < cpCount; i++) {
            constants.add(reader.readConstantInfo());
        }
    }
    
    public String getUtf8String(int index) {
        ConstantInfo info = constants.get(index);
        if (info instanceof ConstantUtf8Info) {
            return ((ConstantUtf8Info) info).getValue();
        } else {
            throw new ClassParseException("Constant#" + index + " is not ConstantUtf8Info!");
        }
    }
    
}
