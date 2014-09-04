package com.github.zxh.classpy.common;

import java.util.Collections;
import java.util.List;

/**
 * A part of a file.
 * 
 * @author zxh
 */
public class FileComponent {
    
    private String name;
    private String desc; // description
    private int offset; // the position of this FileComponent in the file
    private int length; // how many bytes this FileComponent has
    
    // Getters & Setters
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}
    public final int getOffset() {return offset;}
    public final int getLength() {return length;}
    
    public final void setDesc(int value) {
        desc = Integer.toString(value);
    }
    
    protected final void startRead(int position) {
        offset = position;
    }
    
    protected final void endRead(int position) {
        length = position - offset;
    }
    
    /**
     * Returns sub-components.
     * 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<? extends FileComponent> getSubComponents() {
        try {
            return FileComponentHelper.findSubComponents(this);
        } catch (ReflectiveOperationException e) {
            // todo
            e.printStackTrace(System.err);
            return Collections.EMPTY_LIST;
        }
    }
    
    /**
     * The returned string will be displayed by FileComponentTreeItem.
     * 
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
