package com.github.zxh.classpy.gui.bcplayer;

/**
 *
 * @author zxh
 */
public class VarSlot {
    
    private final int slot;
    private String name;
    private String value;

    public VarSlot(int slot) {
        this.slot = slot;
        name = "";
        value = "";
    }

    public int getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
