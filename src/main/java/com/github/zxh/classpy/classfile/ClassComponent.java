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

    public int getOffset() {return offset;}
    public int getLength() {return length;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDesc() {return desc;}
    public void setDesc(String desc) {this.desc = desc;}

    public void read(ClassReader reader) {
        offset = reader.getPosition();
        readContent(reader);
        length = reader.getPosition() - offset;
    }
    
    @SuppressWarnings("unchecked")
    public List<ClassComponent> getSubComponents() {
        return Collections.EMPTY_LIST;
    }
    
    protected abstract void readContent(ClassReader reader);
    
    @Override
    public String toString() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        
        return desc == null ? name : name + ": " + desc;
    }
    
}
