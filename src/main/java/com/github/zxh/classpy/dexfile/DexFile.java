package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.dexfile.data.ClassDataItem;
import com.github.zxh.classpy.dexfile.data.MapItem;
import com.github.zxh.classpy.dexfile.data.StringDataItem;
import com.github.zxh.classpy.dexfile.data.TypeItem;
import com.github.zxh.classpy.dexfile.header.HeaderItem;
import com.github.zxh.classpy.dexfile.ids.FieldIdItem;
import com.github.zxh.classpy.dexfile.ids.MethodIdItem;
import com.github.zxh.classpy.dexfile.ids.ProtoIdItem;
import com.github.zxh.classpy.dexfile.ids.StringIdItem;
import com.github.zxh.classpy.dexfile.ids.TypeIdItem;
import com.github.zxh.classpy.dexfile.list.DataList;
import com.github.zxh.classpy.dexfile.list.DexList;
import com.github.zxh.classpy.dexfile.list.SizeList;
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
    private SizeList<MapItem> mapList;
    private DataList<StringDataItem> stringDataList;
    private DataList<ClassDataItem> classDataList;
    private DataList<SizeList<TypeItem>> typeList;

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
        stringIds = reader.readDexList(header.getStringIdsSize(), StringIdItem::new);
        reader.setPosition(header.getTypeIdsOff());
        typeIds = reader.readDexList(header.getTypeIdsSize(), TypeIdItem::new);
        reader.setPosition(header.getProtoIdsOff());
        protoIds = reader.readDexList(header.getProtoIdsSize(), ProtoIdItem::new);
        reader.setPosition(header.getFieldIdsOff());
        fieldIds = reader.readDexList(header.getFieldIdsSize(), FieldIdItem::new);
        reader.setPosition(header.getMethodIdsOff());
        methodIds = reader.readDexList(header.getMethodIdsSize(), MethodIdItem::new);
        reader.setPosition(header.getClassDefsOff());
        classDefs = reader.readDexList(header.getClassDefsSize(), ClassDefItem::new);
    }
    
    private void readData(DexReader reader) {
        mapList = reader.readSizeList(MapItem::new);
        stringDataList = reader.readDataList(StringDataItem::new,
                stringIds.stream().map(StringIdItem::getStringDataOff));
        classDataList = reader.readDataList(ClassDataItem::new,
                classDefs.stream().map(ClassDefItem::getClassDataOff));
        
        //Supplier<SizeList<TypeItem>> factory = () -> new SizeList<>(TypeItem::new);
        typeList = reader.readDataList(() -> new SizeList<>(TypeItem::new), 
                classDefs.stream().map(ClassDefItem::getInterfacesOff).filter(off -> off.getValue() > 0));
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header,
                stringIds, typeIds, protoIds, fieldIds, methodIds, classDefs,
                mapList, stringDataList, classDataList, typeList);
    }
    
}
