package com.github.zxh.classpy.luacout.component;

import java.util.List;
import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.luacout.LuacOutComponent;
import com.github.zxh.classpy.luacout.datatype.CInt;
import com.github.zxh.classpy.luacout.datatype.LuaStr;

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

    public long getLine(int pc) {
        List<BytesComponent> locVars = super.get("line_info").getComponents();
        if (pc + 1 >= locVars.size()) {
            return -1;
        } else {
            return ((CInt) locVars.get(pc + 1)).getValue();
        }
    }

    public String getLocVarName(int idx) {
        List<BytesComponent> locVars = super.get("loc_vars").getComponents();
        if (idx + 1 >= locVars.size()) {
            return "";
        } else {
            return ((LocVar) locVars.get(idx + 1)).getVarName();
        }
    }

    public String getUpvalName(int idx) {
        List<BytesComponent> upvals = super.get("upvalues").getComponents();
        if (idx + 1 >= upvals.size()) {
            return "";
        } else {
            return upvals.get(idx + 1).getDesc();
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
