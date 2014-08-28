package com.github.zxh.classpy.dexfile.data;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UShort;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class MapItem extends DexComponent {

    private UShort type;
    private UShort unused;
    private UInt size;
    private UInt offset;
    
    @Override
    protected void readContent(DexReader reader) {
        type = reader.readUShort();
        unused = reader.readUShort();
        size = reader.readUInt();
        offset = reader.readUInt();
        setDesc(getTypeCode(type.getValue()));
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(type, unused, size, offset);
    }
    
    private static String getTypeCode(int type) {
        switch (type) {
            case 0x0000: return "TYPE_HEADER_ITEM";
            case 0x0001: return "TYPE_STRING_ID_ITEM";
            case 0x0002: return "TYPE_TYPE_ID_ITEM";
            case 0x0003: return "TYPE_PROTO_ID_ITEM";
            case 0x0004: return "TYPE_FIELD_ID_ITEM";
            case 0x0005: return "TYPE_METHOD_ID_ITEM";
            case 0x0006: return "TYPE_CLASS_DEF_ITEM";
            case 0x1000: return "TYPE_MAP_LIST";
            case 0x1001: return "TYPE_TYPE_LIST";
            case 0x1002: return "TYPE_ANNOTATION_SET_REF_LIST";
            case 0x1003: return "TYPE_ANNOTATION_SET_ITEM";
            case 0x2000: return "TYPE_CLASS_DATA_ITEM";
            case 0x2001: return "TYPE_CODE_ITEM";
            case 0x2002: return "TYPE_STRING_DATA_ITEM";
            case 0x2003: return "TYPE_DEBUG_INFO_ITEM";
            case 0x2004: return "TYPE_ANNOTATION_ITEM";
            case 0x2005: return "TYPE_ENCODED_ARRAY_ITEM";
            case 0x2006: return "TYPE_ANNOTATIONS_DIRECTORY_ITEM";
            default: throw new FileParseException("Invalid TypeCode: " + type);
        }
    }
    
}
