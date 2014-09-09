package com.github.zxh.classpy.pecoff.header.file.optional;

import com.github.zxh.classpy.common.FileParseException;

/**
 *
 * @author zxh
 */
public class WindowsSubsystem {
 
    public static String getSubsystem(int value) {
        switch (value) {
            case  0: return "IMAGE_SUBSYSTEM_UNKNOWN"; // An unknown subsystem
            case  1: return "IMAGE_SUBSYSTEM_NATIVE"; // Device drivers and native Windows processes
            case  2: return "IMAGE_SUBSYSTEM_WINDOWS_GUI"; // The Windows graphical user interface (GUI) subsystem
            case  3: return "IMAGE_SUBSYSTEM_WINDOWS_CUI"; // The Windows character subsystem
            case  7: return "IMAGE_SUBSYSTEM_POSIX_CUI"; // The Posix character subsystem
            case  9: return "IMAGE_SUBSYSTEM_WINDOWS_CE_GUI"; // Windows CE
            case 10: return "IMAGE_SUBSYSTEM_EFI_APPLICATION"; // An Extensible Firmware Interface (EFI) application
            case 11: return "IMAGE_SUBSYSTEM_EFI_BOOT_SERVICE_DRIVER"; // An EFI driver with boot services
            case 12: return "IMAGE_SUBSYSTEM_EFI_RUNTIME_DRIVER"; // An EFI driver with run-time services
            case 13: return "IMAGE_SUBSYSTEM_EFI_ROM"; // An EFI ROM image
            case 14: return "IMAGE_SUBSYSTEM_XBOX"; // XBOX
            default: throw new FileParseException("Invalid Subsystem: " + value);
        }
    }
    
}
