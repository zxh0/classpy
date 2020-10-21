package com.github.zxh.classpy.common;

import java.util.List;

public interface FilePart {

    int getOffset();
    int getLength();

    String getName();
    void setName(String name);
    String getDesc();
    void setDesc(String name);

    List<FilePart> getParts();
    FilePart get(String name);
    void add(String name, FilePart subPart);

}
