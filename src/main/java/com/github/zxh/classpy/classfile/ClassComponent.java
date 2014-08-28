package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.common.FileComponent;

/**
 * Abstract base class for all class file components.
 * 
 * @author zxh
 */
public abstract class ClassComponent extends FileComponent {

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(ClassReader reader) {
        startRead(reader.getPosition());
        readContent(reader);
        endRead(reader.getPosition());
    }
    
    /**
     * Reads content using ClassReader.
     * @param reader 
     */
    protected abstract void readContent(ClassReader reader);
    
}
