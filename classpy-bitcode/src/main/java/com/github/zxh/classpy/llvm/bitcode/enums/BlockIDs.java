package com.github.zxh.classpy.llvm.bitcode.enums;

public enum BlockIDs {

    // Blocks
    MODULE_BLOCK_ID ,//= FIRST_APPLICATION_BLOCKID,

    // Module sub-block id's.
    PARAMATTR_BLOCK_ID,
    PARAMATTR_GROUP_BLOCK_ID,

    CONSTANTS_BLOCK_ID,
    FUNCTION_BLOCK_ID,

    // Block intended to contains information on the bitcode versioning.
    // Can be used to provide better error messages when we fail to parse a
    // bitcode file.
    IDENTIFICATION_BLOCK_ID,

    VALUE_SYMTAB_BLOCK_ID,
    METADATA_BLOCK_ID,
    METADATA_ATTACHMENT_ID,

    TYPE_BLOCK_ID_NEW,

    USELIST_BLOCK_ID,

    MODULE_STRTAB_BLOCK_ID,
    GLOBALVAL_SUMMARY_BLOCK_ID,

    OPERAND_BUNDLE_TAGS_BLOCK_ID,

    METADATA_KIND_BLOCK_ID,

    STRTAB_BLOCK_ID,

    FULL_LTO_GLOBALVAL_SUMMARY_BLOCK_ID,

    SYMTAB_BLOCK_ID,

    SYNC_SCOPE_NAMES_BLOCK_ID,
    ;

    public static final int FIRST_APPLICATION_BLOCKID = 8;

    public int getValue() {
        return ordinal() + FIRST_APPLICATION_BLOCKID;
    }

}
