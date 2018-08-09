package com.github.zxh.classpy.lua.binarychunk.component;

import java.util.List;
import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.lua.binarychunk.BinaryChunkComponent;
import com.github.zxh.classpy.lua.binarychunk.datatype.CInt;
import com.github.zxh.classpy.lua.binarychunk.datatype.LuaStr;

/**
 * debug info.
 *
 * @see /lua/src/ldump.c#DumpDebug()
 */
public class Debug extends BinaryChunkComponent {

    {
        table("line_info",  CInt::new);
        table("loc_vars", LocVar::new);
        table("upvalues", LuaStr::new);
    }

    public long getLine(int pc) {
        List<FileComponent> locVars = super.get("line_info").getComponents();
        if (pc + 1 >= locVars.size()) {
            return -1;
        } else {
            return ((CInt) locVars.get(pc + 1)).getValue();
        }
    }

    public String getLocVarName(int idx) {
        List<FileComponent> locVars = super.get("loc_vars").getComponents();
        if (idx + 1 >= locVars.size()) {
            return "";
        } else {
            return ((LocVar) locVars.get(idx + 1)).getVarName();
        }
    }

    public String getUpvalName(int idx) {
        List<FileComponent> upvals = super.get("upvalues").getComponents();
        if (idx + 1 >= upvals.size()) {
            return "";
        } else {
            return upvals.get(idx + 1).getDesc();
        }
    }

    public static class LocVar extends BinaryChunkComponent {

        {
            str ("var_name");
            cint("start_pc");
            cint("end_pc"  );
        }

        @Override
        protected void postRead() {
            setName(get("var_name").getDesc());
            setDesc(get("start_pc").getDesc()
                    + " ~ " + get("end_pc").getDesc());
        }

        private String getVarName() {
            return super.get("var_name").getDesc();
        }

    }

}
