package com.github.zxh.classpy.dexfile;

import com.github.zxh.classpy.common.IntValue;
import com.github.zxh.classpy.dexfile.body.ClassDefItem;
import com.github.zxh.classpy.dexfile.body.data.AnnotationItem;
import com.github.zxh.classpy.dexfile.body.data.AnnotationOffItem.AnnotationSetItem;
import com.github.zxh.classpy.dexfile.body.data.AnnotationSetRefItem;
import com.github.zxh.classpy.dexfile.body.data.AnnotationSetRefItem.AnnotationSetRefList;
import com.github.zxh.classpy.dexfile.body.data.AnnotationsDirectoryItem;
import com.github.zxh.classpy.dexfile.body.data.AnnotationsDirectoryItem.FieldAnnotation;
import com.github.zxh.classpy.dexfile.body.data.AnnotationsDirectoryItem.MethodAnnotation;
import com.github.zxh.classpy.dexfile.body.data.ClassDataItem;
import com.github.zxh.classpy.dexfile.body.data.ClassDataItem.EncodedMethod;
import com.github.zxh.classpy.dexfile.body.data.CodeItem;
import com.github.zxh.classpy.dexfile.body.data.DebugInfoItem;
import com.github.zxh.classpy.dexfile.body.data.EncodedArrayItem;
import com.github.zxh.classpy.dexfile.body.data.MapItem;
import com.github.zxh.classpy.dexfile.body.data.StringDataItem;
import com.github.zxh.classpy.dexfile.body.data.TypeList;
import com.github.zxh.classpy.dexfile.header.HeaderItem;
import com.github.zxh.classpy.dexfile.body.ids.FieldIdItem;
import com.github.zxh.classpy.dexfile.body.ids.MethodIdItem;
import com.github.zxh.classpy.dexfile.body.ids.ProtoIdItem;
import com.github.zxh.classpy.dexfile.body.ids.StringIdItem;
import com.github.zxh.classpy.dexfile.body.ids.TypeIdItem;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.list.OffsetsKnownList;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import com.github.zxh.classpy.dexfile.list.SizeHeaderList;
import java.util.ArrayList;
import java.util.List;
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
    private OffsetsKnownList<TypeList> typeLists;
    private OffsetsKnownList<CodeItem> codeList;
    private OffsetsKnownList<DebugInfoItem> debugInfoList;
    private OffsetsKnownList<AnnotationsDirectoryItem> annotationsDirectoryList;
    private OffsetsKnownList<EncodedArrayItem> encodedArrayList;
    private OffsetsKnownList<AnnotationSetRefList> annotationSetRefLists;
    private OffsetsKnownList<AnnotationSetItem> annotationSetItemList;
    private OffsetsKnownList<AnnotationItem> annotationList;

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
        readMapList(reader);
        readStringDataList(reader);
        readClassDataList(reader);
        readTypeLists(reader);
        readCodeList(reader);
        readDebugInfoList(reader);
        readAnnotationsDirectoryList(reader);
        readEncodedArrayList(reader);
        readAnnotationSetRefLists(reader);
        readAnnotationSetItemList(reader);
        readAnnotationList(reader);
    }
    
    private void readMapList(DexReader reader) {
        if (header.getMapOff().getValue() > 0) {
            reader.setPosition(header.getMapOff());
            mapList = reader.readSizeHeaderList(MapItem::new);
        }
    }
    
    private void readStringDataList(DexReader reader) {
        int[] offArr = stringIds.stream()
                .mapToInt(stringId -> stringId.getStringDataOff().getValue())
                .toArray();
        
        stringDataList = reader.readOffsetsKnownList(offArr, StringDataItem::new);
    }
    
    private void readClassDataList(DexReader reader) {
        int[] offArr = classDefs.stream()
                .mapToInt(classDef -> classDef.getClassDataOff().getValue())
                .filter(off -> off > 0)
                .toArray();
        
        classDataList = reader.readOffsetsKnownList(offArr, ClassDataItem::new);
    }
    
    private void readTypeLists(DexReader reader) {
        IntStream off1 = classDefs.stream()
                .mapToInt(classDef -> classDef.getInterfacesOff().getValue())
                .filter(off -> off > 0);
        IntStream off2 = protoIds.stream()
                .mapToInt(protoId -> protoId.getParametersOff().getValue())
                .filter(off -> off > 0);
        int[] offArr = IntStream.concat(off1, off2)
                .distinct()
                .toArray();
        
        typeLists = reader.readOffsetsKnownList(offArr, TypeList::new);
    }
    
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
        
        int[] offArr = codeOffsets.stream()
                .mapToInt(Uleb128::getValue)
                .toArray();
        
        codeList = reader.readOffsetsKnownList(offArr, CodeItem::new);
    }
    
    private void readDebugInfoList(DexReader reader) {
        int[] offArr = codeList.stream()
                .mapToInt(codeItem -> codeItem.getDebugInfoOff().getValue())
                .filter(off -> off > 0)
                .toArray();
        
        debugInfoList = reader.readOffsetsKnownList(offArr, DebugInfoItem::new);
    }
    
    private void readAnnotationsDirectoryList(DexReader reader) {
        int[] offArr = classDefs.stream()
                .mapToInt(classDef -> classDef.getAnnotationsOff().getValue())
                .filter(off -> off > 0)
                .toArray();
        
        annotationsDirectoryList = reader.readOffsetsKnownList(offArr,
                AnnotationsDirectoryItem::new);
    }
    
    private void readEncodedArrayList(DexReader reader) {
        int[] offArr = classDefs.stream()
                .mapToInt(classDef -> classDef.getStaticValuesOff().getValue())
                .filter(off -> off > 0)
                .toArray();
        
        encodedArrayList = reader.readOffsetsKnownList(offArr, EncodedArrayItem::new);
    }
    
    private void readAnnotationSetRefLists(DexReader reader) {
        int[] offArr = annotationsDirectoryList.stream()
                .flatMap(d -> d.getParameterAnnotations().stream())
                .mapToInt(a -> a.getAnnotationsOff().getValue())
                .filter(off -> off > 0)
                .distinct()
                .toArray();
        
        annotationSetRefLists = reader.readOffsetsKnownList(offArr,
                AnnotationSetRefList::new);
    }
    
    private void readAnnotationSetItemList(DexReader reader) {
        List<UInt> offList = new ArrayList<>();
        for (AnnotationsDirectoryItem d : annotationsDirectoryList) {
            offList.add(d.getClassAnnotationsOff());
            for (FieldAnnotation a : d.getFieldAnnotations()) {
                offList.add(a.getAnnotationsOff());
            }
            for (MethodAnnotation a : d.getMethodAnnotations()) {
                offList.add(a.getAnnotationsOff());
            }
        }
        for (AnnotationSetRefList list : annotationSetRefLists) {
            for (AnnotationSetRefItem item : list.getList()) {
                offList.add(item.getAnnotationsOff());
            }
        }
        
        int[] offArr = offList.stream()
                .mapToInt(off -> off.getValue())
                .filter(off -> off > 0)
                .distinct()
                .toArray();
        
        annotationSetItemList = reader.readOffsetsKnownList(offArr,
                AnnotationSetItem::new);
    }
    
    private void readAnnotationList(DexReader reader) {
        int[] offArr = annotationSetItemList.stream()
                .flatMap(setItem -> setItem.getList().stream())
                .mapToInt(offItem -> offItem.getAnnotationOff().getValue())
                .filter(off -> off > 0)
                .distinct()
                .toArray();
        
        annotationList = reader.readOffsetsKnownList(offArr, AnnotationItem::new);
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
