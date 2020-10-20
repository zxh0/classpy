package com.github.zxh.classpy.decoder;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDecoderTest {

    @Test
    public void decode() throws IOException {
        String hwClassFile = "/Users/zxh/me/github/classpy/classpy-classfile/build/classes/java/test/com/github/zxh/classpy/classfile/testclasses/HelloWorld.class";
        byte[] hwData = Files.readAllBytes(Path.of(hwClassFile));
        FileDecoder cfParser = FileDecoder.load("?");
        cfParser.parse(hwData);
     }

}
