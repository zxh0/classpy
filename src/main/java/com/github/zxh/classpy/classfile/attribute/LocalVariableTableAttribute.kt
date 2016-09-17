package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;

/*
LocalVariableTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 local_variable_table_length;
    {   u2 start_pc;
        u2 length;
        u2 name_index;
        u2 descriptor_index;
        u2 index;
    } local_variable_table[local_variable_table_length];
}
 */
class LocalVariableTableAttribute : AttributeInfo() {

    init {
        u2   ("local_variable_table_length");
        table("local_variable_table", LocalVariableTableEntry::class.java);
    }
    
}

class LocalVariableTableEntry : ClassComponent() {

    init {
        u2  ("start_pc");
        u2  ("length");
        u2cp("name_index");
        u2cp("descriptor_index");
        u2  ("index");
    }

    override fun afterRead(cp: ConstantPool) {
        val startPc = super.getInt("start_pc");
        val length = super.getInt("length");
        val nameIndex = super.getInt("name_index");

        val fromPc = startPc;
        val toPc = fromPc + length - 1;
        val varName = cp.getConstantDesc(nameIndex);
        setDesc(String.format("%s(%d~%d)", varName, fromPc, toPc));
    }

}
