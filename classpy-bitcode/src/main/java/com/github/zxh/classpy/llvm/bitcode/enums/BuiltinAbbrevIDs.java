package com.github.zxh.classpy.llvm.bitcode.enums;

// https://llvm.org/docs/BitCodeFormat.html#abbreviation-ids
public class BuiltinAbbrevIDs {

    public static final int END_BLOCK       = 0; // — This abbrev ID marks the end of the current block.
    public static final int ENTER_SUBBLOCK  = 1; // — This abbrev ID marks the beginning of a new block.
    public static final int DEFINE_ABBREV   = 2; // — This defines a new abbreviation.
    public static final int UNABBREV_RECORD = 3; // — This ID specifies the definition of an unabbreviated record.

    public static final int FIRST_APP_DEFINED_ABBREV_ID = 4;

}
