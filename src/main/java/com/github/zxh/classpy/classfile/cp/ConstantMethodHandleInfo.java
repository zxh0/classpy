package com.github.zxh.classpy.classfile.cp;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U1;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
CONSTANT_MethodHandle_info {
    u1 tag;
    u1 reference_kind;
    u2 reference_index;
}
*/
public class ConstantMethodHandleInfo extends ConstantInfo {

    private U1 referenceKind;
    private U2 referenceIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        referenceKind = reader.readU1();
        referenceIndex = reader.readU2();
    }
        
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(getTag(), referenceKind, referenceIndex);
    }
    
}
