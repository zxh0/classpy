package com.github.zxh.classpy.decoder.types.builtin;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.FilePart;

public interface BuiltinTypeReader {

    FilePart read(BytesReader reader, String typeName);

}
