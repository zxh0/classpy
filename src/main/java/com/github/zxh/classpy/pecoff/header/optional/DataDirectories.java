package com.github.zxh.classpy.pecoff.header.optional;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt32;

/**
 *
 * @author zxh
 */
public class DataDirectories extends PeComponent {

    @Override
    protected void readContent(PeReader reader) {
        // todo
    }

    
    public static class DataDirectory extends PeComponent {

        private UInt32 virtualAddress;
        private UInt32 size;
        
        @Override
        protected void readContent(PeReader reader) {
            virtualAddress = reader.readUInt32();
            size = reader.readUInt32();
        }
        
    }
    
}
