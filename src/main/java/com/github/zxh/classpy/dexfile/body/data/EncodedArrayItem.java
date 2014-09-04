package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UByte;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.helper.EncodedValueDecoder;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
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
        private SizeKnownList<EncodedValue> values;
        
        @Override
        protected void readContent(DexReader reader) {
            size = reader.readUleb128();
            values = reader.readSizeKnownList(size, EncodedValue::new);
        }
        
    }
    
    public static class EncodedValue extends DexComponent {

        private UByte typeAndArg; // (value_arg << 5) | value_type
        private DexComponent value;
        
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
            
            String typeName = getTypeName(valueType);
            setDesc(typeName);
            typeAndArg.setDesc(typeName + "&" + valueArg);
            
            switch (valueType) {
                case 0x00: // signed one-byte integer value
                    value = reader.readByteArray(1);
                    value.setDesc(new EncodedValueDecoder(value, 1, true).readByte());
                    break;
                case 0x02: // signed two-byte integer value, sign-extended
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 2, true).readShort());
                    break;
                case 0x03: // unsigned two-byte integer value, zero-extended
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 2, false).readChar());
                    break;
                case 0x04: // signed four-byte integer value, sign-extended
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 4, true).readInt());
                    break;
                case 0x06: // signed eight-byte integer value, sign-extended
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 8, true).readLong());
                    break;
                case 0x10: // four-byte bit pattern, zero-extended to the right, and interpreted as an IEEE754 32-bit floating point value 
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 4, false).readFloat());
                    break;
                case 0x11: // eight-byte bit pattern, zero-extended to the right, and interpreted as an IEEE754 64-bit floating point value 
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 8, false).readDouble());
                    break;
                case 0x17: // unsigned (zero-extended) four-byte integer value, interpreted as an index into the string_ids section and representing a string value 
                case 0x18: // unsigned (zero-extended) four-byte integer value, interpreted as an index into the type_ids section and representing a reflective type/class value 
                case 0x19: // unsigned (zero-extended) four-byte integer value, interpreted as an index into the field_ids section and representing a reflective field value 
                case 0x1a: // unsigned (zero-extended) four-byte integer value, interpreted as an index into the method_ids section and representing a reflective method value 
                case 0x1b: // unsigned (zero-extended) four-byte integer value, interpreted as an index into the field_ids section and representing the value of an enumerated type constant 
                    value = reader.readByteArray(size);
                    value.setDesc(new EncodedValueDecoder(value, 4, false).readInt());
                    break;
                case 0x1c: // an array of values, in the format specified by "encoded_array Format" below. The size of the value is implicit in the encoding. 
                    value = new EncodedArray();
                    value.read(reader);
                    break;
                case 0x1d: // a sub-annotation, in the format specified by "encoded_annotation Format" below. The size of the value is implicit in the encoding. 
                    value = new EncodedAnnotation();
                    value.read(reader);
                    break;
                case 0x1e: // null reference value
                    break;
                case 0x1f: // one-bit value; 0 for false and 1 for true. The bit is represented in the value_arg. 
                    break;
            }
        }
        
        private static String getTypeName(int valueType) {
            switch (valueType) {
                case 0x00: return "VALUE_BYTE(0x00)";
                case 0x02: return "VALUE_SHORT(0x02)";
                case 0x03: return "VALUE_CHAR(0x03)";
                case 0x04: return "VALUE_INT(0x04)";
                case 0x06: return "VALUE_LONG(0x06)";
                case 0x10: return "VALUE_FLOAT(0x10)";
                case 0x11: return "VALUE_DOUBLE(0x11)";
                case 0x17: return "VALUE_STRING(0x17)";
                case 0x18: return "VALUE_TYPE(0x18)";
                case 0x19: return "VALUE_FIELD(0x19)";
                case 0x1a: return "VALUE_METHOD(0x1a)";
                case 0x1b: return "VALUE_ENUM(0x1b)";
                case 0x1c: return "VALUE_ARRAY(0x1c)";
                case 0x1d: return "VALUE_ANNOTATION(0x1d)";
                case 0x1e: return "VALUE_NULL(0x1e)";
                case 0x1f: return "VALUE_BOOLEAN(0x1f)";
                default: throw new FileParseException("Invalid EncodedValue Type: " + valueType);
            }
        }
        
    }
    
    public static class EncodedAnnotation extends DexComponent {

        private Uleb128 typeIdx;
        private Uleb128 size;
        private SizeKnownList<AnnotationElement> elements;
        
        @Override
        protected void readContent(DexReader reader) {
            typeIdx = reader.readUleb128();
            size = reader.readUleb128();
            elements = reader.readSizeKnownList(size, AnnotationElement::new);
        }
        
    }
    
    public static class AnnotationElement extends DexComponent {

        private Uleb128 nameIdx;
        private EncodedValue value;
        
        @Override
        protected void readContent(DexReader reader) {
            nameIdx = reader.readUleb128();
            value = new EncodedValue();
            value.read(reader);
        }
        
    }
    
}
