package com.github.zxh.classpy.dexfile.bytecode;

import static com.github.zxh.classpy.dexfile.bytecode.InstructionFormat.*;
/**
 *
 * http://source.android.com/devices/tech/dalvik/dalvik-bytecode.html
 * 
 * @author pc
 */
public enum InstructionSet {
    
    nop(0x00, _10x);
    
    
    public final int op;
    public final InstructionFormat format;

    private InstructionSet(int op, InstructionFormat format) {
        this.op = op;
        this.format = format;
    }
    
}
