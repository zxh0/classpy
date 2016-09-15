package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.ClassComponent
import com.github.zxh.classpy.classfile.reader.ClassReader

/*
lookupswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
npairs1
npairs2
npairs3
npairs4
match-offset pairs...
 */
class LookupSwitch(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {


    override fun readOperands(reader: ClassReader) {
        skipPadding(reader)
        
        val defaultOffset = MatchOffset(true, pc)
        defaultOffset.read(reader)
        
        val npairs = reader.readInt()
        for (i in 0..npairs-1) {
            val offset = MatchOffset(false, pc)
            offset.read(reader)
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
    
}

class MatchOffset(val isDefault: Boolean, val basePc: Int) : ClassComponent() {

    var match: Int = 0
    var _offset: Int = 0

    override fun readContent(reader: ClassReader) {
        if (!isDefault) {
            match = reader.readInt()
            setName(match.toString())
        } else {
            setName("default")
        }

        _offset = reader.readInt()
        setDesc(Integer.toString(basePc + offset))
    }

}