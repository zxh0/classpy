package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.ByteArray;
import com.github.zxh.classpy.dexfile.datatype.UByte;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.helper.EncodedValueDecoder;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;
import java.io.IOException;

/**
 *
 * @author zxh
 */
public class EncodedArrayItem extends DexComponent {

    private EncodedArray value;
    
    @Override
    protected void readContent(DexReader reader) {
        value = new EncodedArray();
        value.read(reader);
    }
    
    
    // todo
    public static class EncodedArray extends DexComponent {

        private Uleb128 size;
        private SizeHeaderList<EncodedValue> values;
        
        @Override
        protected void readContent(DexReader reader) {
            size = reader.readUleb128();
            values = reader.readSizeHeaderList(EncodedValue::new);
        }
        
    }
    
    public static class EncodedValue extends DexComponent {

        private UByte typeAndArg; // (value_arg << 5) | value_type
        private ByteArray value;
        
        @Override
        protected void readContent(DexReader reader) {
            typeAndArg = reader.readUByte();
            try {
                decodeValue(typeAndArg, reader);
            } catch (IOException e) {
                throw new FileParseException(e);
            }
        }
        
        private void decodeValue(UByte typeAndArg, DexReader reader) throws IOException {
            int valueType = typeAndArg.getValue() & 0b11111;
            int valueArg = typeAndArg.getValue() >> 5;
            int size = valueArg + 1;
            
            switch (valueType) {
                case 0x00: // signed one-byte integer value
                    typeAndArg.setDesc("VALUE_BYTE(0x00)|" + valueArg);
                    value = reader.readByteArray(1);
                    value.setDesc(new EncodedValueDecoder(value, 1, true).readByte());
                    break;
                case 0x02: // signed two-byte integer value, sign-extended
                    typeAndArg.setDesc("VALUE_SHORT(0x02)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 2, true).readShort());
                    break;
                case 0x03: // unsigned two-byte integer value, zero-extended
                    typeAndArg.setDesc("VALUE_CHAR(0x03)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(String.valueOf(new EncodedValueDecoder(value, 2, false).readChar()));
                    break;
                case 0x04: // signed four-byte integer value, sign-extended
                    typeAndArg.setDesc("VALUE_INT(0x04)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 4, true).readInt());
                    break;
                case 0x06: // signed eight-byte integer value, sign-extended
                    typeAndArg.setDesc("VALUE_LONG(0x06)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(String.valueOf(new EncodedValueDecoder(value, 8, true).readLong()));
                    break;
                case 0x10: // four-byte bit pattern, zero-extended to the right, and interpreted as an IEEE754 32-bit floating point value 
                    typeAndArg.setDesc("VALUE_FLOAT(0x10)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(String.valueOf(new EncodedValueDecoder(value, 4, false).readFloat()));
                    break;
                case 0x11: // eight-byte bit pattern, zero-extended to the right, and interpreted as an IEEE754 64-bit floating point value 
                    typeAndArg.setDesc("VALUE_DOUBLE(0x11)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(String.valueOf(new EncodedValueDecoder(value, 8, false).readDouble()));
                    break;
                case 0x17: // unsigned (zero-extended) four-byte integer value, interpreted as an index into the string_ids section and representing a string value 
                    typeAndArg.setDesc("VALUE_STRING(0x17)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 4, false).readInt());
                    // todo
                    break;
                case 0x18: // unsigned (zero-extended) four-byte integer value, interpreted as an index into the type_ids section and representing a reflective type/class value 
                    typeAndArg.setDesc("VALUE_TYPE(0x18)|" + valueArg);
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 4, false).readInt());
                    break;
                case 0x19:
                    typeAndArg.setDesc("VALUE_FIELD(0x19)|" + valueArg);
                    break;
                case 0x1a:
                    typeAndArg.setDesc("VALUE_METHOD(0x1a)|" + valueArg);
                    break;
                case 0x1b:
                    typeAndArg.setDesc("VALUE_ENUM(0x1b)|" + valueArg);
                    break;
                case 0x1c:
                    typeAndArg.setDesc("VALUE_ARRAY(0x1c)|" + valueArg);
                    break;
                case 0x1d:
                    typeAndArg.setDesc("VALUE_ANNOTATION(0x1d)|" + valueArg);
                    break;
                case 0x1e:
                    typeAndArg.setDesc("VALUE_NULL(0x1e)|" + valueArg);
                    break;
                case 0x1f:
                    typeAndArg.setDesc("VALUE_BOOLEAN(0x1f)|" + valueArg);
                    break;
                default: throw new FileParseException("Invalid EncodedValue Type: " + valueType);
            }
        }
        
    }
    
}
