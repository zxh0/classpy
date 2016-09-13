package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.reader.ClassReader;

public class ClassParser {
    
    public static ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        afterRead(cf, cf.getConstantPool());
        return cf;
    }

    private static void afterRead(ClassComponent cc, ConstantPool cp) {
        for (ClassComponent c : cc.getSubComponents()) {
            afterRead(c, cp);
        }
        cc.afterRead(cp);
    }

}
