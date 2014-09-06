package com.github.zxh.classpy.dexfile.body.data;

import com.github.zxh.classpy.dexfile.DexComponent;
import com.github.zxh.classpy.dexfile.DexFile;
import com.github.zxh.classpy.dexfile.DexReader;
import com.github.zxh.classpy.dexfile.datatype.Uleb128;
import com.github.zxh.classpy.dexfile.datatype.Uleb128p1;
import com.github.zxh.classpy.dexfile.list.SizeKnownList;

/**
 *
 * @author zxh
 */
public class DebugInfoItem extends DexComponent {

    private static final int NO_INDEX = -1;
    
    // header
    private Uleb128 lineStart;
    private Uleb128 parametersSize;
    private SizeKnownList<Uleb128p1> parameterNames;
    private Bytecodes bytecodes; // todo
    
    @Override
    protected void readContent(DexReader reader) {
        lineStart = reader.readUleb128();
        parametersSize = reader.readUleb128();
        parameterNames = reader.readSizeKnownList(parametersSize, Uleb128p1::new);
        bytecodes = new Bytecodes();
        bytecodes.read(reader);
    }

    @Override
    protected void postRead(DexFile dexFile) {
        for (Uleb128p1 index : parameterNames) {
            if (index.getValue() != NO_INDEX) {
                String name = dexFile.getString(index);
                index.setDesc(index.getValue() + "->" + name);
            }
        }
    }
    
    
    public static class Bytecodes extends DexComponent {

        @Override
        protected void readContent(DexReader reader) {
            while (true) {
                byte b = reader.readByte();
                if (b == 0) {
                    break;
                }
            }
        }
        
    }
    
}
