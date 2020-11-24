package com.github.zxh.classpy.llvm.bitcode;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.llvm.bitcode.enums.BlockIDs;
import com.github.zxh.classpy.llvm.bitcode.enums.BuiltinAbbrevIDs;
import com.github.zxh.classpy.llvm.bitcode.types.U32Dec;

import java.util.ArrayList;
import java.util.List;

// https://llvm.org/docs/BitCodeFormat.html#enter-subblock
// [ENTER_SUBBLOCK, blockid(vbr8), newabbrevlen(vbr4), <align32bits>, blocklen(32)]
public class Block extends BitCodePart {

    private final int abbrevLen;
    private List<DefineAbbrev> definedAbbrevs;

    public Block(int abbrevLen) {
        this.abbrevLen = abbrevLen;
    }

    @Override
    protected void readContent(BitCodeReader reader) {
        long abbrevID = reader.readFixed(abbrevLen);
        if (abbrevID != BuiltinAbbrevIDs.ENTER_SUBBLOCK) {
            throw new ParseException("invalid abbrevID, expected: 1, got: " + abbrevID);
        }
        long blockID = reader.readVBR(8);
        long newAbbrevLen = reader.readVBR(4);
        reader.align32bits();

        U32Dec blockLen = new U32Dec();
        add("blockLen", blockLen);
        blockLen.read(reader);

        setName(getBlockName(blockID));
        setDesc(String.format("id=%d, newAbbrevLen=%d, blockLen=%d",
                blockID, newAbbrevLen, blockLen.getValue()));
        readBlock1(reader, blockID, (int) newAbbrevLen, blockLen.getIntValue());
    }

    // TODO
    private void readBlock1(BitCodeReader reader, long blockID,
                            int newAbbrevLen, int blockLen) {
        int left = reader.remaining();
        int p1 = reader.getPosition();
        reader.setLimit(p1 + blockLen * 4);
        readBlock2(reader, blockID, newAbbrevLen);
        int p2 = reader.getPosition();
        reader.align32bits();
        reader.skipBytes(blockLen * 4 - (p2 - p1));
        reader.setLimit(p1 + left); // reset limit
    }

    // TODO
    private void readBlock2(BitCodeReader reader, long blockID,
                            int newAbbrevLen) {
        while (reader.remaining() > 0) {
            long abbrevID = reader.getFixed(newAbbrevLen);
            // TODO
            if (abbrevID != BuiltinAbbrevIDs.ENTER_SUBBLOCK) {
                reader.readFixed(newAbbrevLen);
            }

            if (abbrevID == BuiltinAbbrevIDs.ENTER_SUBBLOCK) {
                var block = new Block(newAbbrevLen);
                add("BLOCK", block);
                block.read(reader);
            } else if (abbrevID == BuiltinAbbrevIDs.DEFINE_ABBREV) {
                var defAbbr = new DefineAbbrev();
                add("DEFINE_ABBREV", defAbbr);
                defAbbr.read(reader);

                if (definedAbbrevs == null) {
                    definedAbbrevs = new ArrayList<>();
                }
                definedAbbrevs.add(defAbbr);
            } else if (abbrevID == BuiltinAbbrevIDs.UNABBREV_RECORD) {
                var record = new RecordUnAbbrev();
                add("UNABBREV_RECORD", record);
                record.read(reader);
                setChildName(blockID, record.code, record);
            } else if (abbrevID > 0
                    && !definedAbbrevs.isEmpty()
                    && definedAbbrevs.size() > abbrevID - BuiltinAbbrevIDs.FIRST_APP_DEFINED_ABBREV_ID) {
                DefineAbbrev defAbbr = definedAbbrevs.get((int) abbrevID - BuiltinAbbrevIDs.FIRST_APP_DEFINED_ABBREV_ID);
                var record = new RecordDefined(defAbbr);
                add("DEFINED_RECORD", record);
                record.read(reader);
            } else {
                throw new ParseException("TODO: abbrevID=" + abbrevID);
            }
        }
    }

    private static String getBlockName(long blockID) {
        if (blockID == 0) {
            return "BLOCKINFO_BLOCK";
        }
        for (var knownBlockID : BlockIDs.values()) {
            if (knownBlockID.getValue() == blockID) {
                return knownBlockID.name().replace("_ID", "");
            }
        }
        return "UNKNOWN_BLOCK";
    }

    // TODO
    private static void setChildName(long parentBlockID, long childCode,
                                     BitCodePart child) {
        if (parentBlockID == BlockIDs.MODULE_BLOCK_ID.getValue()) {
            if (childCode == 1) {
                child.setName("VERSION");
            }
        }
    }

}
