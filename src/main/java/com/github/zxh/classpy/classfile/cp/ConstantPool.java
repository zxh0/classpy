package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.ClassReader;
import java.util.function.Consumer;

/**
 *
 * @author zxh
 */
public class ConstantPool extends ClassComponent {
    
    private final int cpCount;
    private final ConstantInfo[] constants;

    public ConstantPool(int cpCount) {
        this.cpCount = cpCount;
        constants = new ConstantInfo[cpCount];
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        // The constant_pool table is indexed from 1 to constant_pool_count - 1. 
        for (int i = 1; i < cpCount; i++) {
            constants[i] = reader.readConstantInfo();
            setConstantName(constants[i], i);
        }
    }
    
    // like #32: Utf8
    private void setConstantName(ConstantInfo constant, int idx) {
        int idxWide = String.valueOf(cpCount).length();
        String fmtStr = "#%0" + idxWide + "d: %s";
        String constantName = constant.getClass().getSimpleName()
                .replace("Constant", "")
                .replace("Info", "");
        constant.setName(String.format(fmtStr, idx, constantName));
    }
    
    public void forEach(Consumer<ConstantInfo> consumer) {
        for (int i = 1; i < cpCount; i++) {
            consumer.accept(constants[i]);
        }
    }
    
    public String getUtf8String(int index) {
        ConstantInfo info = constants[index];
        if (info instanceof ConstantUtf8Info) {
            return ((ConstantUtf8Info) info).getValue();
        } else {
            throw new ClassParseException("Constant#" + index + " is not ConstantUtf8Info!");
        }
    }
    
}
