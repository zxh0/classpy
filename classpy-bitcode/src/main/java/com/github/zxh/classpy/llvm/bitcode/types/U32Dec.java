package com.github.zxh.classpy.llvm.bitcode.types;

import com.github.zxh.classpy.common.FixedInt;
import com.github.zxh.classpy.llvm.bitcode.BitCodeReader;

public class U32Dec extends FixedInt<BitCodeReader> {

    public U32Dec() {
        super(IntType.U32, IntDesc.Dec);
    }

}
