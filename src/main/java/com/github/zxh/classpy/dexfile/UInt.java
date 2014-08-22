package com.github.zxh.classpy.dexfile;

/**
 *
 * @author zxh
 */
public class UInt extends DexComponent {

    private int value;

    public int getValue() {
        if (value < 0) {
            // todo
        }
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        value = reader.readInt(); // todo
        if (value < 0) {
            setDesc(Integer.toUnsignedString(value));
        } else {
            setDesc(Integer.toString(value));
        }
    }
    
    public String toHexString() {
        return "0x" + Integer.toHexString(value);
    }
    
}
