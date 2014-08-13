package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

/*
Exceptions_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_exceptions;
    u2 exception_index_table[number_of_exceptions];
}
 */
public class ExceptionsAttribute extends AttributeInfo {

    private U2 numberOfExceptions;
    private U2[] exceptionIndexTable;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numberOfExceptions = reader.readU2();
        exceptionIndexTable = reader.readArray(U2.class,
                numberOfExceptions.getValue());
    }
    
}
