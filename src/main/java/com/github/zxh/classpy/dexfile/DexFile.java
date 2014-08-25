package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.data.StringDataList;
import com.github.zxh.classpy.dexfile.data.MapList;
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
    private DexList<StringIdItem> stringIds;
    private DexList<TypeIdItem> typeIds;
    private DexList<ProtoIdItem> protoIds;
    private DexList<FieldIdItem> fieldIds;
    private DexList<MethodIdItem> methodIds;
    private DexList<ClassDefItem> classDefs;
    private MapList mapList;
    private StringDataList stringList;

    @Override
    protected void readContent(DexReader reader) {
        readHeader(reader);
        readIdsAndClassDefs(reader);
        readData(reader);
    }
    
    private void readHeader(DexReader reader) {
        header = new HeaderItem();
        header.read(reader);
    }
    
    private void readIdsAndClassDefs(DexReader reader) {
        reader.setPosition(header.getStringIdsOff());
        stringIds = reader.readList(header.getStringIdsSize(), StringIdItem::new);
        reader.setPosition(header.getTypeIdsOff());
        typeIds = reader.readList(header.getTypeIdsSize(), TypeIdItem::new);
        reader.setPosition(header.getProtoIdsOff());
        protoIds = reader.readList(header.getProtoIdsSize(), ProtoIdItem::new);
        reader.setPosition(header.getFieldIdsOff());
        fieldIds = reader.readList(header.getFieldIdsSize(), FieldIdItem::new);
        reader.setPosition(header.getMethodIdsOff());
        methodIds = reader.readList(header.getMethodIdsSize(), MethodIdItem::new);
        reader.setPosition(header.getClassDefsOff());
        classDefs = reader.readList(header.getClassDefsSize(), ClassDefItem::new);
    }
    
    private void readData(DexReader reader) {
        mapList = new MapList();
        mapList.read(reader);
        stringList = new StringDataList(stringIds);
        stringList.read(reader);
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header,
                stringIds, typeIds, protoIds, fieldIds, methodIds, classDefs,
                mapList, stringList);
    }
    
}
