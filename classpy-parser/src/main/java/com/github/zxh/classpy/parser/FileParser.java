package com.github.zxh.classpy.parser;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.parser.format.FileFormatDef;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileParser {

    private final FileFormatDef ffDef;

    public static FileParser load(String jsonResFile) throws IOException  {
        URL url = FileParser.class.getResource("/java_class.json");
        try (InputStream is = url.openStream()) {
            JsonObject jsonObj = new Gson().fromJson(new InputStreamReader(is), JsonObject.class);
            return new FileParser(jsonObj);
        }
    }

    public FileParser(JsonObject ffJson) {
        this.ffDef = new FileFormatDef(ffJson);
    }

    public FilePart2 parse(BytesReader reader) {
        FilePart2 fp = new FilePart2();



        // TODO

        return fp;
    }

}
