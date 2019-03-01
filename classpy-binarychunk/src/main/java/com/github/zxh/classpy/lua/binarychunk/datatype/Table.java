package com.github.zxh.classpy.lua.binarychunk.datatype;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.helper.StringHelper;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkReader;

import java.util.List;
import java.util.function.Supplier;

/**
 * Table in binary chunk.
 */
public class Table extends BinaryChunkPart {

    private final Supplier<BinaryChunkPart> partSupplier;

    public Table(Supplier<BinaryChunkPart> partSupplier) {
        this.partSupplier = partSupplier;
    }

    @Override
    protected void readContent(BinaryChunkReader reader) {
        CInt size = new CInt();
        size.read(reader);
        super.add("size", size);

        for (int i = 0; i < size.getValue(); i++) {
            BinaryChunkPart c = partSupplier.get();
            super.add(null, c);
            c.read(reader);
        }
    }

    @Override
    protected void postRead() {
        List<FilePart> kids = super.getParts();
        int maxIdx = kids.size() - 1;
        for (int i = 1; i < kids.size(); i++) {
            FilePart kid = kids.get(i);
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
