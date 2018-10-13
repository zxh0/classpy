package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockComponent;
import com.github.zxh.classpy.bitcoin.BlockReader;

import java.util.function.Supplier;

public class Table extends BlockComponent {

    private Supplier<? extends BlockComponent> supplier;
    private String componentName;

    public Table(Supplier<? extends BlockComponent> supplier) {
        this.supplier = supplier;
        componentName = supplier.get().getClass().getSimpleName();
    }

    @Override
    protected void readContent(BlockReader reader) {
        long count = readVarInt(reader, "Count");
        for (long i = 0; i < count; i++) {
            BlockComponent element = supplier.get();
            add(componentName + "#" + i, element);
            element.read(reader);
        }
        setDesc(Long.toString(count));
    }

}
