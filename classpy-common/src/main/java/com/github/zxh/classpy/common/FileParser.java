package com.github.zxh.classpy.common;

public interface FileParser {

    FileParser NOP = data -> new AbstractFilePart() {};

    FilePart parse(byte[] data);

}
