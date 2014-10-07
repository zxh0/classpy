package com.github.zxh.classpy.protobuf;

/**
 *
 * @author zxh
 */
public class KeyValuePair extends PbComponent {

    private Key key;
    
    @Override
    protected void readContent(PbReader reader) {
        // todo
        key = new Key();
        key.read(reader);
    }
    
    public static class Key extends PbComponent {

        private int wireType;
        private int tag;
        
        @Override
        protected void readContent(PbReader reader) {
            int varint = reader.readVarint();
            wireType = varint & 0b111;
            tag = varint >>> 3;
            //setDesc(wireType+":"+tag);
        }
        
    }
    
}
