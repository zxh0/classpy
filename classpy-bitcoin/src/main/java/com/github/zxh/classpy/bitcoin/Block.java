package com.github.zxh.classpy.bitcoin;

public class Block extends BlockPart {

    {
        uint32("Version");
        hash  ("HashPrevBlock");
        hash  ("HashMerkleRoot");
        uint32("Time");
        uint32("Bits");
        uint32("Nonce");
        table ("Transactions", Transaction::new);
    }

}
