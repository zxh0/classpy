package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

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
    private InnerClassInfo[] classes;
    
    
    @Override
    protected void readInfo(ClassReader reader) {
        numberOfClasses = reader.readU2();
        classes = new InnerClassInfo[numberOfClasses.getValue()];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = new InnerClassInfo();
            classes[i].read(reader);
        }
    }
    
    public static class InnerClassInfo extends ClassComponent {
        
        private U2 innerClassInfoIndex;
        private U2 outerClassInfoIndex;
        private U2 innerNameIndex;
        private U2 innerClassAccessFlags;

        @Override
        protected void readContent(ClassReader reader) {
            innerClassInfoIndex = reader.readU2();
            outerClassInfoIndex = reader.readU2();
            innerNameIndex = reader.readU2();
            innerClassAccessFlags = reader.readU2();
        }
        
    }
    
}
