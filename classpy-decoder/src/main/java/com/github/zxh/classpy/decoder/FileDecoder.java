package com.github.zxh.classpy.decoder;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.common.FilePartImpl;
import com.github.zxh.classpy.decoder.format.FieldDef;
import com.github.zxh.classpy.decoder.format.FileFormatDef;
import com.github.zxh.classpy.decoder.format.ListWithoutLenType;
import com.github.zxh.classpy.decoder.format.TypeDef;
import com.github.zxh.classpy.decoder.types.builtin.BuiltinTypeReader;
import com.github.zxh.classpy.decoder.types.builtin.IntValue;
import com.github.zxh.classpy.decoder.types.builtin.javaclass.JavaBuiltinTypeReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileDecoder {

    private final FileFormatDef ffDef;
    private BuiltinTypeReader builtinTypeReader;

    public static FileDecoder load(String jsonResFile) throws IOException  {
        URL url = FileDecoder.class.getResource("/java_class_file.json");
        try (InputStream is = url.openStream()) {
            JsonObject jsonObj = new Gson().fromJson(new InputStreamReader(is), JsonObject.class);
            return new FileDecoder(jsonObj);
        }
    }

    public FileDecoder(JsonObject ffJson) {
        this.ffDef = new FileFormatDef(ffJson);
        this.builtinTypeReader = new JavaBuiltinTypeReader(); // TODO
    }

    public FilePartImpl parse(byte[] data) {
        BytesReader reader = new BytesReader(data, ffDef.getByteOrder());
        return readCustomType(reader, ffDef.getMainType());
    }

    private FilePartImpl readCustomType(BytesReader reader, String typeName) {
        FilePartImpl part = new FilePartImpl();
        TypeDef typeDef = ffDef.getTypeDef(typeName);
        for (FieldDef fieldDef :  typeDef.getFields()) {
            FilePart field = readField(reader, fieldDef, part);
            part.add(fieldDef.getName(), field);
        }
        return part;
    }
    private FilePart readField(BytesReader reader, FieldDef fieldDef, FilePart parent) {
        if (fieldDef.getListWithoutLenType() != null) {
            return readListWithoutLenType(reader, fieldDef.getListWithoutLenType(), parent);
        } else if (ffDef.hasTypeDef(fieldDef.getType())) {
            return readCustomType(reader, fieldDef.getType());
        } else {
            return readBuiltinType(reader, fieldDef.getType());
        }
    }

    private FilePart readListWithoutLenType(BytesReader reader,
                                            ListWithoutLenType type, FilePart parent) {
        FilePart lenField = parent.get(type.getLenFieldName());
        if (lenField instanceof IntValue intVal) {
            intVal.getValue();
        }
        throw new DecodeException("TODO~" + lenField.getClass());
    }

    private FilePart readBuiltinType(BytesReader reader, String typeName) {
        return builtinTypeReader.read(reader, typeName);
    }

}
