package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassFilePart;
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
public class LocalVariableTableAttribute extends AttributeInfo {

    {
        u2   ("local_variable_table_length");
        table("local_variable_table", LocalVariableTableEntry.class);
    }

    
    public static class LocalVariableTableEntry extends ClassFilePart {

        {
            u2  ("start_pc");
            u2  ("length");
            u2cp("name_index");
            u2cp("descriptor_index");
            u2  ("index");
        }

        @Override
        protected void postRead(ConstantPool cp) {
            int startPc = super.getUInt("start_pc");
            int length = super.getUInt("length");
            int nameIndex = super.getUInt("name_index");

            int fromPc = startPc;
            int toPc = fromPc + length - 1;
            String varName = cp.getConstantDesc(nameIndex);
            setDesc(String.format("%s(%d~%d)", varName, fromPc, toPc));
        }
        
    }
    
}
