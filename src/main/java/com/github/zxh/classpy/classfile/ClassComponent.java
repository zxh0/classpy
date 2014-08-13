package com.github.zxh.classpy.classfile;

/**
 *
 * @author zxh
 */
public abstract class ClassComponent {
    
    private int offset;
    private int length;
    private String name;

    public int getOffset() {return offset;}
    public int getLength() {return length;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public void read(ClassReader reader) {
        offset = reader.position();
        readContent(reader);
        length = reader.position() - offset;
    }
    
    protected abstract void readContent(ClassReader reader);
    
    @Override
    public String toString() {
        return name != null ? name : getClass().getSimpleName();
    }
    
}
