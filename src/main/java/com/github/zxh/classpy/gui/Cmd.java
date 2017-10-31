package com.github.zxh.classpy.gui;

import java.util.Arrays;
import java.util.List;

public final class Cmd {
    public List<String> files;

    public String[] parse(String[] args) {
        files = Arrays.asList(args);
        return new String[0];
    }
}
