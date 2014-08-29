package com.github.zxh.classpy.dexfile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public class DexFileTest {
    
    @Test
    public void dex() throws Exception {
        loadDex("classes.dex");
    }
    
    private static DexFile loadDex(String dexFileName) throws Exception {
        Path dexFilePath = Paths.get(ClassLoader.getSystemResource(dexFileName).toURI());
        byte[] dexBytes = Files.readAllBytes(dexFilePath);
        DexFile dex = new DexParser().parse(dexBytes);
        return dex;
    }
    
}
