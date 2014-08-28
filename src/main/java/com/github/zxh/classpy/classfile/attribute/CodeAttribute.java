package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.classfile.datatype.U4;
import java.util.Arrays;
import java.util.List;

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
    private Code code;
    private U2 exceptionTableLength;
    private Table<ExceptionTableEntry> exceptionTable;
    private U2 attributesCount;
    private Table<AttributeInfo> attributes;
    
    @Override
    protected void readInfo(ClassReader reader) {
        maxStack = reader.readU2();
        maxLocals = reader.readU2();
        codeLength = reader.readU4();
        code = new Code(codeLength.getValue());
        code.read(reader);
        exceptionTableLength = reader.readU2();
        exceptionTable = reader.readTable(ExceptionTableEntry.class,
                exceptionTableLength);
        attributesCount = reader.readU2();
        attributes = reader.readTable(AttributeInfo.class, attributesCount);
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength,
                maxStack, maxLocals, codeLength, code,
                exceptionTableLength, exceptionTable,
                attributesCount, attributes);
    }
    
    
    public static class ExceptionTableEntry extends ClassComponent {
        
        private U2 startPc;
        private U2 endPc;
        private U2 handlerPc;
        private U2CpIndex catchType;

        @Override
        protected void readContent(ClassReader reader) {
            startPc = reader.readU2();
            endPc = reader.readU2();
            handlerPc = reader.readU2();
            catchType = reader.readU2CpIndex();
        }
        
        @Override
        public List<ClassComponent> getSubComponents() {
            return Arrays.asList(startPc, endPc, handlerPc, catchType);
        }
    
    }
    
}
