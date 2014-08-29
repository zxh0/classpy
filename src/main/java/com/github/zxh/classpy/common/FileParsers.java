package com.github.zxh.classpy.common;

import com.github.zxh.classpy.classfile.ClassParser;
import com.github.zxh.classpy.dexfile.DexParser;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zxh
 */
public class FileParsers {
    
    private static final Map<String, FileParser> parsers = new HashMap<>();
    static {
        parsers.put(".class", new ClassParser());
        parsers.put(".dex", new DexParser());
    }
    
    
    public static FileParser getParser(String fileType) {
        if (! parsers.containsKey(fileType)) {
            throw new FileParseException("Unsupported file type: " + fileType);
        }
        
        return parsers.get(fileType);
    }
    
}
