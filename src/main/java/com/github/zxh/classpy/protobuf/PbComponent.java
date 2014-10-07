package com.github.zxh.classpy.protobuf;

import com.github.zxh.classpy.common.FileComponent;

/**
 * 
 * @author zxh
 */
public abstract class PbComponent extends FileComponent {

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(PbReader reader) {
        startRead(reader.getPosition());
        readContent(reader);
        endRead(reader.getPosition());
    }
    
    /**
     * Reads content using PbReader.
     * @param reader 
     */
    protected abstract void readContent(PbReader reader);
    
}
