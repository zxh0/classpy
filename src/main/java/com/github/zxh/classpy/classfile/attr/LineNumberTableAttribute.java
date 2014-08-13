package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

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
    private LineNumberTableEntry[] lineNumberTable;
    
    @Override
    protected void readInfo(ClassReader reader) {
        lineNumberTableLength = reader.readU2();
        lineNumberTable = reader.readArray(LineNumberTableEntry.class,
                lineNumberTableLength.getValue());
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
