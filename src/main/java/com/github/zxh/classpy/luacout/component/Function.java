package com.github.zxh.classpy.luacout.component;

import com.github.zxh.classpy.luacout.LuacOutComponent;

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
        super.get("code")
                .getComponents()
                .stream()
                .skip(1) // skip size
                .map(c -> (Instruction) c)
                .forEach(inst -> inst.expandOperands(this));
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

    }

}
