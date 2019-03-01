package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.datatype.*;
import com.github.zxh.classpy.common.FilePart;

/**
 * Base class for all class file parts.
 */
public abstract class ClassFilePart extends FilePart {

    /**
     * Reads content, records offset and length.
     * @param reader
     */
    public final void read(ClassFileReader reader) {
        try {
            int offset = reader.getPosition();
            readContent(reader);
            int length = reader.getPosition() - offset;
            super.setOffset(offset);
            super.setLength(length);
        } catch (Exception e) {
            System.out.println("error parsing: " + getClass());
            throw e;
        }
    }

    /**
     * Reads content using ClassFileReader.
     * @param reader
     */
    protected void readContent(ClassFileReader reader) {
        for (FilePart fc : getParts()) {
            ((ClassFilePart) fc).read(reader);
        }
    }

    protected void postRead(ConstantPool cp) {

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

    protected final void u2af(String name, int afType) {
        this.add(name, new U2AccessFlags(afType));
    }

    protected final void u4(String name) {
        this.add(name, new U4());
    }

    protected final void u4hex(String name) {
        this.add(name, new U4Hex());
    }

    protected final void table(String name,
                               Class<? extends ClassFilePart> entryClass) {
        UInt length = (UInt) getParts().get(getParts().size() - 1);
        Table table = new Table(length, entryClass);
        this.add(name, table);
    }

    protected final void bytes(String name) {
        UInt count = (UInt) getParts().get(getParts().size() - 1);
        Bytes bytes = new Bytes(count);
        this.add(name, bytes);
    }

    protected final void add(ClassFilePart subPart) {
        this.add(null, subPart);
    }

}
