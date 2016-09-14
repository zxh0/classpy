package com.github.zxh.classpy.classfile.datatype

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.ClassParseException
import com.github.zxh.classpy.classfile.attribute.AttributeFactory
import com.github.zxh.classpy.classfile.constant.ConstantPool
import com.github.zxh.classpy.classfile.reader.ClassReader
import com.github.zxh.classpy.classfile.attribute.AttributeInfo
import com.github.zxh.classpy.helper.StringHelper

/**
 * Array of class components.
 */
class Table(length: UInt, entryClass: Class<out ClassComponent>) : ClassComponent() {

    private val length = length;
    private val entryClass = entryClass;
    
    override fun readContent(reader: ClassReader) {
        try {
            for (i in 0..length.value-1) {
                super.add(readEntry(reader))
            }
        } catch (e: ReflectiveOperationException) {
            throw ClassParseException(e)
        }
    }

    //@Throws(ReflectiveOperationException::class)
    private fun readEntry(reader: ClassReader): ClassComponent {
        if (entryClass == AttributeInfo::class.java) {
            return readAttributeInfo(reader);
        } else {
            println(entryClass)
            val c = entryClass.newInstance()
            c.read(reader)
            return c
        }
    }
    
    private fun readAttributeInfo(reader: ClassReader): AttributeInfo {
        val attrNameIndex = reader.getShort(reader.position).toInt()
        val attrName = reader.constantPool!!.getUtf8String(attrNameIndex)
        
        val attr = AttributeFactory.create(attrName)
        attr.name = attrName
        attr.read(reader)
        
        return attr
    }

    override fun afterRead(cp: ConstantPool) {
        var i = 0
        for (entry in super.getSubComponents()) {
            var newName = StringHelper.formatIndex(length.value, i++)
            val oldName = entry.getName()
            if (oldName != null) {
                newName += " (" + oldName + ")"
            }
            entry.setName(newName)
        }
    }

}
