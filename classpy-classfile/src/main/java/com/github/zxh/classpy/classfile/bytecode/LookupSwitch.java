package com.github.zxh.classpy.classfile.bytecode;

import com.github.zxh.classpy.classfile.ClassFilePart;
import com.github.zxh.classpy.classfile.ClassFileReader;
import com.github.zxh.classpy.classfile.jvm.Opcode;

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
public class LookupSwitch extends Instruction {

    //private final List<MatchOffset> matchOffsets = new ArrayList<>();
    
    public LookupSwitch(Opcode opcode, int pc) {
        super(opcode, pc);
    }
    
    @Override
    protected void readOperands(ClassFileReader reader) {
        skipPadding(reader);
        
        MatchOffset defaultOffset = new MatchOffset(true, pc);
        defaultOffset.read(reader);
        
        int npairs = reader.readInt();
        for (int i = 0; i < npairs; i++) {
            MatchOffset offset = new MatchOffset(false, pc);
            offset.read(reader);
            add(offset);
        }
        
        add(defaultOffset);
    }
    
    private void skipPadding(ClassFileReader reader) {
        for (int i = 1; (pc + i) %4 != 0; i++) {
            reader.readByte();
        }
    }

    
    public static class MatchOffset extends ClassFilePart {

        private final boolean isDefault;
        private final int basePc;
        private int match;
        private int offset;

        public MatchOffset(boolean isDefault, int basePc) {
            this.isDefault = isDefault;
            this.basePc = basePc;
        }
        
        @Override
        protected void readContent(ClassFileReader reader) {
            if (!isDefault) {
                match = reader.readInt();
                setName(String.valueOf(match));
            } else {
                setName("default");
            }
            
            offset = reader.readInt();
            setDesc(Integer.toString(basePc + offset));
        }
        
    }
    
}
