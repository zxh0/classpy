package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.*;
import com.github.zxh.classpy.classfile.reader.ClassReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all class file components.
 */
public abstract class ClassComponent {
    
    private String name;
    private String desc; // description
    private int offset; // the position of this ClassComponent in the file
    private int length; // how many bytes this ClassComponent has
    private List<ClassComponent> subComponents;
    
    // Getters & Setters
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}
    public final int getOffset() {return offset;}
    public final int getLength() {return length;}

    public List<ClassComponent> getSubComponents() {
        return subComponents == null
                ? Collections.EMPTY_LIST
                : Collections.unmodifiableList(subComponents);
    }

    /**
     * Find sub-component by name.
     * @param name
     * @return
     */
    protected final ClassComponent get(String name) {
        for (ClassComponent c : subComponents) {
            if (name.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    protected int getUInt(String name) {
        return ((UInt) get(name)).getValue();
    }

    protected final void u1(String name) {
        this.add(name, new U1());
    }

    protected final void u1cp(String name) {
        this.add(name, new U1CpIndex());
    }

    protected final void u2(String name) {
        this.add(name, new U2());
    }

    protected final void u2cp(String name) {
        this.add(name, new U2CpIndex());
    }

    protected final void u4(String name) {
        this.add(name, new U4());
    }

    protected final void u4hex(String name) {
        this.add(name, new U4Hex());
    }

    protected final void table(String name,
                               Class<? extends ClassComponent> entryClass) {
        UInt length = (UInt) subComponents.get(subComponents.size() - 1);
        Table table = new Table(length, entryClass);
        this.add(name, table);
    }

    protected final void bytes(String name) {
        UInt count = (UInt) subComponents.get(subComponents.size() - 1);
        Bytes bytes = new Bytes(count);
        this.add(name, bytes);
    }

    protected final void add(ClassComponent subComponent) {
        this.add(null, subComponent);
    }

    protected final void add(String name, ClassComponent subComponent) {
        if (name != null) {
            subComponent.setName(name);
        }
        if (subComponents == null) {
            subComponents = new ArrayList<>();
        }
        subComponents.add(subComponent);
    }

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(ClassReader reader) {
        offset = reader.getPosition();
        readContent(reader);
        length = reader.getPosition() - offset;
    }
    
    /**
     * Reads content using ClassReader.
     * @param reader 
     */
    protected void readContent(ClassReader reader) {
        if (subComponents != null) {
            for (ClassComponent cc : subComponents) {
                cc.read(reader);
            }
        }
    }

    protected void afterRead(ConstantPool cp) {

    }

    /**
     * The returned string will be displayed by ClassTreeItem.
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
