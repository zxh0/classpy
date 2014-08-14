package com.github.zxh.classpy.classfile.attr;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.ClassReader;
import com.github.zxh.classpy.classfile.U2;
import com.github.zxh.classpy.classfile.U4;

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
    protected void readContent(ClassReader reader) {
        attributeNameIndex = reader.readU2();
        attributeLength = reader.readU4();
        readInfo(reader);
        setName(reader.getConstantPool().getUtf8String(attributeNameIndex));
    }
    
    protected abstract void readInfo(ClassReader reader);
    
    public static AttributeInfo create(String name) {
        switch (name) {
            case "ConstantValue": return new ConstantValueAttribute();
            case "Code": return new CodeAttribute(); // todo
            case "StackMapTable": return new StackMapTableAttribute(); // todo
            case "Exceptions": return new ExceptionsAttribute(); // todo
            case "InnerClasses": return new InnerClassesAttribute(); // todo
            case "EnclosingMethod": return new EnclosingMethodAttribute();
            case "Synthetic": break; // todo
            case "Signature": return new SignatureAttribute();
            case "SourceFile":  return new SourceFileAttribute();
            case "SourceDebugExtension": break; // todo
            case "LineNumberTable": return new LineNumberTableAttribute();
            case "LocalVariableTable": return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable": return new LocalVariableTypeTableAttribute();
            case "Deprecated": return new DeprecatedAttribute();
            case "RuntimeVisibleAnnotations": return new RuntimeVisibleAnnotationsAttribute();
            case "RuntimeInvisibleAnnotations": break;
            case "RuntimeVisibleParameterAnnotations": break;
            case "RuntimeInvisibleParameterAnnotations": break;
            case "RuntimeVisibleTypeAnnotations": break;
            case "RuntimeInvisibleTypeAnnotations": break;
            case "AnnotationDefault": break;
            case "BootstrapMethods": return new BootstrapMethodsAttribute();
            case "MethodParameters": break;
        }
        
        // todo
        throw new ClassParseException(name);
    }
    
}
