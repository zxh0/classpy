package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.Table;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
BootstrapMethods_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 num_bootstrap_methods;
    {   u2 bootstrap_method_ref;
        u2 num_bootstrap_arguments;
        u2 bootstrap_arguments[num_bootstrap_arguments];
    } bootstrap_methods[num_bootstrap_methods];
}
 */
public class BootstrapMethodsAttribute extends AttributeInfo {

    private U2 numBootstrapMethods;
    private Table<BootstrapMethodInfo> bootstrapMethods;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numBootstrapMethods = reader.readU2();
        bootstrapMethods = reader.readTable(BootstrapMethodInfo.class,
                numBootstrapMethods);
    }
    
    
    public static class BootstrapMethodInfo extends ClassComponent {
        
        private U2CpIndex bootstrapMethodRef;
        private U2 numBootstrapArguments;
        private Table<U2CpIndex> bootstrapArguments;
        
        @Override
        protected void readContent(ClassReader reader) {
            bootstrapMethodRef = reader.readU2CpIndex();
            numBootstrapArguments = reader.readU2();
            bootstrapArguments = reader.readTable(U2CpIndex.class,
                    numBootstrapArguments);
            //setDesc(bootstrapMethodRef.getDesc());
        }
        
    }
    
}
