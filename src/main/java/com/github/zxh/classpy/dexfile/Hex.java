package com.github.zxh.classpy.dexfile;

/**
 * ubyte[20].
 *
 * @author zxh
 */
public class Hex extends DexComponent {

    private final int n; // number of bytes

    public Hex(int n) {
        this.n = n;
    }
    
    @Override
    protected void readContent(DexReader reader) {
        StringBuilder hex = new StringBuilder("0x");
        for (int i = 0; i < n; i++) {
            hex.append(Integer.toHexString(reader.readUByte()));
        }
        
        setDesc(hex.toString());
    }
    
}
