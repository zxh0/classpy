package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

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

    private U2 localVariableTableLength;
    private Table<LocalVariableTableEntry> localVariableTable;
    
    @Override
    protected void readInfo(ClassReader reader) {
        localVariableTableLength = reader.readU2();
        localVariableTable = reader.readTable(LocalVariableTableEntry.class,
                localVariableTableLength);
    }
    
    
    public static class LocalVariableTableEntry extends ClassComponent {
        
        private U2 startPc;
        private U2 length;
        private U2CpIndex nameIndex;
        private U2CpIndex descriptorIndex;
        private U2 index;

        @Override
        protected void readContent(ClassReader reader) {
            startPc = reader.readU2();
            length = reader.readU2();
            nameIndex = reader.readU2CpIndex();
            descriptorIndex = reader.readU2CpIndex();
            index = reader.readU2();
            setDesc(reader.getConstantPool().getConstantDesc(nameIndex.getValue()));
        }
    
    }
    
}
