package com.github.zxh.classpy.luacout.datatype;

import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.helper.StringHelper;
import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.LuacOutReader;

import java.util.List;
import java.util.function.Supplier;

/**
 * Table in luac.out
 */
public class Table extends LuacOutComponent {

    private final Supplier<LuacOutComponent> componentSupplier;

    public Table(Supplier<LuacOutComponent> componentSupplier) {
        this.componentSupplier = componentSupplier;
    }

    @Override
    protected void readContent(LuacOutReader reader) {
        CInt size = new CInt();
        size.read(reader);
        super.add("size", size);

        for (int i = 0; i < size.getValue(); i++) {
            LuacOutComponent c = componentSupplier.get();
            super.add(null, c);
            c.read(reader);
        }
    }

    @Override
    protected void afterRead() {
        List<BytesComponent> kids = super.getComponents();
        int maxIdx = kids.size() - 1;
        for (int i = 1; i < kids.size(); i++) {
            BytesComponent kid = kids.get(i);
            if (kid.getName() == null) {
                kid.setName(StringHelper.formatIndex(maxIdx, i - 1));
            } else {
                kid.setName(StringHelper.formatIndex(maxIdx, i - 1)
                        + " (" + kid.getName() + ")");
            }
        }
        super.setDesc("[" + maxIdx + "]");
    }

}
