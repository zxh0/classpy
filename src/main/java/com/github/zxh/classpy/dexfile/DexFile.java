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
import com.github.zxh.classpy.dexfile.list.OffsetsKnownList;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;
import java.util.Arrays;
import java.util.List;

/**
 * The parse result of .dex file.
 * http://source.android.com/devices/tech/dalvik/dex-format.html
 * 
 * @author zxh
 */
public class DexFile extends DexComponent {
    
    private HeaderItem header;
    private SizeKnownList<StringIdItem> stringIds;
    private SizeKnownList<TypeIdItem> typeIds;
    private SizeKnownList<ProtoIdItem> protoIds;
    private SizeKnownList<FieldIdItem> fieldIds;
    private SizeKnownList<MethodIdItem> methodIds;
    private SizeKnownList<ClassDefItem> classDefs;
    private SizeHeaderList<MapItem> mapList;
    private OffsetsKnownList<StringDataItem> stringDataList;
    private OffsetsKnownList<ClassDataItem> classDataList;
    private OffsetsKnownList<SizeHeaderList<TypeItem>> typeList;

    @Override
    protected void readContent(DexReader reader) {
        readHeader(reader);
        readIdsAndClassDefs(reader);
        readData(reader);
        postRead();
    }
    
    private void readHeader(DexReader reader) {
        header = new HeaderItem();
        header.read(reader);
    }
    
    private void readIdsAndClassDefs(DexReader reader) {
        reader.setPosition(header.getStringIdsOff());
        stringIds = reader.readSizeKnownList(header.getStringIdsSize(), StringIdItem::new);
        reader.setPosition(header.getTypeIdsOff());
        typeIds = reader.readSizeKnownList(header.getTypeIdsSize(), TypeIdItem::new);
        reader.setPosition(header.getProtoIdsOff());
        protoIds = reader.readSizeKnownList(header.getProtoIdsSize(), ProtoIdItem::new);
        reader.setPosition(header.getFieldIdsOff());
        fieldIds = reader.readSizeKnownList(header.getFieldIdsSize(), FieldIdItem::new);
        reader.setPosition(header.getMethodIdsOff());
        methodIds = reader.readSizeKnownList(header.getMethodIdsSize(), MethodIdItem::new);
        reader.setPosition(header.getClassDefsOff());
        classDefs = reader.readSizeKnownList(header.getClassDefsSize(), ClassDefItem::new);
    }
    
    private void readData(DexReader reader) {
        mapList = reader.readSizeHeaderList(MapItem::new);
        
        reader.setPosition(stringIds.get(0).getStringDataOff());
        stringDataList = reader.readOffsetsKnownList(StringDataItem::new,
                stringIds.stream().map(StringIdItem::getStringDataOff));
        
        // todo
        classDataList = reader.readOffsetsKnownList(ClassDataItem::new,
                classDefs.stream().map(ClassDefItem::getClassDataOff));
        
        //Supplier<SizeList<TypeItem>> factory = () -> new SizeList<>(TypeItem::new);
        typeList = reader.readOffsetsKnownList(() -> new SizeHeaderList<>(TypeItem::new), 
                classDefs.stream().map(ClassDefItem::getInterfacesOff).filter(off -> off.getValue() > 0));
    }
    
    private void postRead() {
        typeIds.stream().forEach(typeId -> {
            int descIdx = typeId.getDescriptorIdx().getValue();
            String desc = stringDataList.get(descIdx).getValue();
            typeId.setDesc(desc);
        });
        
        protoIds.stream().forEach(protoId -> {
            int descIdx = protoId.getShortyIdx().getValue();
            String desc = stringDataList.get(descIdx).getValue();
            protoId.setDesc(desc);
        });
        
        fieldIds.stream().forEach(fieldId -> {
            //int classIdx = fieldId.getClassIdx().getValue();
            //String className = typeIds.get(classIdx).getDesc();
            int nameIdx = fieldId.getNameIdx().getValue();
            String name = stringDataList.get(nameIdx).getValue();
            fieldId.setDesc(name);
        });
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header,
                stringIds, typeIds, protoIds, fieldIds, methodIds, classDefs,
                mapList, stringDataList, classDataList, typeList);
    }
    
}
