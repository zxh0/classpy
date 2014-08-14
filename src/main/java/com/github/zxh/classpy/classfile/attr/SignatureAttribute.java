package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import java.util.Arrays;
import java.util.List;

/*
Signature_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 signature_index;
}
 */
public class SignatureAttribute extends AttributeInfo {

    private U2 signatureIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        signatureIndex = reader.readU2();
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength,
                signatureIndex);
    }
    
}
