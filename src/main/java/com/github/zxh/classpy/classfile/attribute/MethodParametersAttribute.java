package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
MethodParameters_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 parameters_count;
    {   u2 name_index;
        u2 access_flags;
    } parameters[parameters_count];
}
 */
public class MethodParametersAttribute extends AttributeInfo {

    private U1 parametersCount;
    private Table<ParameterInfo> parameters;
    
    @Override
    protected void readInfo(ClassReader reader) {
        parametersCount = reader.readU1();
        parameters = reader.readTable(ParameterInfo.class, parametersCount);
    }
    
    
    public static class ParameterInfo extends ClassComponent {

        private U2 nameIndex;
        private U2 accessFlags;
        
        @Override
        protected void readContent(ClassReader reader) {
            nameIndex = reader.readU2();
            accessFlags = reader.readU2();
        }
        
    }
    
}
