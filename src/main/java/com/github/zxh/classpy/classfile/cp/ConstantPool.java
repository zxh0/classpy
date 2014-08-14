package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import com.github.zxh.classpy.classfile.Util;
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
    
    // like #32: (Utf8)
    private void setConstantName(ConstantInfo constant, int idx) {
        String idxStr = Util.formatIndex(cpCount, idx);
        String constantName = constant.getClass().getSimpleName()
                .replace("Constant", "")
                .replace("Info", "");
        constant.setName(idxStr + " (" + constantName + ")");
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
        return getConstant(ConstantUtf8Info.class, index).getString();
    }
    
    public String getUtf8String(U2 index) {
        return getUtf8String(index.getValue());
    }
    
    public ConstantUtf8Info getUtf8Info(U2 index) {
        return getConstant(ConstantUtf8Info.class, index.getValue());
    }
    
    public ConstantClassInfo getClassInfo(U2 index) {
        return getConstant(ConstantClassInfo.class, index.getValue());
    }
    
    public ConstantNameAndTypeInfo getNameAndTypeInfo(U2 index) {
        return getConstant(ConstantNameAndTypeInfo.class, index.getValue());
    }
    
    public ConstantFieldrefInfo getFieldrefInfo(U2 index) {
        return getConstant(ConstantFieldrefInfo.class, index.getValue());
    }
    
    public ConstantMethodrefInfo getMethodrefInfo(U2 index) {
        return getConstant(ConstantMethodrefInfo.class, index.getValue());
    }
    
    public ConstantInterfaceMethodrefInfo getInterfaceMethodrefInfo(U2 index) {
        return getConstant(ConstantInterfaceMethodrefInfo.class, index.getValue());
    }
    
    private <T> T getConstant(Class<T> classOfT, int index) {
        ConstantInfo c = constants[index];
        if (c.getClass() != classOfT) {
            throw new ClassParseException("Constant#" + index + " is not " + classOfT.getSimpleName() + "!");
        }
        return classOfT.cast(c);
    }
    
}
