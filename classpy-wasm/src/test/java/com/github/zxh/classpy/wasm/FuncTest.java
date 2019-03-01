package com.github.zxh.classpy.wasm;

import com.github.zxh.classpy.common.FilePart;
import com.github.zxh.classpy.wasm.instructions.Expr;
import com.github.zxh.classpy.wasm.instructions.Instr;
import com.github.zxh.classpy.wasm.sections.Code;
import org.junit.Test;

import java.util.Base64;

public class FuncTest {

    @Test
    public void read() {
        String base64 = "AQJ/IAIEfwNAIAAsAAAiAyABLAAAIgRGBEAgAEEBaiEAIAFBAWohAUEAIAJBf2oiAkUNAxoMAQsLIANB/wFxIARB/wFxawVBAAsPCw==";
        byte[] code = Base64.getDecoder().decode(base64);
        Code.Func func = new Code.Func();
        try {
            func.read(new WasmBinReader(code));
        } catch (Exception e) {
            new ExprPrinter().printExpr((Expr) func.getComponents().get(1));
            throw e;
        }
    }


    private static class ExprPrinter {

        private int indentation;

        private void printExpr(Expr expr) {
            for (FilePart fc : expr.getComponents()) {
                printInstr((Instr) fc);
            }
        }

        private void printInstr(Instr instr) {
            if (instr.getOpcode() == 0x0B
                    || instr.getOpcode() == 0x05) {
                indentation -= 1;
            }
            for (int i = 0; i < indentation; i++) {
                System.out.print("\t");
            }
            System.out.println(instr.getName());
            if (instr.getOpcode() == 0x02
                    || instr.getOpcode() == 0x03
                    || instr.getOpcode() == 0x04) {
                indentation += 1;
            }
            for (FilePart fc : instr.getComponents()) {
                if (fc instanceof Instr) {
                    printInstr((Instr) fc);
                }
            }
        }
    }

}
