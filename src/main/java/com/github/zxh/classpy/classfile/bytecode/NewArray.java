package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.reader.ClassReader;

public class NewArray extends Instruction {

    public NewArray(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassReader reader) {
        int atype = reader.readU1().getValue();
        setDesc(getDesc() + " " + getArrayType(atype));
    }
    
    private static String getArrayType(int atype) {
        switch (atype) {
            case  4: return "boolean";
            case  5: return "char";
            case  6: return "float";
            case  7: return "double";
            case  8: return "byte";
            case  9: return "short";
            case 10: return "int";
            case 11: return "long";
            default: throw new ClassParseException("Invalid atype: " + atype);
        }
    }
    
}
