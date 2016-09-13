package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.helper.StringUtil;

public class U4Hex extends U4 {

    @Override
    protected void afterRead(ConstantPool cp) {
        setDesc(StringUtil.toHexString(value));
    }

}
