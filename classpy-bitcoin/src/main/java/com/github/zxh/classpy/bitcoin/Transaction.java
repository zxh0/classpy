package com.github.zxh.classpy.bitcoin;

import com.github.zxh.classpy.bitcoin.types.Table;
import com.github.zxh.classpy.bitcoin.types.UInt32;

// https://en.bitcoin.it/wiki/Transaction
public class Transaction extends BlockComponent {

    @Override
    protected void readContent(BlockReader reader) {
        read(reader, "Version", new UInt32());
        Table txins = new Table(TxIn::new);
        txins.read(reader);
        if (txins.getParts().size() == 1 && reader.readByte() != 0) {
            read(reader, "Txins", new Table(TxIn::new));
            read(reader, "Txouts", new Table(TxOut::new));
            int nTxins = get("Txins").getParts().size() - 1;
            for (int i = 0; i < nTxins; i++) {
                read(reader, "Witnesses", new WitnessData());
            }
        } else {
            add("Txins", txins);
            read(reader, "Txouts", new Table(TxOut::new));
        }
        read(reader, "LockTime", new UInt32());
    }


    private static class WitnessData extends BlockComponent {

        @Override
        protected void readContent(BlockReader reader) {
            long n = reader.readVarInt();
            for (int i = 0; i < n; i++) {
                long m = reader.readVarInt();
                reader.readBytes((int) m);
            }
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
