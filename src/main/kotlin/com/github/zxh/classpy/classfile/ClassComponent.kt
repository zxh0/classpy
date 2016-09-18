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
abstract class ClassComponent {
    
    var name: String? = null;
    var desc: String? = null; // description
    var offset: Int = 0; // the position of this ClassComponent in the file
    var length: Int = 0; // how many bytes this ClassComponent has
    var subComponents: ArrayList<ClassComponent>? = null;

//    public List<ClassComponent> getSubComponents() {
//        return subComponents == null
//                ? Collections.EMPTY_LIST
//                : Collections.unmodifiableList(subComponents);
//    }

    /**
     * Find sub-component by name.
     * @param name
     * @return
     */
    protected fun get(name: String): ClassComponent? {
        if (subComponents != null) {
            for (c in subComponents!!) {
                if (name.equals(c.name)) {
                    return c;
                }
            }
        }

        return null;
    }

    protected fun getInt(name: String): Int {
        return (get(name) as IntType).value;
    }

    protected fun s1(name: String) {
        this.add(name, S1());
    }

    protected fun s2(name: String) {
        this.add(name, S2());
    }

    protected fun u1(name: String) {
        this.add(name, U1());
    }

    protected fun u1cp(name: String) {
        this.add(name, U1CpIndex());
    }

    protected fun u2(name: String) {
        this.add(name, U2());
    }

    protected fun u2cp(name: String) {
        this.add(name, U2CpIndex());
    }

    protected fun u4(name: String) {
        this.add(name, U4());
    }

    protected fun u4hex(name: String) {
        this.add(name, U4Hex());
    }

    protected fun table(name: String,
                        entryClass: Class<out ClassComponent>) {
        val length = subComponents!![subComponents!!.size - 1] as IntType;
        val table = Table(length, entryClass);
        this.add(name, table);
    }

    protected fun bytes(name: String) {
        val count = subComponents!![subComponents!!.size - 1] as IntType;
        val bytes = Bytes(count);
        this.add(name, bytes);
    }

    protected fun add(subComponent: ClassComponent) {
        this.add(null, subComponent);
    }

    protected fun add(name: String?, subComponent: ClassComponent) {
        if (name != null) {
            subComponent.name = name;
        }
        if (subComponents == null) {
            subComponents = ArrayList();
        }
        subComponents!!.add(subComponent);
    }

    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public fun read(reader: ClassReader) {
        offset = reader.position;
        readContent(reader);
        length = reader.position - offset;
    }
    
    /**
     * Reads content using ClassReader.
     * @param reader 
     */
    protected open fun readContent(reader: ClassReader) {
        if (subComponents != null) {
            for (cc in subComponents!!) {
                cc.read(reader);
            }
        }
    }

    open fun afterRead(cp: ConstantPool) {

    }

    /**
     * The returned string will be displayed by ClassTreeItem.
     *
     * @return
     */
    override fun toString(): String {
        if (name != null && desc != null) {
            return name + ": " + desc;
        }
        if (name != null) {
            return name!!;
        }
        if (desc != null) {
            return desc!!;
        }

        return javaClass.simpleName;
    }

}
