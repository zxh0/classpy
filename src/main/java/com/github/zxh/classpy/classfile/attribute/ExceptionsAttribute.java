package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.datatype.U2;
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

    private U2 numberOfExceptions;
    private Table<U2CpIndex> exceptionIndexTable;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numberOfExceptions = reader.readU2();
        exceptionIndexTable = reader.readTable(U2CpIndex.class, numberOfExceptions);
    }
    
}
