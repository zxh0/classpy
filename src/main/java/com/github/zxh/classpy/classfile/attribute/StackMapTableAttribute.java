package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
StackMapTable_attribute {
    u2              attribute_name_index;
    u4              attribute_length;
    u2              number_of_entries;
    stack_map_frame entries[number_of_entries];
}
 */
public class StackMapTableAttribute extends AttributeInfo {

    private U2 numberOfEntries;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numberOfEntries = reader.readU2();
        
        // todo
        reader.skipBytes(attributeLength.getValue() - 2);
    }
    
    
//    public static class StackMapFrame extends ClassComponent {
//
//        @Override
//        protected void readContent(ClassReader reader) {
//            // todo
//        }
//        
//    }
    
}
