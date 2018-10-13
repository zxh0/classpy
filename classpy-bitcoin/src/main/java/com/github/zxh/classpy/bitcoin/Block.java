package com.github.zxh.classpy.bitcoin;

public class Block extends BlockComponent {

    {
        uint32("Version");
        hash  ("HashPrevBlock");
        hash  ("HashMerkleRoot");
        uint32("Time");
        uint32("Bits");
        uint32("Nonce");
        table ("Transactions", Transaction::new);
    }

    // https://en.bitcoin.it/wiki/Transaction
    private class Transaction extends BlockComponent {

        {
            uint32("Version");
            table ("Txins", TxIn::new);
            table ("Txouts", TxOut::new);
            uint32("LockTime");
        }

    }

    private static class TxIn extends BlockComponent {

        {
            hash  ("PreviousTransactionHash");
            uint32("PreviousTxoutIndex");
            script("TxinScript");
            bytes ("SequenceNo", 4);
        }

    }

    private static class TxOut extends BlockComponent {

        {
            uint64("value");
            script("TxoutScript");
        }

    }

}
