package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.common.FileComponent;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author zxh
 */
public abstract class PeComponent extends FileComponent {

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(PeReader reader) {
        startRead(reader.getPosition());
        readContent(reader);
        endRead(reader.getPosition());
    }
    
    /**
     * Reads content using PeReader.
     * @param reader 
     */
    protected abstract void readContent(PeReader reader);
    
    @Override
    @SuppressWarnings("unchecked")
    public List<? extends PeComponent> getSubComponents() {
        return Collections.EMPTY_LIST;
    }
    
}
