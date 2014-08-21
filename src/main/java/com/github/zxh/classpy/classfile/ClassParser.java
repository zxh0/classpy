package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.common.FileComponentHelper;

/**
 *
 * @author zxh
 */
public class ClassParser {
    
    public static ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        
        try {
            FileComponentHelper.inferSubComponentName(cf);
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
        
        return cf;
    }

}
