package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
LineNumberTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 line_number_table_length;
    {   u2 start_pc;
        u2 line_number;	
    } line_number_table[line_number_table_length];
}
 */
public class LineNumberTableAttribute extends AttributeInfo {

    private U2 lineNumberTableLength;
    private Table<LineNumberTableEntry> lineNumberTable;
    
    @Override
    protected void readInfo(ClassReader reader) {
        lineNumberTableLength = reader.readU2();
        lineNumberTable = reader.readTable(LineNumberTableEntry.class,
                lineNumberTableLength);
        lineNumberTable.getSubComponents().forEach(entry -> {
            entry.setName("line " + entry.lineNumber.getValue());
            entry.setDesc(entry.startPc.getValue());
        });
    }
    
    
    public static class LineNumberTableEntry extends ClassComponent {
        
        private U2 startPc;
        private U2 lineNumber;

        @Override
        protected void readContent(ClassReader reader) {
            startPc = reader.readU2();
            lineNumber = reader.readU2();
        }
        
    }
    
}
