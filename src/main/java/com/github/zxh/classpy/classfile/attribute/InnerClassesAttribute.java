package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.AccessFlags;
import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
InnerClasses_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_classes;
    {   u2 inner_class_info_index;
        u2 outer_class_info_index;
        u2 inner_name_index;
        u2 inner_class_access_flags;
    } classes[number_of_classes];
}
 */
public class InnerClassesAttribute extends AttributeInfo {

    private U2 numberOfClasses;
    private Table<InnerClassInfo> classes;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numberOfClasses = reader.readU2();
        classes = reader.readTable(InnerClassInfo.class, numberOfClasses);
    }
    
    
    public static class InnerClassInfo extends ClassComponent {
        
        private U2CpIndex innerClassInfoIndex;
        private U2CpIndex outerClassInfoIndex;
        private U2CpIndex innerNameIndex;
        private U2 innerClassAccessFlags;

        @Override
        protected void readContent(ClassReader reader) {
            innerClassInfoIndex = reader.readU2CpIndex();
            outerClassInfoIndex = reader.readU2CpIndex();
            innerNameIndex = reader.readU2CpIndex();
            innerClassAccessFlags = reader.readU2();
            AccessFlags.describeInnerClassFlags(innerClassAccessFlags);
        }
        
    }
    
}
