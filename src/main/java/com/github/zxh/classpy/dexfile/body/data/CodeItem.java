package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.UInt;
import com.github.zxh.classpy.dexfile.datatype.UIntHex;
import com.github.zxh.classpy.dexfile.datatype.UShort;
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
    // handlers
    
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
                debugInfoOff, insnsSize, padding, tries);
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
    
}
