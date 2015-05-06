package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.datatype.Table;
import java.util.Optional;
import java.util.function.Predicate;

public interface AttributeContainer {
    
    public Table<AttributeInfo> getAttributes();
    
    public default <T extends AttributeInfo> T findAttribute(Class<T> attrClass) {
        Optional<AttributeInfo> codeAttr = findAttribute(attrClass::isInstance);
        return attrClass.cast(codeAttr.orElse(null));
    }
    
    public default Optional<AttributeInfo> findAttribute(Predicate<AttributeInfo> predicate) {
        return getAttributes().getSubComponents().stream()
                .filter(predicate)
                .findAny();
    }
    
}
