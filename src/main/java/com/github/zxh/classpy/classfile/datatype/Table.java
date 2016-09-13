package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.attribute.AttributeFactory;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.helper.StringUtil;
import com.github.zxh.classpy.classfile.attribute.AttributeInfo;

/**
 * Array of class components.
 */
public class Table extends ClassComponent {

    private final UInt length;
    private final Class<? extends ClassComponent> entryClass;

    public Table(UInt length, Class<? extends ClassComponent> entryClass) {
        this.length = length;
        this.entryClass = entryClass;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        try {
            for (int i = 0; i < length.getValue(); i++) {
                super.add(readEntry(reader));
            }
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
    }

    private ClassComponent readEntry(ClassReader reader) throws ReflectiveOperationException {
        if (entryClass == AttributeInfo.class) {
            return readAttributeInfo(reader);
        } else {
            ClassComponent c = entryClass.newInstance();
            c.read(reader);
            return c;
        }
    }
    
    private AttributeInfo readAttributeInfo(ClassReader reader) {
        int attrNameIndex = reader.getByteBuffer().getShort(reader.getPosition());
        String attrName = reader.getConstantPool().getUtf8String(attrNameIndex);
        
        AttributeInfo attr = AttributeFactory.create(attrName);
        attr.setName(attrName);
        attr.read(reader);
        
        return attr;
    }

    @Override
    protected void afterRead(ConstantPool cp) {
        int i = 0;
        for (ClassComponent entry : super.getSubComponents()) {
            String newName = StringUtil.formatIndex(length.getValue(), i++);
            String oldName = entry.getName();
            if (oldName != null) {
                newName += " (" + oldName + ")";
            }
            entry.setName(newName);
        }
    }

}
