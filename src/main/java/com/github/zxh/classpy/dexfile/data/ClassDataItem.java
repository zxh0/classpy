package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.dexfile.DcList;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.Uleb128;

/**
 *
 * @author zxh
 */
public class ClassDataItem extends DexComponent {

    private Uleb128 staticFieldsSize;
    private Uleb128 instanceFieldsSize;
    private Uleb128 directMethodsSize;
    private Uleb128 virtualMethodsSize;
    private DcList<EncodedField> staticFields;
    private DcList<EncodedField> instanceFields;
    private DcList<EncodedMethod> directMethods;
    private DcList<EncodedMethod> virtualMethods;
    
    @Override
    protected void readContent(DexReader reader) {
        staticFieldsSize = reader.readUleb128();
        instanceFieldsSize = reader.readUleb128();
        directMethodsSize = reader.readUleb128();
        virtualMethodsSize = reader.readUleb128();
        staticFields = reader.readList(staticFieldsSize, EncodedField::new);
        instanceFields = reader.readList(instanceFieldsSize, EncodedField::new);
        directMethods = reader.readList(directMethodsSize, EncodedMethod::new);
        virtualMethods = reader.readList(virtualMethodsSize, EncodedMethod::new);
    }
    
    
    public static class EncodedField extends DexComponent {

        private Uleb128 fieldIdxDiff;
        private Uleb128 accessFlags;
        
        @Override
        protected void readContent(DexReader reader) {
            fieldIdxDiff = reader.readUleb128();
            accessFlags = reader.readUleb128();
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
        
    }
    
}
