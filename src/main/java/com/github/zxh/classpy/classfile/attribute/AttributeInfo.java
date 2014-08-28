package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U4;
import java.util.Arrays;
import java.util.List;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
 */
public abstract class AttributeInfo extends ClassComponent {

    protected U2 attributeNameIndex;
    protected U4 attributeLength;
    
    @Override
    protected final void readContent(ClassReader reader) {
        attributeNameIndex = reader.readU2();
        attributeLength = reader.readU4();
        readInfo(reader);
        setName(reader.getConstantPool().getUtf8String(attributeNameIndex));
    }
    
    protected abstract void readInfo(ClassReader reader);
    
    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(attributeNameIndex, attributeLength);
    }
    
    
    /**
     * Create concrete XxxAttribute by name.
     * @param name
     * @return 
     */
    public static AttributeInfo create(String name) {
        //  predefined class file attributes:
        switch (name) {
            case "ConstantValue": return new ConstantValueAttribute();
            case "Code": return new CodeAttribute();
            case "StackMapTable": return new StackMapTableAttribute(); // todo
            case "Exceptions": return new ExceptionsAttribute();
            case "InnerClasses": return new InnerClassesAttribute();
            case "EnclosingMethod": return new EnclosingMethodAttribute();
            case "Synthetic": return new SyntheticAttribute();
            case "Signature": return new SignatureAttribute();
            case "SourceFile":  return new SourceFileAttribute();
            case "SourceDebugExtension": return new SourceDebugExtensionAttribute(); // todo
            case "LineNumberTable": return new LineNumberTableAttribute();
            case "LocalVariableTable": return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable": return new LocalVariableTypeTableAttribute();
            case "Deprecated": return new DeprecatedAttribute();
            case "RuntimeVisibleAnnotations": return new RuntimeVisibleAnnotationsAttribute();
            case "RuntimeInvisibleAnnotations": return new RuntimeVisibleAnnotationsAttribute();
            case "RuntimeVisibleParameterAnnotations": return new RuntimeVisibleParameterAnnotationsAttribute();
            case "RuntimeInvisibleParameterAnnotations": return new RuntimeVisibleParameterAnnotationsAttribute();
            case "RuntimeVisibleTypeAnnotations": return new RuntimeVisibleTypeAnnotationsAttribute();
            case "RuntimeInvisibleTypeAnnotations": return new RuntimeVisibleTypeAnnotationsAttribute();
            case "AnnotationDefault": return new AnnotationDefaultAttribute();
            case "BootstrapMethods": return new BootstrapMethodsAttribute();
            case "MethodParameters": return new MethodParametersAttribute(); // todo
        }
        
        //throw new ClassParseException(name);
        return new UndefinedAttribute();
    }
    
}
