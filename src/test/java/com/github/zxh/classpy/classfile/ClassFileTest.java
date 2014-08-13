package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.TestClass;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public class ClassFileTest {
    
    @Test
    public void read() throws Exception {
        String classFileName = TestClass.class.getName().replace('.', '/') + ".class";
        Path classFilePath = Paths.get(TestClass.class.getClassLoader().getResource(classFileName).toURI());
        byte[] classBytes = Files.readAllBytes(classFilePath);
        
        new ClassFile().read(classBytes);
    }
    
}
