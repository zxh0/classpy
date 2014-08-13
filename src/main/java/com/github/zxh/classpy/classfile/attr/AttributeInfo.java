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
    }
    
    protected abstract void readInfo(ClassReader reader);
    
    public static AttributeInfo create(String name) {
        switch (name) {
            case "ConstantValue": return new ConstantValueAttribute();
            case "Code": return new CodeAttribute();
            case "StackMapTable": return new StackMapTableAttribute();
            case "Exceptions": break;
            case "InnerClasses": return new InnerClassesAttribute();
            case "EnclosingMethod":  break;
            case "Synthetic":  break;
            case "Signature": return new SignatureAttribute();
            case "SourceFile":  return new SourceFileAttribute();
            case "SourceDebugExtension":  break;
            case "LineNumberTable": return new LineNumberTableAttribute();
            case "LocalVariableTable": return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable": return new LocalVariableTypeTableAttribute();
            case "Deprecated":  break;
            case "RuntimeVisibleAnnotations":  break;
            case "RuntimeInvisibleAnnotations":  break;
            case "RuntimeVisibleParameterAnnotations":  break;
            case "RuntimeInvisibleParameterAnnotations":  break;
            case "RuntimeVisibleTypeAnnotations":  break;
            case "RuntimeInvisibleTypeAnnotations":  break;
            case "AnnotationDefault":  break;
            case "BootstrapMethods": return new BootstrapMethodsAttribute();
            case "MethodParameters":  break;
        }
        
        // todo
        throw new ClassParseException(name);
    }
    
}
