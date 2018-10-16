package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileParser;

public class ClassFileParser implements FileParser {

    public ClassFile parse(byte[] data) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassFileReader(data));
        postRead(cf, cf.getConstantPool());
        return cf;
    }

    private static void postRead(ClassFileComponent fc, ConstantPool cp) {
        for (FileComponent c : fc.getComponents()) {
            postRead((ClassFileComponent) c, cp);
        }
        fc.postRead(cp);
    }

}
