package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileParser;

public class ClassFileParser implements FileParser {
    
    public ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassFileReader(bytes));
        postRead(cf, cf.getConstantPool());
        return cf;
    }

    private static void postRead(ClassFileComponent cc, ConstantPool cp) {
        for (FileComponent c : cc.getComponents()) {
            postRead((ClassFileComponent) c, cp);
        }
        cc.postRead(cp);
    }

}
