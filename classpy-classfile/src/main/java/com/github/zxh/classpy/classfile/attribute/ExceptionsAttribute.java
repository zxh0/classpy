package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
Exceptions_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_exceptions;
    u2 exception_index_table[number_of_exceptions];
}
 */
public class ExceptionsAttribute extends AttributeInfo {

    {
        u2   ("number_of_exceptions");
        table("exception_index_table", U2CpIndex.class);
    }
    
}
