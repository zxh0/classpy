package com.github.zxh.classpy.dex;

import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all .dex file components.
 * 
 * @author zxh
 */
public abstract class DexComponent {
    
    private int offset; // the position of this ClassComponent in class file
    private int length; // how many bytes this ClassComponent has
    private String name;
    private String desc; // description

    // Getters & Setters
    public final int getOffset() {return offset;}
    public final int getLength() {return length;}
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(DexReader reader) {
        offset = reader.getPosition();
        readContent(reader);
        length = reader.getPosition() - offset;
    }
    
    /**
     * Reads content using DexReader.
     * @param reader 
     */
    protected abstract void readContent(DexReader reader);
    
    /**
     * Returns sub-components.
     * This is the default implementation, subclass which really has sub-components
     * should override this.
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<? extends DexComponent> getSubComponents() {
        return Collections.EMPTY_LIST;
    }
    
    /**
     * The returned string will be displayed by ClassComponentTreeItem.
     * @return 
     */
    @Override
    public final String toString() {
        if (name != null && desc != null) {
            return name + ": " + desc;
        }
        if (name != null) {
            return name;
        }
        if (desc != null) {
            return desc;
        }
        
        return getClass().getSimpleName();
    }
    
}
