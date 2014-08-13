package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import com.github.zxh.classpy.classfile.U4;

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
public class CodeAttribute extends AttributeInfo {

    private U2 maxStack;
    private U2 maxLocals;
    private U4 codeLength;
    private byte[] code; // todo
    private U2 exceptionTableLength;
    private ExceptionTableEntry[] exceptionTable;
    private U2 attributesCount;
    private AttributeInfo[] attributes;
    
    @Override
    protected void readInfo(ClassReader reader) {
        maxStack = reader.readU2();
        maxLocals = reader.readU2();
        codeLength = reader.readU4();
        code = reader.readBytes(codeLength.getValue());
        exceptionTableLength = reader.readU2();
        exceptionTable = new ExceptionTableEntry[exceptionTableLength.getValue()];
        for (int i = 0; i < exceptionTable.length; i++) {
            ExceptionTableEntry entry = new ExceptionTableEntry();
            entry.read(reader);
            exceptionTable[i] = entry;
        }
        attributesCount = reader.readU2();
        attributes = new AttributeInfo[attributesCount.getValue()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = reader.readAttributeInfo();
        }
    }
    
    public static class ExceptionTableEntry extends ClassComponent {
        private U2 startPc;
        private U2 endPc;
        private U2 handlerPc;
        private U2 catchType;

        @Override
        protected void readContent(ClassReader reader) {
            startPc = reader.readU2();
            endPc = reader.readU2();
            handlerPc = reader.readU2();
            catchType = reader.readU2();
        }
    }
    
}
