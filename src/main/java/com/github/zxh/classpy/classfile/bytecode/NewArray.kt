package com.github.zxh.classpy.classfile.bytecode

import com.github.zxh.classpy.classfile.ClassParseException
import com.github.zxh.classpy.classfile.constant.ConstantPool

class NewArray(opcode: Opcode, pc: Int) : Instruction(opcode, pc) {

    init {
        u1("opcode");
        u1("atype");
    }
    
    override fun afterRead(cp: ConstantPool) {
        val atype = getArrayType(super.getInt("atype"))
        desc = "${opcode.name} $atype"
    }
    
    private fun getArrayType(atype: Int): String {
        return when (atype) {
             4 -> "boolean";
             5 -> "char";
             6 -> "float";
             7 -> "double";
             8 -> "byte";
             9 -> "short";
            10 -> "int";
            11 -> "long";
            else -> throw ClassParseException("Invalid atype: " + atype);
        }
    }
    
}
