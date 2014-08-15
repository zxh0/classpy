package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import com.github.zxh.classpy.classfile.U4;
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
            case "LocalVariableTypeTable": return new LocalVariableTypeTableAttribute(); // todo
            case "Deprecated": return new DeprecatedAttribute();
            case "RuntimeVisibleAnnotations": return new RuntimeVisibleAnnotationsAttribute(); // todo
            case "RuntimeInvisibleAnnotations": break; // todo
            case "RuntimeVisibleParameterAnnotations": break; // todo
            case "RuntimeInvisibleParameterAnnotations": break; // todo
            case "RuntimeVisibleTypeAnnotations": break; // todo
            case "RuntimeInvisibleTypeAnnotations": break; // todo
            case "AnnotationDefault": break; // todo
            case "BootstrapMethods": return new BootstrapMethodsAttribute();
            case "MethodParameters": break; // todo
        }
        
        // todo
        throw new ClassParseException(name);
    }
    
}
