package com.github.zxh.classpy.classfile.attribute;

object AttributeFactory {

    /**
     * Create concrete XxxAttribute by name.
     * @param name
     * @return
     */
    fun create(name: String): AttributeInfo {
        //  predefined class file attributes:
        return when (name) {
            "ConstantValue" -> ConstantValueAttribute();
            "Code" -> CodeAttribute();
            "StackMapTable" -> StackMapTableAttribute(); // todo
            "Exceptions" -> ExceptionsAttribute();
            "InnerClasses" -> InnerClassesAttribute();
            "EnclosingMethod" -> EnclosingMethodAttribute();
            "Synthetic" -> SyntheticAttribute();
            "Signature" -> SignatureAttribute();
            "SourceFile" -> SourceFileAttribute();
            "SourceDebugExtension" -> SourceDebugExtensionAttribute(); // todo
            "LineNumberTable" -> LineNumberTableAttribute();
            "LocalVariableTable" -> LocalVariableTableAttribute();
            "LocalVariableTypeTable" -> LocalVariableTypeTableAttribute();
            "Deprecated" -> DeprecatedAttribute();
            "RuntimeVisibleAnnotations" -> RuntimeVisibleAnnotationsAttribute();
            "RuntimeInvisibleAnnotations" -> RuntimeVisibleAnnotationsAttribute();
            "RuntimeVisibleParameterAnnotations" -> RuntimeVisibleParameterAnnotationsAttribute();
            "RuntimeInvisibleParameterAnnotations" -> RuntimeVisibleParameterAnnotationsAttribute();
            "RuntimeVisibleTypeAnnotations" -> RuntimeVisibleTypeAnnotationsAttribute();
            "RuntimeInvisibleTypeAnnotations" -> RuntimeVisibleTypeAnnotationsAttribute();
            "AnnotationDefault" -> AnnotationDefaultAttribute();
            "BootstrapMethods" -> BootstrapMethodsAttribute();
            "MethodParameters" -> MethodParametersAttribute(); // todo
            else -> UndefinedAttribute()
        }
    }

}
