package com.github.zxh.classpy.luacout.component;

import java.util.List;
import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.datatype.CInt;
import com.github.zxh.classpy.luacout.datatype.LuByte;

/**
 * Lua function.
 *
 * @see /lua/src/ldump.c#DumpFunction()
 */
public class Function extends LuacOutComponent {

    {
        str    ("source"                  );
        cint   ("line_defined"            );
        cint   ("last_line_defined"       );
        lu_byte("num_params"              );
        lu_byte("is_vararg"               );
        lu_byte("max_stack_size"          );
        table  ("code",   Instruction::new);
        table  ("constants", Constant::new);
        table  ("upvalues",   UpValue::new);
        table  ("protos",    Function::new);
        add    ("debug",       new Debug());
    }

    @Override
    public void afterRead() {
        long lineDefined = ((CInt) super.get("line_defined")).getValue();
        long lastLineDefined = ((CInt) super.get("last_line_defined")).getValue();
        super.setDesc("<" + lineDefined + "," + lastLineDefined + ">");

        Debug debug = (Debug) super.get("debug");
        super.get("upvalues").getComponents().stream()
                .skip(1) // skip size
                .map(c -> (UpValue) c)
                .forEach(upval -> upval.setDesc(debug));

        List<BytesComponent> code = super.get("code").getComponents();
        for (int i = 1; i < code.size(); i++) {
            ((Instruction) code.get(i)).setDesc(i - 1, this, debug);
        }
    }

    public Constant getConstant(int idx) {
        return (Constant) super.get("constants").getComponents().get(idx + 1);
    }

    public String getLocVarName(int idx) {
        return ((Debug) super.get("debug")).getLocVarName(idx);
    }


    // @see /lua/src/ldump.c#DumpUpvalues()
    public static class UpValue extends LuacOutComponent {

        {
            lu_byte("instack");
            lu_byte("idx"    );
        }

        private void setDesc(Debug debug) {
            int idx = ((LuByte) super.get("idx")).getValue();
            super.setDesc(debug.getUpvalName(idx));
        }

    }

}
