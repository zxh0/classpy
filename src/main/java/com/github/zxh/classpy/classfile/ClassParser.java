package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class ClassParser implements FileParser {
    
    @Override
    public ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        return cf;
    }

}
