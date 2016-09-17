package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.U4;

/*
Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
 */
class CodeAttribute : AttributeInfo() {

    init {
        val codeLength = U4();

        u2   ("max_stack");
        u2   ("max_locals");
        add  ("code_length", codeLength);
        add  ("code", Code(codeLength));
        u2   ("exception_table_length");
        table("exception_table", ExceptionTableEntry::class.java);
        u2   ("attributes_count");
        table("attributes", AttributeInfo::class.java);
    }
    
}

class ExceptionTableEntry : ClassComponent() {

    init {
        u2  ("start_pc");
        u2  ("end_pc");
        u2  ("handler_pc");
        u2cp("catch_type");
    }

}