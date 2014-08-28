package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.dexfile.body.ClassDefItem;
import com.github.zxh.classpy.dexfile.body.data.ClassDataItem;
import com.github.zxh.classpy.dexfile.body.data.ClassDataItem.EncodedMethod;
import com.github.zxh.classpy.dexfile.body.data.CodeItem;
import com.github.zxh.classpy.dexfile.body.data.MapItem;
import com.github.zxh.classpy.dexfile.body.data.StringDataItem;
import com.github.zxh.classpy.dexfile.body.data.TypeItem;
import com.github.zxh.classpy.dexfile.header.HeaderItem;
import com.github.zxh.classpy.dexfile.body.ids.FieldIdItem;
import com.github.zxh.classpy.dexfile.body.ids.MethodIdItem;
import com.github.zxh.classpy.dexfile.body.ids.ProtoIdItem;
import com.github.zxh.classpy.dexfile.body.ids.StringIdItem;
import com.github.zxh.classpy.dexfile.body.ids.TypeIdItem;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.list.OffsetsKnownList;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

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
    private OffsetsKnownList<CodeItem> codeList;

    @Override
    protected void readContent(DexReader reader) {
        readHeader(reader);
        readIds(reader);
        readClassDefs(reader);
        readData(reader);
        super.postRead(this);
    }
    
    private void readHeader(DexReader reader) {
        header = new HeaderItem();
        header.read(reader);
    }
    
    private void readIds(DexReader reader) {
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
    }
    
    private void readClassDefs(DexReader reader) {
        reader.setPosition(header.getClassDefsOff());
        classDefs = reader.readSizeKnownList(header.getClassDefsSize(), ClassDefItem::new);
    }
    
    private void readData(DexReader reader) {
        reader.setPosition(header.getMapOff());
        mapList = reader.readUIntHeaderList(MapItem::new);
        
        reader.setPosition(stringIds.get(0).getStringDataOff());
        stringDataList = reader.readOffsetsKnownList(StringDataItem::new,
                stringIds.stream().mapToInt(stringId -> stringId.getStringDataOff().getValue()));
        
        reader.setPosition(classDefs.get(0).getClassDataOff());
        classDataList = reader.readOffsetsKnownList(ClassDataItem::new,
                classDefs.stream().mapToInt(classDef -> classDef.getClassDataOff().getValue()));
        
        readTypeList(reader);
        readCodeList(reader);
    }
    
    private void readTypeList(DexReader reader) {
        IntStream off1 = classDefs.stream()
                .map(ClassDefItem::getInterfacesOff)
                .mapToInt(off -> off.getValue())
                .filter(off -> off > 0);
        IntStream off2 = protoIds.stream()
                .map(ProtoIdItem::getParametersOff)
                .mapToInt(off -> off.getValue())
                .filter(off -> off > 0);
        int[] offArr = IntStream.concat(off1, off2).distinct().toArray();
        
        Supplier<SizeHeaderList<TypeItem>> factory = () -> new SizeHeaderList<>(TypeItem::new);
        
        reader.setPosition(offArr[0]);
        typeList = reader.readOffsetsKnownList(factory, Arrays.stream(offArr));
    }
    
    // todo
    private void readCodeList(DexReader reader) {
        List<Uleb128> codeOffsets = new ArrayList<>();
        for (ClassDataItem classData : classDataList) {
            for (EncodedMethod method : classData.getDirectMethods()) {
                if (method.getCodeOff().getValue() > 0) {
                    codeOffsets.add(method.getCodeOff());
                }
            }
            for (EncodedMethod method : classData.getVirtualMethods()) {
                if (method.getCodeOff().getValue() > 0) {
                    codeOffsets.add(method.getCodeOff());
                }
            }
        }
        
        reader.setPosition(codeOffsets.get(0));
        codeList = reader.readOffsetsKnownList(CodeItem::new,
                codeOffsets.stream().mapToInt(Uleb128::getValue));
    }
    
    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(header,
                stringIds, typeIds, protoIds, fieldIds, methodIds, classDefs,
                mapList, stringDataList, classDataList, typeList, codeList);
    }
    
    public String getString(IntValue index) {
        return getString(index.getValue());
    }
    
    public String getString(int index) {
        return stringDataList.get(index).getValue();
    }
    
    public TypeIdItem getTypeIdItem(IntValue index) {
        return getTypeIdItem(index.getValue());
    }
    
    public TypeIdItem getTypeIdItem(int index) {
        return typeIds.get(index);
    }
    
    public ProtoIdItem getProtoIdItem(int index) {
        return protoIds.get(index);
    }
    
    public FieldIdItem getFieldIdItem(int index) {
        return fieldIds.get(index);
    }
    
    public MethodIdItem getMethodIdItem(int index) {
        return methodIds.get(index);
    }
    
}
