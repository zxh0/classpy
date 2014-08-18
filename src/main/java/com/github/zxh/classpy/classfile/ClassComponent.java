package com.github.zxh.classpy.classfile;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author zxh
 */
public abstract class ClassComponent {
    
    private int offset;
    private int length;
    private String name;
    private String desc;

    public final int getOffset() {return offset;}
    public final int getLength() {return length;}
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}

    public void read(ClassReader reader) {
        offset = reader.getPosition();
        readContent(reader);
        length = reader.getPosition() - offset;
    }
    
    protected abstract void readContent(ClassReader reader);
    
    @SuppressWarnings("unchecked")
    public List<? extends ClassComponent> getSubComponents() {
        return Collections.EMPTY_LIST;
    }
    
    @Override
    public String toString() {
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
