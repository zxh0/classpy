package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class PeParser implements FileParser {

    @Override
    public FileComponent parse(byte[] bytes) {
        PeFile pe = new PeFile();
        pe.read(new PeReader(bytes));
        return pe;
    }
    
}
