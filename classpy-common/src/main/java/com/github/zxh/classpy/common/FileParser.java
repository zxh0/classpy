package com.github.zxh.classpy.common;

public interface FileParser {

    FileParser NOP = data -> new FileComponent() {};

    FileComponent parse(byte[] data);

}
