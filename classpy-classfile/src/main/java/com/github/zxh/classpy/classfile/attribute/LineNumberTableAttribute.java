package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassFilePart;
import com.github.zxh.classpy.classfile.constant.ConstantPool;

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
public class LineNumberTableAttribute extends AttributeInfo {

    {
        u2   ("line_number_table_length");
        table("line_number_table", LineNumberTableEntry.class);
    }

    
    public static class LineNumberTableEntry extends ClassFilePart {

        {
            u2("start_pc");
            u2("line_number");
        }

        @Override
        protected void postRead(ConstantPool cp) {
            int lineNumber = super.getUInt("line_number");
            int startPc = super.getUInt("start_pc");
            setName("line " + lineNumber);
            setDesc(Integer.toString(startPc));
        }

    }
    
}
