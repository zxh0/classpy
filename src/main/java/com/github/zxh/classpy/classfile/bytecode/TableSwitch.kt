package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.reader.ClassReader

/*
    tableswitch
    <0-3 byte pad>
    defaultbyte1
    defaultbyte2
    defaultbyte3
    defaultbyte4
    lowbyte1
    lowbyte2
    lowbyte3
    lowbyte4
    highbyte1
    highbyte2
    highbyte3
    highbyte4
    jump offsets...
 */
class TableSwitch(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    override fun readOperands(reader: ClassReader) {
        skipPadding(reader)
        
        val defaultOffset = readJumpOffset(reader, "default")
        
        val low = reader.readInt()
        val high = reader.readInt()
        
        // high - low + 1 signed 32-bit offsets
        for (i in low..high) {
            val offset = readJumpOffset(reader, i.toString())
            add(offset)
        }

        add(defaultOffset)
    }

    private fun skipPadding(reader: ClassReader) {
        var i = 1
        while ((pc + i) % 4 !== 0) {
            reader.readByte()
            i++
        }
    }


    private fun readJumpOffset(reader: ClassReader, name: String): JumpOffset {
        val offset = JumpOffset()
        offset.read(reader)
        offset.setName(name)
        offset.setDesc(Integer.toString(pc + offset._offset))
        return offset
    }
    
}

class JumpOffset() : ClassComponent() {

    var _offset: Int = 0

    override fun readContent(reader: ClassReader) {
        _offset = reader.readInt()
    }

}