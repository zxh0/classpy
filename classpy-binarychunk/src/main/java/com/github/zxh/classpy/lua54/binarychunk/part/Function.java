package com.github.zxh.classpy.lua54.binarychunk.part;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuByte;
import com.github.zxh.classpy.lua54.binarychunk.datatype.VarInt;

import java.util.List;

// lua5.4.1/lundump.c#loadFunction()
public class Function extends BinaryChunkPart {

    {
        str    ("source"                  );
        varInt ("line_defined"            );
        varInt ("last_line_defined"       );
        lu_byte("num_params"              );
        lu_byte("is_vararg"               );
        lu_byte("max_stack_size"          );
        vector ("code",   Instruction::new);
        vector ("constants", Constant::new);
        vector ("upvalues",   UpValue::new);
        vector ("protos",    Function::new);
        add    ("debug",       new Debug());
    }

    @Override
    public void postRead() {
        long lineDefined = ((VarInt) super.get("line_defined")).getValue();
        long lastLineDefined = ((VarInt) super.get("last_line_defined")).getValue();
        super.setDesc("<" + lineDefined + "," + lastLineDefined + ">");

        Debug debug = (Debug) super.get("debug");
        super.get("upvalues").getParts().stream()
                .skip(1) // skip size
                .map(c -> (UpValue) c)
                .forEach(upval -> upval.setDesc(debug));

        List<FilePart> code = super.get("code").getParts();
        for (int i = 1; i < code.size(); i++) {
            ((Instruction) code.get(i)).updateDesc();
        }
    }

    public String getConstant(int idx) {
        return super.get("constants").getParts().get(idx + 1).getDesc();
    }


    // @see /lua/src/ldump.c#DumpUpvalues()
    public static class UpValue extends BinaryChunkPart {

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
