package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.common.Util;
import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.body.ids.TypeIdItem;
import com.github.zxh.classpy.dexfile.datatype.Sleb128;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.datatype.UShort;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class CodeItem extends DexComponent {

    private UShort registersSize;
    private UShort insSize;
    private UShort outsSize;
    private UShort triesSize;
    private UIntHex debugInfoOff; // todo
    private UInt insnsSize;
    // insns
    private UShort padding; // optional
    private SizeKnownList<TryItem> tries; // optional
    private EncodedCatchHandlerList handlers; // optional
    
    @Override
    protected void readContent(DexReader reader) {
        registersSize = reader.readUShort();
        insSize = reader.readUShort();
        outsSize = reader.readUShort();
        triesSize = reader.readUShort();
        debugInfoOff = reader.readUIntHex();
        insnsSize = reader.readUInt();
        reader.skipBytes(insnsSize.getValue() * 2); // insns
        readPadding(reader);
        readTries(reader);
        readHandlers(reader);
    }
    
    private void readPadding(DexReader reader) {
        // This element is only present if tries_size is non-zero and insns_size is odd. 
        if ((triesSize.getValue() > 0) && (insnsSize.getValue() %2 == 1)) {
            padding = reader.readUShort();
        }
    }
    
    private void readTries(DexReader reader) {
        if (triesSize.getValue() > 0) {
            tries = reader.readSizeKnownList(triesSize, TryItem::new);
        }
    }
    
    private void readHandlers(DexReader reader) {
        if (triesSize.getValue() > 0) {
            handlers = new EncodedCatchHandlerList();
            handlers.read(reader);
        }
    }
    
    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Util.listWithoutNulls(registersSize, insSize, outsSize,
                triesSize, debugInfoOff, insnsSize, padding, tries, handlers);
    }
    
    
    public static class TryItem extends DexComponent {

        private UInt startAddr;
        private UShort insnCount;
        private UShort handlerOff;
        
        @Override
        protected void readContent(DexReader reader) {
            startAddr = reader.readUInt();
            insnCount = reader.readUShort();
            handlerOff = reader.readUShort();
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(startAddr, insnCount, handlerOff);
        }
        
    }
    
    public static class EncodedCatchHandlerList extends DexComponent {

        private Uleb128 size;
        private SizeKnownList<EncodedCatchHandler> list;
        
        @Override
        protected void readContent(DexReader reader) {
            size = reader.readUleb128();
            list = reader.readSizeKnownList(size, EncodedCatchHandler::new);
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(size, list);
        }
        
    }
    
    public static class EncodedCatchHandler extends DexComponent {

        private Sleb128 size;
        private SizeKnownList<EncodedTypeAddrPair> handlers;
        private Uleb128 catchAllAddr; // optional
        
        @Override
        protected void readContent(DexReader reader) {
            size = reader.readSleb128();
            handlers = reader.readSizeKnownList(Math.abs(size.getValue()),
                    EncodedTypeAddrPair::new);
            if (size.getValue() <= 0) {
                catchAllAddr = reader.readUleb128();
            }
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Util.listWithoutNulls(size, handlers, catchAllAddr);
        }
        
    }
    
    public static class EncodedTypeAddrPair extends DexComponent {

        private Uleb128 typeIdx; // todo
        private Uleb128 addr;
        
        @Override
        protected void readContent(DexReader reader) {
            typeIdx = reader.readUleb128();
            addr = reader.readUleb128();
        }
        
        @Override
        protected void postRead(DexFile dexFile) {
            TypeIdItem typeId = dexFile.getTypeIdItem(typeIdx);
            String typeDesc = dexFile.getString(typeId.getDescriptorIdx());
            
            typeIdx.setDesc(typeIdx.getValue() + "->" + typeDesc);
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(typeIdx, addr);
        }
        
    }
    
}
