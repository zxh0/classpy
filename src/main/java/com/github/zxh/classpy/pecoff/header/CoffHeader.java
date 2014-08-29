package com.github.zxh.classpy.pecoff.header;

import com.github.zxh.classpy.common.FileParseException;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import com.github.zxh.classpy.pecoff.datatype.UInt16Hex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zxh
 */
public class CoffHeader extends PeComponent {

    private UInt16Hex machine;
    
    @Override
    protected void readContent(PeReader reader) {
        machine = reader.readUInt16Hex();
        machine.setDesc(machine.getDesc() + "(" + getMachineType(machine.getValue()) + ")");
    }
    
    private static String getMachineType(int value) {
        switch (value) {
            case 0x0: return "IMAGE_FILE_MACHINE_UNKNOWN";
            case 0x1d3: return "IMAGE_FILE_MACHINE_AM33";
            case 0x8664: return "case";
            case 0x1c0: return "IMAGE_FILE_MACHINE_ARM";
            case 0x1c4: return "IMAGE_FILE_MACHINE_ARMNT";
            case 0xaa64: return "IMAGE_FILE_MACHINE_ARM64";
            case 0xebc: return "IMAGE_FILE_MACHINE_EBC";
            case 0x14c: return "IMAGE_FILE_MACHINE_I386";
            case 0x200: return "IMAGE_FILE_MACHINE_IA64";
            case 0x9041: return "IMAGE_FILE_MACHINE_M32R";
            case 0x266: return "IMAGE_FILE_MACHINE_MIPS16";
            case 0x366: return "IMAGE_FILE_MACHINE_MIPSFPU";
            case 0x466: return "IMAGE_FILE_MACHINE_MIPSFPU16";
            case 0x1f0: return "IMAGE_FILE_MACHINE_POWERPC";
            case 0x1f1: return "IMAGE_FILE_MACHINE_POWERPCFP";
            case 0x166: return "IMAGE_FILE_MACHINE_R4000";
            case 0x1a2: return "IMAGE_FILE_MACHINE_SH3";
            case 0x1a3: return "IMAGE_FILE_MACHINE_SH3DSP";
            case 0x1a6: return "IMAGE_FILE_MACHINE_SH4";
            case 0x1a8: return "IMAGE_FILE_MACHINE_SH5";
            case 0x1c2: return "IMAGE_FILE_MACHINE_THUMB";
            case 0x169: return "IMAGE_FILE_MACHINE_WCEMIPSV2";
            default: throw new FileParseException("Invalid Machine Type: " + value);
        }
    }

    @Override
    public List<? extends PeComponent> getSubComponents() {
        return Arrays.asList(machine);
    }
    
}
