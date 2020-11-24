package com.github.zxh.classpy.llvm.bitcode;

import com.github.zxh.classpy.llvm.bitcode.types.U32Hex;

// https://llvm.org/docs/BitCodeFormat.html#bitcode-wrapper-format
public class Wrapper extends BitCodePart {

    {
        add("Magic",   new U32Hex());
        add("Version", new U32Hex());
        add("Offset",  new U32Hex());
        add("Size",    new U32Hex());
        add("CPUType", new U32Hex());
    }

}
