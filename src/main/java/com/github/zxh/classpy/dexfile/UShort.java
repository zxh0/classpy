package com.github.zxh.classpy.dexfile;

/**
 *
 * @author zxh
 */
public class UShort extends DexComponent {

    private int value;

    public int getValue() {
        return value;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        value = Short.toUnsignedInt(reader.readShort());
        setDesc(String.valueOf(value));
    }
    
}
