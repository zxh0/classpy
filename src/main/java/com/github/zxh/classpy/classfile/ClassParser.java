package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.FileComponentHelper;
import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class ClassParser implements FileParser {
    
    @Override
    public ClassFile parse(byte[] bytes) throws FileParseException {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        
        try {
            FileComponentHelper.inferSubComponentName(cf);
        } catch (ReflectiveOperationException e) {
            throw new FileParseException(e);
        }
        
        return cf;
    }

}
