package com.github.zxh.classpy.pecoff;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileComponentHelper;
import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.common.FileParser;

/**
 *
 * @author zxh
 */
public class PeParser implements FileParser {

    @Override
    public FileComponent parse(byte[] bytes) throws FileParseException {
        PeFile pe = new PeFile();
        pe.read(new PeReader(bytes));
        
        try {
            FileComponentHelper.inferSubComponentName(pe);
        } catch (ReflectiveOperationException e) {
            throw new FileParseException(e);
        }
        
        return pe;
    }
    
}
