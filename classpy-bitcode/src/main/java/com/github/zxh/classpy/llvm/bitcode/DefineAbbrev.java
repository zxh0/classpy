package com.github.zxh.classpy.llvm.bitcode;

import com.github.zxh.classpy.common.ParseException;

import java.util.ArrayList;
import java.util.List;

// https://llvm.org/docs/BitCodeFormat.html#define-abbrev
// [DEFINE_ABBREV, numabbrevops(vbr5), abbrevop0, abbrevop1, …]
public class DefineAbbrev extends BitCodePart {

    // operand encodings
    static final int FIXED = 1;
    static final int VBR   = 2;
    static final int ARRAY = 3;
    static final int CHAR6 = 4;
    static final int BLOB  = 5;

    static class Field {
        int coding;
        int width;
        Field elemType;
    }

    final List<Field> fields = new ArrayList<>();

    private void addField(Field f) {
        if (fields.size() > 0) {
            Field lastField = fields.get(fields.size() - 1);
            if (lastField.coding == ARRAY) {
                lastField.elemType = f;
                return;
            }
        }
        fields.add(f);
    }

    @Override
    protected void readContent(BitCodeReader reader) {
        readDefineAbbrevOps(reader);
    }

    private void readDefineAbbrevOps(BitCodeReader reader) {
        long numAbbrevOps = reader.readVBR(5);
        add("numAbbrevOp", new VBR(numAbbrevOps));

        System.out.println("numAbbrevOp=" + numAbbrevOps);
        for (int i = 0; i < numAbbrevOps; i++) {
            readDefineAbbrevOp(reader, i);
        }
    }

    private void readDefineAbbrevOp(BitCodeReader reader, int idx) {
        long b = reader.readFixed(1);
        if (b == 1) {
            long litVal = reader.readVBR(8);
            add("op#" + idx + "(Literal)", new VBR(litVal));
            return;
        }

        Field field = new Field();
        field.coding = (int) reader.readFixed(3);
        switch (field.coding) {
            case FIXED -> {
                field.width = (int) reader.readVBR(5);
                add("op#" + idx + "(Fixed)", new VBR(field.width));
            }
            case VBR -> {
                field.width = (int) reader.readVBR(5);
                add("op#" + idx + "(VBR)", new VBR(field.width));
            }
            case ARRAY -> add("op#" + idx + "(Array)", new Dummy());
            case CHAR6 -> add("op#" + idx + "(CHAR6)", new Dummy());
            case BLOB -> add("op#" + idx + "(BLOB)", new Dummy());
            default -> throw new ParseException("Unknown operand encoding: " + field.coding);
        }
        addField(field);
    }

}
