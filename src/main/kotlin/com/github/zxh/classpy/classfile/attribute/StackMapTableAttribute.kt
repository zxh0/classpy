package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/*
StackMapTable_attribute {
    u2              attribute_name_index;
    u4              attribute_length;
    u2              number_of_entries;
    stack_map_frame entries[number_of_entries];
}
 */
class StackMapTableAttribute : AttributeInfo() {

    init {
        u2("number_of_entries");
    }

    override fun readContent(reader: ClassReader) {
        super.readContent(reader);
        // todo
        reader.skipBytes(super.getInt("attribute_length") - 2);
    }

}
