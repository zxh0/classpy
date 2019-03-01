package com.github.zxh.classpy.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for all file parts.
 */
public abstract class FilePart {
    
    private String name;
    private String desc; // description
    private int offset; // the position of this FilePart in the file
    private int length; // how many bytes this FilePart has
    private List<FilePart> parts; // sub-parts
    
    // Getters & Setters
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}
    public final int getOffset() {return offset;}
    public final void setOffset(int offset) {this.offset = offset;}
    public final int getLength() {return length;}
    public final void setLength(int length) {this.length = length;}

    public List<FilePart> getParts() {
        return parts == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(parts);
    }

    /**
     * Find sub-part by name.
     * @param name name of sub-part
     * @return value of sub-part
     */
    protected final FilePart get(String name) {
        for (FilePart c : parts) {
            if (name.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    protected final void add(String name, FilePart subPart) {
        if (name != null) {
            subPart.setName(name);
        }
        if (parts == null) {
            parts = new ArrayList<>();
        }
        parts.add(subPart);
    }

    protected final void clear() {
        parts.clear();
    }

    /**
     * The returned string will be displayed by BytesTreeItem.
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
