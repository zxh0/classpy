package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_InvokeDynamic_info {
    u1 tag;
    u2 bootstrap_method_attr_index;
    u2 name_and_type_index;
}
*/
public class ConstantInvokeDynamicInfo extends ConstantInfo {

    private U2 bootstrapMethodAttrIndex;
    private U2 nameAndTypeIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        bootstrapMethodAttrIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }
    
    @Override
    protected String loadDesc(ConstantPool pool) {
        return pool.getNameAndTypeInfo(nameAndTypeIndex).loadDesc(pool);
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, bootstrapMethodAttrIndex, nameAndTypeIndex);
    }
    
}
