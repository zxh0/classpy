package com.github.zxh.classpy.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all class file components.
 */
public abstract class BytesComponent {
    
    private String name;
    private String desc; // description
    private int offset; // the position of this ClassComponent in the file
    private int length; // how many bytes this ClassComponent has
    private List<BytesComponent> components;
    
    // Getters & Setters
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}
    public final int getOffset() {return offset;}
    public final void setOffset(int offset) {this.offset = offset;}
    public final int getLength() {return length;}
    public final void setLength(int length) {this.length = length;}

    public List<BytesComponent> getComponents() {
        return components == null
                ? Collections.EMPTY_LIST
                : Collections.unmodifiableList(components);
    }

    /**
     * Find sub-component by name.
     * @param name
     * @return
     */
    protected final BytesComponent get(String name) {
        for (BytesComponent c : components) {
            if (name.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    protected final void add(String name, BytesComponent subComponent) {
        if (name != null) {
            subComponent.setName(name);
        }
        if (components == null) {
            components = new ArrayList<>();
        }
        components.add(subComponent);
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
