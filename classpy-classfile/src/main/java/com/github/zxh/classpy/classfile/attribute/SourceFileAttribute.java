package com.github.zxh.classpy.classfile.attribute;

/*
SourceFile_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 sourcefile_index;
}
 */
public class SourceFileAttribute extends AttributeInfo {

    {
        u2cp("source_file_index");
    }

}
