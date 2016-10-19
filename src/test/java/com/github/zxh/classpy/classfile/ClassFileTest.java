package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.testclasses.AnnotatedClass;
import com.github.zxh.classpy.classfile.testclasses.SimpleAttr;
import com.github.zxh.classpy.classfile.testclasses.ByteCode;
import com.github.zxh.classpy.classfile.testclasses.CodeAttr;
import com.github.zxh.classpy.classfile.testclasses.ConstantPool;
import com.github.zxh.classpy.classfile.testclasses.SimpleClass;
import com.github.zxh.classpy.classfile.testclasses.GenericClass;
import com.github.zxh.classpy.classfile.testclasses.MyInterface;
import com.github.zxh.classpy.classfile.testclasses.annotations.MyRuntimeAnnotation;
import com.github.zxh.classpy.classfile.testclasses.TypeAnnotatedClass;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClassFileTest {
    
    @Test
    public void simpleClass() throws Exception {
        ClassFile cf = loadClass(SimpleClass.class);
        assertEquals(0, cf.getUInt("minor_version"));
        assertEquals(52, cf.getUInt("major_version"));
        assertEquals(37, cf.getUInt("constant_pool_count"));
        assertEquals(2, cf.getUInt("interfaces_count"));
        assertEquals(2, cf.getUInt("fields_count"));
        assertEquals(5, cf.getUInt("methods_count"));
        assertEquals(2, cf.getUInt("attributes_count"));
    }

    @Test
    public void constantPool() throws Exception {
        loadClass(ConstantPool.class);
    }
    
    @Test
    public void simpleAttr() throws Exception {
        loadClass(SimpleAttr.class);
    }
    
    @Test
    public void codeAttr() throws Exception {
        loadClass(CodeAttr.class);
    }
    
    @Test
    public void enclosingMethodAttribute() throws Exception {
        String classFileName = SimpleAttr.class.getName().replace('.', '/') + "$1.class";
        loadClass(classFileName);
    }
    
    @Test
    public void annotationDefaultAttribute() throws Exception {
        loadClass(MyRuntimeAnnotation.class);
    }
    
    @Test
    public void methodParametersAttribute() throws Exception {
        loadClass(MyInterface.class);
    }
    
    @Test
    public void genericClass() throws Exception {
        loadClass(GenericClass.class);
    }
    
    @Test
    public void annotatedClass() throws Exception {
        loadClass(AnnotatedClass.class);
    }
    
    @Test
    public void typeAnnotatedClass() throws Exception {
        loadClass(TypeAnnotatedClass.class);
    }
    
    @Test
    public void byteCode() throws Exception {
        loadClass(ByteCode.class);
    }
    
    private static ClassFile loadClass(Class<?> cls) throws Exception {
        String classFileName = cls.getName().replace('.', '/') + ".class";
        return loadClass(classFileName);
    }
    
    private static ClassFile loadClass(String classFileName) throws Exception {
        ClassLoader cl = SimpleClass.class.getClassLoader();
        Path classFilePath = Paths.get(cl.getResource(classFileName).toURI());
        byte[] classBytes = Files.readAllBytes(classFilePath);
        ClassFile cf = new ClassParser().parse(classBytes);
        return cf;
    }
    
}
