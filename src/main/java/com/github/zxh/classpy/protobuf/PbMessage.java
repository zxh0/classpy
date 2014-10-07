package com.github.zxh.classpy.protobuf;

import java.util.ArrayList;
import java.util.List;

/**
 * http://code.google.com/p/protobuf/
 * 
 * @author zxh
 */
public class PbMessage extends PbComponent {

    private final List<KeyValuePair> pairs = new ArrayList<>();
    
    @Override
    protected void readContent(PbReader reader) {
        while (reader.getRemaining() > 0) {
            KeyValuePair pair = new KeyValuePair();
            pair.read(reader);
            pairs.add(pair);
        }
    }

    @Override
    public List<KeyValuePair> getSubComponents() {
        return pairs;
    }
    
}
