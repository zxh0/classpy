package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.FileComponent;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all .dex file components.
 * 
 * @author zxh
 */
public abstract class DexComponent extends FileComponent {

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(DexReader reader) {
        startRead(reader.getPosition());
        readContent(reader);
        endRead(reader.getPosition());
    }
    
    /**
     * Reads content using DexReader.
     * @param reader 
     */
    protected abstract void readContent(DexReader reader);
    
    // todo
    protected void postRead(DexFile dexFile) {
        getSubComponents().forEach(sub -> {
            sub.postRead(dexFile);
        });
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<? extends DexComponent> getSubComponents() {
        return Collections.EMPTY_LIST;
    }
    
}
