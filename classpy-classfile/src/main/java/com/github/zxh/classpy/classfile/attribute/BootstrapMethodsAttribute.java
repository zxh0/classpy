package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassFilePart;
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

    {
        u2   ("num_bootstrap_methods");
        table("bootstrap_methods", BootstrapMethodInfo.class);
    }

    
    public static class BootstrapMethodInfo extends ClassFilePart {

        {
            u2cp ("bootstrap_method_ref");
            u2   ("num_bootstrap_arguments");
            table("bootstrap_arguments", U2CpIndex.class);
        }
        
    }
    
}
