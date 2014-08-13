package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

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

    private U2 localVariableTypeTableLength;
    private LocalVariableTypeTableEntry[] localVariableTypeTable;
    
    @Override
    protected void readInfo(ClassReader reader) {
        localVariableTypeTableLength = reader.readU2();
        localVariableTypeTable = reader.readArray(LocalVariableTypeTableEntry.class,
                localVariableTypeTableLength.getValue());
    }
    
    public static class LocalVariableTypeTableEntry extends ClassComponent {
        
        private U2 startPc;
        private U2 length;
        private U2 nameIndex;
        private U2 signatureIndex;
        private U2 index;

        @Override
        protected void readContent(ClassReader reader) {
            startPc = reader.readU2();
            length = reader.readU2();
            nameIndex = reader.readU2();
            signatureIndex = reader.readU2();
            index = reader.readU2();
        }
        
    }
    
}
