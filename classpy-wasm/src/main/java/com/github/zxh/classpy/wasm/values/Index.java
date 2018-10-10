package com.github.zxh.classpy.wasm.values;

public class Index extends U32 {

    @Override
    protected void postRead() {
        setDesc("#" + value);
    }

}
