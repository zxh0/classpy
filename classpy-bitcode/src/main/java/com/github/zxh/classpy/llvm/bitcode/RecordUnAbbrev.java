package com.github.zxh.classpy.llvm.bitcode;

// https://llvm.org/docs/BitCodeFormat.html#unabbrev-record
// [UNABBREV_RECORD, code(vbr6), numops(vbr6), op0(vbr6), op1(vbr6), …]
public class RecordUnAbbrev extends BitCodePart {

    long code;

    @Override
    protected void readContent(BitCodeReader reader) {
        code = reader.readVBR(6);
        long numOps = reader.readVBR(6);
        add("code", new VBR(code));
        add("numOps", new VBR(numOps));

        for (int i = 0; i < numOps; i++) {
            long op = reader.readVBR(6);
            add("op#" + i, new VBR(op));
        }
    }

}
