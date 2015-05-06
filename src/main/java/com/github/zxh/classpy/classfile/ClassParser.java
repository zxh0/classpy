package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.reader.ClassReader;

public class ClassParser {
    
    public static ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        return cf;
    }

}
