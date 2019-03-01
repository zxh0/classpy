package com.github.zxh.classpy.lua.binarychunk.component;

import java.util.List;
import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkComponent;
import com.github.zxh.classpy.lua.binarychunk.datatype.CInt;
import com.github.zxh.classpy.lua.binarychunk.datatype.LuByte;

/**
 * Lua function.
 *
 * @see /lua/src/ldump.c#DumpFunction()
 */
public class Function extends BinaryChunkComponent {

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
    public void postRead() {
        long lineDefined = ((CInt) super.get("line_defined")).getValue();
        long lastLineDefined = ((CInt) super.get("last_line_defined")).getValue();
        super.setDesc("<" + lineDefined + "," + lastLineDefined + ">");

        Debug debug = (Debug) super.get("debug");
        super.get("upvalues").getParts().stream()
                .skip(1) // skip size
                .map(c -> (UpValue) c)
                .forEach(upval -> upval.setDesc(debug));

        List<FilePart> code = super.get("code").getParts();
        for (int i = 1; i < code.size(); i++) {
            ((Instruction) code.get(i)).setDesc(i - 1, this, debug);
        }
    }

    public String getConstant(int idx) {
        return super.get("constants").getParts().get(idx + 1).getDesc();
    }


    // @see /lua/src/ldump.c#DumpUpvalues()
    public static class UpValue extends BinaryChunkComponent {

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
