package com.github.zxh.classpy.bitcoin;

import com.github.zxh.classpy.common.FileParser;

public class TxParser implements FileParser {

    @Override
    public Transaction parse(byte[] data) {
        Transaction tx = new Transaction();
        try {
            tx.read(new BlockReader(data));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return tx;
    }

}
