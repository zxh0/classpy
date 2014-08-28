package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
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
    private UShort padding;
    private SizeKnownList<TryItem> tries;
    private EncodedCatchHandlerList handlers;
    
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
        tries = reader.readSizeKnownList(insSize, TryItem::new);
//        handlers = new EncodedCatchHandlerList();
//        handlers.read(reader);
    }
    
    private void readPadding(DexReader reader) {
        if ((reader.getPosition() % 4) != 0) {
            padding = reader.readUShort();
        } else {
            padding = new UShort();
            padding.readNothing(reader);
        }
    }

    @Override
    public List<? extends DexComponent> getSubComponents() {
        return Arrays.asList(registersSize, insSize, outsSize, triesSize,
                debugInfoOff, insnsSize, padding, tries/*, handlers*/);
    }
    
    
    public static class TryItem extends DexComponent {

        private UInt startAddr; // todo
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
        private Uleb128 catchAllAddr;
        
        @Override
        protected void readContent(DexReader reader) {
            size = reader.readSleb128();
            handlers = reader.readSizeKnownList(Math.abs(size.getValue()),
                    EncodedTypeAddrPair::new);
            if (size.getValue() <= 0) {
                catchAllAddr = reader.readUleb128();
            } else {
                catchAllAddr = new Uleb128();
                catchAllAddr.readNothing(reader);
            }
        }
        
        @Override
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(size, handlers, catchAllAddr);
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
        public List<? extends DexComponent> getSubComponents() {
            return Arrays.asList(typeIdx, addr);
        }
        
    }
    
}
