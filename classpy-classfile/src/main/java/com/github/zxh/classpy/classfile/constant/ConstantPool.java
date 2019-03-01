package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassFilePart;
import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.classfile.ClassFileReader;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.helper.StringHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The constant pool in class file.
 */
public class ConstantPool extends ClassFilePart {
    
    private final U2 cpCount;
    private ConstantInfo[] constants;

    public ConstantPool(U2 cpCount) {
        this.cpCount = cpCount;
    }
    
    @Override
    protected void readContent(ClassFileReader reader) {
        constants = new ConstantInfo[cpCount.getValue()];
        // The constant_pool table is indexed from 1 to constant_pool_count - 1. 
        for (int i = 1; i < cpCount.getValue(); i++) {
            ConstantInfo c = readConstantInfo(reader);
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
        reader.setConstantPool(this);
    }
    
    private ConstantInfo readConstantInfo(ClassFileReader reader) {
        byte tag = reader.getByte(reader.getPosition());
        
        ConstantInfo ci = ConstantFactory.create(tag);
        ci.read(reader);
        
        return ci;
    }
    
    // like #32: (Utf8)
    private void setConstantName(ConstantInfo constant, int idx) {
        String idxStr = StringHelper.formatIndex(cpCount.getValue(), idx);
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
    public List<FilePart> getParts() {
        return Arrays.stream(constants)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String getUtf8String(int index) {
        return getConstant(ConstantUtf8Info.class, index).getString();
    }
    
    public ConstantUtf8Info getUtf8Info(int index) {
        return getConstant(ConstantUtf8Info.class, index);
    }
    
    public ConstantClassInfo getClassInfo(int index) {
        return getConstant(ConstantClassInfo.class, index);
    }
    
    public ConstantNameAndTypeInfo getNameAndTypeInfo(int index) {
        return getConstant(ConstantNameAndTypeInfo.class, index);
    }
    
    public ConstantFieldrefInfo getFieldrefInfo(int index) {
        return getConstant(ConstantFieldrefInfo.class, index);
    }
    
    public ConstantMethodrefInfo getMethodrefInfo(int index) {
        return getConstant(ConstantMethodrefInfo.class, index);
    }
    
    public ConstantInterfaceMethodrefInfo getInterfaceMethodrefInfo(int index) {
        return getConstant(ConstantInterfaceMethodrefInfo.class, index);
    }
    
    private <T> T getConstant(Class<T> classOfT, int index) {
        ConstantInfo c = constants[index];
        if (c.getClass() != classOfT) {
            throw new ParseException("Constant#" + index
                    + " is " + c.getClass().getSimpleName()
                    + " not " + classOfT.getSimpleName() + "!");
        }
        return classOfT.cast(c);
    }
    
    public String getConstantDesc(int index) {
        ConstantInfo c = constants[index];
        return c.getDesc();
    }
    
}
