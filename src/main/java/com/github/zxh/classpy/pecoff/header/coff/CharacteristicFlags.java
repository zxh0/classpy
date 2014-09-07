package com.github.zxh.classpy.pecoff.header.coff;

/**
 *
 * @author zxh
 */
public enum CharacteristicFlags {
    
    // flags
    IMAGE_FILE_RELOCS_STRIPPED        (0x0001),
    IMAGE_FILE_EXECUTABLE_IMAGE       (0x0002),
    IMAGE_FILE_LINE_NUMS_STRIPPED     (0x0004),
    IMAGE_FILE_LOCAL_SYMS_STRIPPED    (0x0008),
    IMAGE_FILE_AGGRESSIVE_WS_TRIM     (0x0010),
    IMAGE_FILE_LARGE_ADDRESS_AWARE    (0x0020),
    RESERVED                          (0x0040),
    IMAGE_FILE_BYTES_REVERSED_LO      (0x0080),
    IMAGE_FILE_32BIT_MACHINE          (0x0100),
    IMAGE_FILE_DEBUG_STRIPPED         (0x0200),
    IMAGE_FILE_REMOVABLE_RUN_FROM_SWAP(0x0400),
    IMAGE_FILE_NET_RUN_FROM_SWAP      (0x0800),
    IMAGE_FILE_SYSTEM                 (0x1000),
    IMAGE_FILE_DLL                    (0x2000),
    IMAGE_FILE_UP_SYSTEM_ONLY         (0x4000),
    IMAGE_FILE_BYTES_REVERSED_HI      (0x8000);

    
    public final int value;

    private CharacteristicFlags(int value) {
        this.value = value;
    }
    
}
