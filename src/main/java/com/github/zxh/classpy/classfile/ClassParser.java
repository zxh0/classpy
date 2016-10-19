package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.common.BytesParser;

public class ClassParser implements BytesParser {
    
    public ClassFile parse(byte[] bytes) {
        ClassFile cf = new ClassFile();
        cf.read(new ClassReader(bytes));
        afterRead(cf, cf.getConstantPool());
        return cf;
    }

    private static void afterRead(ClassComponent cc, ConstantPool cp) {
        for (BytesComponent c : cc.getComponents()) {
            afterRead((ClassComponent) c, cp);
        }
        cc.afterRead(cp);
    }

}
