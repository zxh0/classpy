package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.header.HeaderItem;
import com.github.zxh.classpy.dexfile.ids.FieldIdItem;
import com.github.zxh.classpy.dexfile.ids.MethodIdItem;
import com.github.zxh.classpy.dexfile.ids.ProtoIdItem;
import com.github.zxh.classpy.dexfile.ids.StringIdItem;
import com.github.zxh.classpy.dexfile.ids.TypeIdItem;
import java.util.Arrays;
import java.util.List;

/**
 *
 * http://source.android.com/devices/tech/dalvik/dex-format.html
 * @author zxh
 */
public class DexFile extends DexComponent {
    
    private HeaderItem header;
    private DcList<StringIdItem> stringIds;
    private DcList<TypeIdItem> typeIds;
    private DcList<ProtoIdItem> protoIds;
    private DcList<FieldIdItem> fieldIds;
    private DcList<MethodIdItem> methodIds;
    private DcList<ClassDefItem> classDefs;
    private StringDataList strings;

    @Override
    protected void readContent(DexReader reader) {
        header = new HeaderItem();
        header.read(reader);
        stringIds = reader.readList(header.getStringIdsSize(), StringIdItem::new);
        typeIds = reader.readList(header.getTypeIdsSize(), TypeIdItem::new);
        protoIds = reader.readList(header.getProtoIdsSize(), ProtoIdItem::new);
        fieldIds = reader.readList(header.getFieldIdsSize(), FieldIdItem::new);
        methodIds = reader.readList(header.getMethodIdsSize(), MethodIdItem::new);
        classDefs = reader.readList(header.getClassDefsSize(), ClassDefItem::new);
        strings = new StringDataList(stringIds);
        strings.read(reader);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header, stringIds, typeIds,
                protoIds, fieldIds, methodIds, classDefs, strings);
    }
    
}
