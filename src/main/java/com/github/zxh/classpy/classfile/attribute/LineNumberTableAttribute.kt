package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
LineNumberTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 line_number_table_length;
    {   u2 start_pc;
        u2 line_number;	
    } line_number_table[line_number_table_length];
}
 */
class LineNumberTableAttribute : AttributeInfo() {

    init {
        u2   ("line_number_table_length");
        table("line_number_table", LineNumberTableEntry.class);
    }
    
}

class LineNumberTableEntry : ClassComponent() {

    init {
        u2("start_pc");
        u2("line_number");
    }

    override fun afterRead(cp: ConstantPool) {
        val lineNumber = super.getInt("line_number")
        val startPc = super.getInt("start_pc")
        setName("line " + lineNumber);
        setDesc(Integer.toString(startPc));
    }

}
