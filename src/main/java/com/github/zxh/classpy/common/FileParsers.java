package com.github.zxh.classpy.common;

import com.github.zxh.classpy.classfile.ClassParser;
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
