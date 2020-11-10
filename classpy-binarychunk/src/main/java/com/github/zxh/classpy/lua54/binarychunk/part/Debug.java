package com.github.zxh.classpy.lua54.binarychunk.part;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.lua54.binarychunk.BinaryChunkPart;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuByte;
import com.github.zxh.classpy.lua54.binarychunk.datatype.LuaStr;

import java.util.List;

// lua5.4.1/lundump.c#loadDebug()
public class Debug extends BinaryChunkPart {

    {
        vector("line_info",     LuByte::new);
        vector("abs_line_info", AbsLineInfo::new);
        vector("loc_vars",      LocVar::new);
        vector("upvalues",      LuaStr::new);
    }

    // TODO
    public long getLine(int pc) {
        return -1;
    }

    public String getLocVarName(int idx) {
        List<FilePart> locVars = super.get("loc_vars").getParts();
        if (idx + 1 >= locVars.size()) {
            return "";
        } else {
            return ((LocVar) locVars.get(idx + 1)).getVarName();
        }
    }

    public String getUpvalName(int idx) {
        List<FilePart> upvals = super.get("upvalues").getParts();
        if (idx + 1 >= upvals.size()) {
            return "";
        } else {
            return upvals.get(idx + 1).getDesc();
        }
    }

    public static class AbsLineInfo extends BinaryChunkPart {

        {
            varInt("pc");
            varInt("line");
        }

    }

    public static class LocVar extends BinaryChunkPart {

        {
            str   ("var_name");
            varInt("start_pc");
            varInt("end_pc"  );
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
