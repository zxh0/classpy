package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.Uleb128;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class ClassDataItem extends DexComponent {

    private Uleb128 staticFieldsSize;
    private Uleb128 instanceFieldsSize;
    private Uleb128 directMethodsSize;
    private Uleb128 virtualMethodsSize;
    private SizeKnownList<EncodedField> staticFields;
    private SizeKnownList<EncodedField> instanceFields;
    private SizeKnownList<EncodedMethod> directMethods;
    private SizeKnownList<EncodedMethod> virtualMethods;
    
    @Override
    protected void readContent(DexReader reader) {
        staticFieldsSize = reader.readUleb128();
        instanceFieldsSize = reader.readUleb128();
        directMethodsSize = reader.readUleb128();
        virtualMethodsSize = reader.readUleb128();
        staticFields = reader.readSizeKnownList(staticFieldsSize, EncodedField::new);
        instanceFields = reader.readSizeKnownList(instanceFieldsSize, EncodedField::new);
        directMethods = reader.readSizeKnownList(directMethodsSize, EncodedMethod::new);
        virtualMethods = reader.readSizeKnownList(virtualMethodsSize, EncodedMethod::new);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(staticFieldsSize, instanceFieldsSize,
                directMethodsSize, virtualMethodsSize,
                staticFields, instanceFields, directMethods, virtualMethods);
    }
    
    
    public static class EncodedField extends DexComponent {

        private Uleb128 fieldIdxDiff;
        private Uleb128 accessFlags;
        
        @Override
        protected void readContent(DexReader reader) {
            fieldIdxDiff = reader.readUleb128();
            accessFlags = reader.readUleb128();
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(fieldIdxDiff, accessFlags);
        }
    
    }
    
    public static class EncodedMethod extends DexComponent {

        private Uleb128 methodIdxDiff;
        private Uleb128 accessFlags;
        private Uleb128 codeOff;
        
        @Override
        protected void readContent(DexReader reader) {
            methodIdxDiff = reader.readUleb128();
            accessFlags = reader.readUleb128();
            codeOff = reader.readUleb128();
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(methodIdxDiff, accessFlags, codeOff);
        }
        
    }
    
}
