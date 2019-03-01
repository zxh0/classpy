package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassFilePart;
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
public class LocalVariableTypeTableAttribute extends AttributeInfo {

    {
        u2   ("local_variable_type_table_length");
        table("local_variable_type_table", LocalVariableTypeTableEntry.class);
    }


    public static class LocalVariableTypeTableEntry extends ClassFilePart {

        {
            u2  ("start_pc");
            u2  ("length");
            u2cp("name_index");
            u2cp("signature_index");
            u2  ("index");
        }

        @Override
        protected void postRead(ConstantPool cp) {
            int nameIndex = super.getUInt("name_index");
            setDesc(cp.getUtf8String(nameIndex));
        }
    
    }
    
}
