package com.github.zxh.classpy.common;

import com.github.zxh.classpy.classfile.ClassParser;
import com.github.zxh.classpy.dexfile.DexParser;
import com.github.zxh.classpy.pecoff.PeParser;
import com.github.zxh.classpy.protobuf.PbParser;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zxh
 */
public class FileParsers {
    
    private static final Map<String, FileParser> PARSERS = new HashMap<>();
    static {
        PARSERS.put(".class", new ClassParser());
        PARSERS.put(".dex", new DexParser());
        PARSERS.put(".exe", new PeParser());
        PARSERS.put(".pb", new PbParser());
    }
    
    private static final FileParser UNSUPPORTED_FILE_PARSER = f -> {
        FileComponent fc = new FileComponent();
        fc.setName("UnsupportedFile");
        return fc;
    };
    
    public static FileParser getParser(String fileType) {
        if (PARSERS.containsKey(fileType)) {
            return PARSERS.get(fileType);
        } else {
            return UNSUPPORTED_FILE_PARSER;
        }
    }
    
}
