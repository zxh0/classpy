package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.ByteArray;
import com.github.zxh.classpy.dexfile.datatype.UByte;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;

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
            decodeValue(typeAndArg, reader);
        }
        
        private void decodeValue(UByte typeAndArg, DexReader reader) {
            int valueType = typeAndArg.getValue() & 0b11111;
            int valueArg = typeAndArg.getValue() >> 5;
            
            switch (valueType) {
                case 0x00: // signed one-byte integer value
                    typeAndArg.setDesc("VALUE_BYTE(0x00)|" + valueArg);
                    value = reader.readByteArray(1);
                    //value = reader.readSByte();
                    break;
                case 0x02: // signed two-byte integer value, sign-extended
                    typeAndArg.setDesc("VALUE_SHORT(0x02)|" + valueArg);
                    //value = size == 0 ? reader.readSByte() : reader.readSShort();
                    break;
                case 0x03: // unsigned two-byte integer value, zero-extended
                    typeAndArg.setDesc("VALUE_CHAR(0x03)|" + valueArg);
                    //value = size == 0 ? reader.readUByte() : reader.readUShort();
                    break;
                case 0x04: // signed four-byte integer value, sign-extended
                    typeAndArg.setDesc("VALUE_INT(0x04)|" + valueArg);
                    break;
                case 0x06:
                    typeAndArg.setDesc("VALUE_LONG(0x06)|" + valueArg);
                    break;
                case 0x10:
                    typeAndArg.setDesc("VALUE_FLOAT(0x10)|" + valueArg);
                    break;
                case 0x11:
                    typeAndArg.setDesc("VALUE_DOUBLE(0x11)|" + valueArg);
                    break;
                case 0x17:
                    typeAndArg.setDesc("VALUE_STRING(0x17)|" + valueArg);
                    break;
                case 0x18:
                    typeAndArg.setDesc("VALUE_TYPE(0x18)|" + valueArg);
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
    
//    private static class EncodedValueDecoder extends DataInputStream {
//        
//        private EncodedValueDecoder(byte[] buf, int extendedByteCount) {
//            super(new ByteArrayInputStream(buf));
//        }
//        
//        private byte[] signExtend(byte[] buf, int extendedByteCount) {
//            if (buf.length < 2) {
//                return buf;
//            }
//            if (buf.length == extendedByteCount) {
//                //
//                return buf;
//            }
//            
//            
//        }
//        
//    }
    
}
