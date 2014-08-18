package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.U2;
import com.github.zxh.classpy.classfile.U2CpIndex;
import java.util.Arrays;
import java.util.List;

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
    private Table<LocalVariableTypeTableEntry> localVariableTypeTable;
    
    @Override
    protected void readInfo(ClassReader reader) {
        localVariableTypeTableLength = reader.readU2();
        localVariableTypeTable = reader.readTable(LocalVariableTypeTableEntry.class,
                localVariableTypeTableLength);
    }
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength,
                localVariableTypeTableLength, localVariableTypeTable);
    }
    
    
    public static class LocalVariableTypeTableEntry extends ClassComponent {
        
        private U2 startPc;
        private U2 length;
        private U2CpIndex nameIndex;
        private U2CpIndex signatureIndex;
        private U2 index;

        @Override
        protected void readContent(ClassReader reader) {
            startPc = reader.readU2();
            length = reader.readU2();
            nameIndex = reader.readU2CpIndex();
            signatureIndex = reader.readU2CpIndex();
            index = reader.readU2();
            setDesc(reader.getConstantPool().getUtf8String(nameIndex));
        }
        
        @Override
        public List<ClassComponent> getSubComponents() {
            return Arrays.asList(startPc, length, nameIndex, signatureIndex, index);
        }
    
    }
    
}
