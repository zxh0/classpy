package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public class ClassParser {
    
    public static ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        return cf;
    }

}
