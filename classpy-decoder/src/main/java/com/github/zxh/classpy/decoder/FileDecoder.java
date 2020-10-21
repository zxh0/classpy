package com.github.zxh.classpy.decoder;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.common.FilePartImpl;
import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.decoder.format.*;
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
    private BytesReader bytesReader;

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

    public FilePart decode(byte[] data) {
        bytesReader = new BytesReader(data, ffDef.getByteOrder());
        return readType(ffDef.getMainType());
    }

    private FilePart readType(String name) {
        //System.out.println("read " + name);
        if (ffDef.hasTypeDef(name)) {
            return readDefinedType(name);
        } else {
            return readBuiltinType(name);
        }
    }

    private FilePart readBuiltinType(String typeName) {
        return builtinTypeReader.read(bytesReader, typeName);
    }

    private FilePart readDefinedType(String typeName) {
        TypeDef typeDef = ffDef.getTypeDef(typeName);
        if (typeDef instanceof StructTypeDef structTypeDef) {
            return readStructType(structTypeDef);
        } else if (typeDef instanceof TaggedTypeDef taggedTypeDef) {
            return readTaggedType(taggedTypeDef);
        } else if (typeDef instanceof NamedTypeDef namedTypeDef) {
            return readNamedType(namedTypeDef);
        } else {
            throw new ParseException("unreachable!");
        }
    }

    private FilePart readStructType(StructTypeDef typeDef) {
        FilePartImpl part = new FilePartImpl();
        for (FieldDef fieldDef :  typeDef.getFields()) {
            //System.out.println("read " + fieldDef.getName());
            FilePart field = readField(fieldDef, part);
            part.add(fieldDef.getName(), field);
        }
        return part;
    }
    private FilePart readTaggedType(TaggedTypeDef typeDef) {
        FilePartImpl part = new FilePartImpl();
        // TODO
        throw new RuntimeException("TODO~");
    }
    private FilePart readNamedType(NamedTypeDef typeDef) {
        FilePartImpl part = new FilePartImpl();
        // TODO
        throw new RuntimeException("TODO~");
    }

    private FilePart readField(FieldDef fieldDef, FilePart parent) {
        if (fieldDef.getListWithoutLenTypeDef() != null) {
            return readListWithoutLenType(fieldDef.getListWithoutLenTypeDef(), parent);
        } else {
            return readType(fieldDef.getType());
        }
    }

    private FilePart readListWithoutLenType(ListWithoutLenTypeDef type, FilePart parent) {
        FilePart lenField = parent.get(type.getLenFieldName());
        if (!(lenField instanceof IntValue intVal)) {
            throw new DecodeException(lenField.getClass() + " does not implements " + IntValue.class);
        }

        FilePart part = new FilePartImpl();
        int n = intVal.getValue();
        if (type.hasMinusOne()) {
            n--;
        }
        for (int i = 0; i < n; i++) {
            FilePart elem = readType(type.getElemTypeName());
            part.add("?", elem);
        }
        return part;
    }

}
