package com.github.zxh.classpy.classfile.attribute;

/*
Signature_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 signature_index;
}
 */
class SignatureAttribute : AttributeInfo() {

    init {
        u2cp("signature_index");
    }
    
}
