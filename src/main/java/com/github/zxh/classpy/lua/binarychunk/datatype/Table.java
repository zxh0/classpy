package com.github.zxh.classpy.lua.binarychunk.datatype;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.helper.StringHelper;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkComponent;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;

import java.util.List;
import java.util.function.Supplier;

/**
 * Table in binary chunk.
 */
public class Table extends BinaryChunkComponent {

    private final Supplier<BinaryChunkComponent> componentSupplier;

    public Table(Supplier<BinaryChunkComponent> componentSupplier) {
        this.componentSupplier = componentSupplier;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        CInt size = new CInt();
        size.read(reader);
        super.add("size", size);

        for (int i = 0; i < size.getValue(); i++) {
            BinaryChunkComponent c = componentSupplier.get();
            super.add(null, c);
            c.read(reader);
        }
    }

    @Override
    protected void postRead() {
        List<FileComponent> kids = super.getComponents();
        int maxIdx = kids.size() - 1;
        for (int i = 1; i < kids.size(); i++) {
            FileComponent kid = kids.get(i);
            if (kid.getName() == null) {
                kid.setName(StringHelper.formatIndex(maxIdx, i - 1));
            } else {
                kid.setName(StringHelper.formatIndex(maxIdx, i - 1)
                        + " (" + kid.getName() + ")");
            }
        }
        super.setDesc("(" + maxIdx + ")");
    }

}
