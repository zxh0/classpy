package com.github.zxh.classpy.luacout.component;

import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.datatype.CInt;
import com.github.zxh.classpy.luacout.datatype.LuaStr;

import java.util.List;

/**
 * debug info.
 *
 * @see /lua/src/ldump.c#DumpDebug()
 */
public class Debug extends LuacOutComponent {

    {
        table("line_info",  CInt::new);
        table("loc_vars", LocVar::new);
        table("upvalues", LuaStr::new);
    }

    public String getLocVarName(int idx) {
        List<BytesComponent> locVars = super.get("loc_vars").getComponents();
        if (idx + 1 >= locVars.size()) {
            return "";
        } else {
            return ((LocVar) locVars.get(idx + 1)).getVarName();
        }
    }


    public static class LocVar extends LuacOutComponent {

        {
            str ("var_name");
            cint("start_pc");
            cint("end_pc"  );
        }

        @Override
        protected void afterRead() {
            setName(get("var_name").getDesc());
            setDesc(get("start_pc").getDesc()
                    + " ~ " + get("end_pc").getDesc());
        }

        private String getVarName() {
            return super.get("var_name").getDesc();
        }

    }

}
