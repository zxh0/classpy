package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;

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
    private BootstrapMethodInfo[] bootstrapMethods;
    
    @Override
    protected void readInfo(ClassReader reader) {
        numBootstrapMethods = reader.readU2();
        bootstrapMethods = new BootstrapMethodInfo[numBootstrapMethods.getValue()];
        for (int i = 0; i < bootstrapMethods.length; i++) {
            bootstrapMethods[i] = new BootstrapMethodInfo();
            bootstrapMethods[i].read(reader);
        }
    }
    
    public static class BootstrapMethodInfo extends ClassComponent {
        
        private U2  bootstrapMethodRef;
        private U2  numBootstrapArguments;
        private U2[] bootstrapArguments;
        
        @Override
        protected void readContent(ClassReader reader) {
            bootstrapMethodRef = reader.readU2();
            numBootstrapArguments = reader.readU2();
            bootstrapArguments = new U2[numBootstrapArguments.getValue()];
            for (int i = 0; i < bootstrapArguments.length; i++) {
                bootstrapArguments[i] = reader.readU2();
            }
        }
        
    }
    
}
