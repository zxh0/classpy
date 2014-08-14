package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.ClassReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            ConstantInfo c = reader.readConstantInfo();
            setConstantName(c, i);
            constants[i] = c;
            // http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
            // All 8-byte constants take up two entries in the constant_pool table of the class file.
            // If a CONSTANT_Long_info or CONSTANT_Double_info structure is the item in the constant_pool
            // table at index n, then the next usable item in the pool is located at index n+2. 
            // The constant_pool index n+1 must be valid but is considered unusable. 
            if (c instanceof ConstantLongInfo || c instanceof ConstantDoubleInfo) {
                i++;
            }
        }
        loadConstantDesc();
    }
    
    // like #32: Utf8
    private void setConstantName(ConstantInfo constant, int idx) {
        int idxWide = String.valueOf(cpCount).length();
        String fmtStr = "#%0" + idxWide + "d (%s)";
        String constantName = constant.getClass().getSimpleName()
                .replace("Constant", "")
                .replace("Info", "");
        constant.setName(String.format(fmtStr, idx, constantName));
    }
    
    private void loadConstantDesc() {
        for (ConstantInfo c : constants) {
            if (c != null) {
                c.setDesc(c.loadDesc(this));
            }
        }
    }

    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.stream(constants)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    public String getUtf8String(int index) {
        ConstantInfo info = constants[index];
        if (info instanceof ConstantUtf8Info) {
            return ((ConstantUtf8Info) info).getString();
        } else {
            throw new ClassParseException("Constant#" + index + " is not ConstantUtf8Info!");
        }
    }
    
}
