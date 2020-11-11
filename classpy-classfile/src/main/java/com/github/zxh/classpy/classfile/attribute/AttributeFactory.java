package com.github.zxh.classpy.classfile.attribute;

public class AttributeFactory {

    /**
     * Create concrete XxxAttribute by name.
     * @param name type of attribute
     * @return new AttributeInfo
     */
    public static AttributeInfo create(String name) {
        //  predefined class file attributes:
        return switch (name) {
            case "ConstantValue" -> new ConstantValueAttribute();
            case "Code" -> new CodeAttribute();
            case "StackMapTable" -> new StackMapTableAttribute(); // TODO
            case "Exceptions" -> new ExceptionsAttribute();
            case "InnerClasses" -> new InnerClassesAttribute();
            case "EnclosingMethod" -> new EnclosingMethodAttribute();
            case "Synthetic" -> new SyntheticAttribute();
            case "Signature" -> new SignatureAttribute();
            case "SourceFile" -> new SourceFileAttribute();
            case "SourceDebugExtension" -> new SourceDebugExtensionAttribute(); // TODO
            case "LineNumberTable" -> new LineNumberTableAttribute();
            case "LocalVariableTable" -> new LocalVariableTableAttribute();
            case "LocalVariableTypeTable" -> new LocalVariableTypeTableAttribute();
            case "Deprecated" -> new DeprecatedAttribute();
            case "RuntimeVisibleAnnotations" -> new RuntimeVisibleAnnotationsAttribute();
            case "RuntimeInvisibleAnnotations" -> new RuntimeVisibleAnnotationsAttribute();
            case "RuntimeVisibleParameterAnnotations" -> new RuntimeVisibleParameterAnnotationsAttribute();
            case "RuntimeInvisibleParameterAnnotations" -> new RuntimeVisibleParameterAnnotationsAttribute();
            case "RuntimeVisibleTypeAnnotations" -> new RuntimeVisibleTypeAnnotationsAttribute();
            case "RuntimeInvisibleTypeAnnotations" -> new RuntimeVisibleTypeAnnotationsAttribute();
            case "AnnotationDefault" -> new AnnotationDefaultAttribute();
            case "BootstrapMethods" -> new BootstrapMethodsAttribute();
            case "MethodParameters" -> new MethodParametersAttribute(); // TODO
            case "Module" -> new ModuleAttribute();
            case "ModulePackages" -> new ModulePackagesAttribute();
            case "ModuleMainClass" -> new ModuleMainClassAttribute();
            default -> new UndefinedAttribute();
        };

        //throw new ParseException(name);
    }

}
