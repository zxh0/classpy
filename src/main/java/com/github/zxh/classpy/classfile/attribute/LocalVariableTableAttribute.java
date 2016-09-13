package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.U2;

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

    
    public static class LocalVariableTableEntry extends ClassComponent {

        {
            u2  ("start_pc");
            u2  ("length");
            u2cp("name_index");
            u2cp("descriptor_index");
            u2  ("index");
        }

        @Override
        protected void afterRead(ConstantPool cp) {
            int startPc = ((U2) super.get("start_pc")).getValue();
            int length = ((U2) super.get("length")).getValue();
            int nameIndex = ((U2) super.get("name_index")).getValue();

            int fromPc = startPc;
            int toPc = fromPc + length - 1;
            String varName = cp.getConstantDesc(nameIndex);
            setDesc(String.format("%s(%d~%d)", varName, fromPc, toPc));
        }
        
    }
    
}
