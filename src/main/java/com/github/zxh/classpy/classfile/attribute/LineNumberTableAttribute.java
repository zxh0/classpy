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
public class LineNumberTableAttribute extends AttributeInfo {

    {
        u2   ("line_number_table_length");
        table("line_number_table", LineNumberTableEntry.class);
    }

    
    public static class LineNumberTableEntry extends ClassComponent {

        {
            u2("start_pc");
            u2("line_number");
        }

        @Override
        protected void afterRead(ConstantPool cp) {
            int lineNumber = ((U2) super.get("line_number")).getValue();
            int startPc = ((U2) super.get("start_pc")).getValue();
            setName("line " + lineNumber);
            setDesc(Integer.toString(startPc));
        }

    }
    
}
