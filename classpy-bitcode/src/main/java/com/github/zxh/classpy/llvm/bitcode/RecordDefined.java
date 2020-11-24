package com.github.zxh.classpy.llvm.bitcode;

import com.github.zxh.classpy.common.ParseException;

// https://llvm.org/docs/BitCodeFormat.html#abbreviated-record-encoding
// [<abbrevid>, fields...]
public class RecordDefined extends BitCodePart {

    private final DefineAbbrev def;

    public RecordDefined(DefineAbbrev def) {
        this.def = def;
    }

    @Override
    protected void readContent(BitCodeReader reader) {
        for (var field : def.fields) {
            readField(reader, field);
        }
    }

    private void readField(BitCodeReader reader,
                           DefineAbbrev.Field field) {
        switch (field.coding) {
            case DefineAbbrev.FIXED -> reader.readFixed(field.width);
            case DefineAbbrev.VBR -> reader.readVBR(field.width);
            case DefineAbbrev.ARRAY -> readArray(reader, field.elemType);
            case DefineAbbrev.CHAR6 -> throw new ParseException("TODO: char6");
            case DefineAbbrev.BLOB -> throw new ParseException("TODO: blob");
            default -> throw new ParseException("unknown coding: " + field.coding);
        }
    }

    private void readArray(BitCodeReader reader,
                           DefineAbbrev.Field elemType) {
        if (elemType.coding != DefineAbbrev.CHAR6) {
            throw new ParseException("TODO: array elem type is " + elemType.coding);
        }

        int len = (int) reader.readVBR(6);
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            int x = (int) reader.readFixed(6);
            if (0 <= x && x <= 25) {
                chars[i] = (char) ('a' + x);
            } else if (26 <= x && x <= 51) {
                chars[i] = (char) ('A' + x - 26);
            } else if (52 <= x && x <= 61) {
                chars[i] = (char) ('0' + x - 52);
            } else if (x == 62) {
                chars[i] = '.';
            } else if (x == 63) {
                chars[i] = '_';
            }
        }
        setDesc(new String(chars));
    }

}
