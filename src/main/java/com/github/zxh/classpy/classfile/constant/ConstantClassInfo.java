package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_Class_info {
    u1 tag;
    u2 name_index;
}
*/
public class ConstantClassInfo extends ConstantInfo {

    private U2 nameIndex;

    public U2 getNameIndex() {
        return nameIndex;
    }

    @Override
    protected void readInfo(ClassReader reader) {
        nameIndex = reader.readU2();
    }

    @Override
    protected String loadDesc(ConstantPool pool) {
        return pool.getUtf8String(nameIndex);
    }

    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(tag, nameIndex);
    }
    
}
