package com.github.zxh.classpy.dexfile;

/**
 *
 * @author zxh
 */
public class UInt extends DexComponent {

    @Override
    protected void readContent(DexReader reader) {
        int value = reader.readInt(); // todo
        if (value < 0) {
            setDesc(Integer.toUnsignedString(value));
        } else {
            setDesc(Integer.toString(value));
        }
    }
    
}
