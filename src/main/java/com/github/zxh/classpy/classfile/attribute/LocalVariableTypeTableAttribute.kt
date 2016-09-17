package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;

/*
LocalVariableTypeTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 local_variable_type_table_length;
    {   u2 start_pc;
        u2 length;
        u2 name_index;
        u2 signature_index;
        u2 index;
    } local_variable_type_table[local_variable_type_table_length];
}
 */
class LocalVariableTypeTableAttribute : AttributeInfo() {

    init {
        u2   ("local_variable_type_table_length");
        table("local_variable_type_table", LocalVariableTypeTableEntry::class.java);
    }
    
}

class LocalVariableTypeTableEntry : ClassComponent() {

    init {
        u2  ("start_pc");
        u2  ("length");
        u2cp("name_index");
        u2cp("signature_index");
        u2  ("index");
    }

    override fun afterRead(cp: ConstantPool) {
        val nameIndex = super.getInt("name_index");
        desc = cp.getUtf8String(nameIndex);
    }

}