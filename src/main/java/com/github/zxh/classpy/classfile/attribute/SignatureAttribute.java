package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
Signature_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 signature_index;
}
 */
public class SignatureAttribute extends AttributeInfo {

    private U2CpIndex signatureIndex;
    
    @Override
    protected void readInfo(ClassReader reader) {
        signatureIndex = reader.readU2CpIndex();
    }
    
}
