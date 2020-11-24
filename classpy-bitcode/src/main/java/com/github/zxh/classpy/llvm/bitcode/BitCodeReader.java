package com.github.zxh.classpy.llvm.bitcode;

import com.github.zxh.classpy.common.BytesReader;
import com.github.zxh.classpy.common.ParseException;

import java.nio.ByteOrder;

// BitStream
public class BitCodeReader extends BytesReader {

    private long curWord;
    private int bitsInCurWord;

    public BitCodeReader(byte[] data) {
        super(data, ByteOrder.LITTLE_ENDIAN);
    }

    private void readNextWord() {
        long nextWord = readFixedU32();
        curWord = (nextWord << bitsInCurWord) | curWord;
        bitsInCurWord += 32;
    }

    public void align32bits() {
        curWord = 0;
        bitsInCurWord = 0;
    }

    // https://llvm.org/docs/BitCodeFormat.html#fixed-width-value
    public long readFixed(int numBits) {
        long x = getFixed(numBits);
        curWord >>>= numBits;
        bitsInCurWord -= numBits;
        return x;
    }

    public long getFixed(int numBits) {
        if (numBits < 0 || numBits > 32) {
            throw new ParseException("no or too many bits to read: " + numBits);
        }
        if (bitsInCurWord < numBits) {
            readNextWord();
        }
//        System.out.printf("readFixed, numBits=%d, curWord=%x, bitsInCurWord=%d\n",
//                numBits, curWord, bitsInCurWord);
        return curWord & ~(-1 << numBits);
    }

    // https://llvm.org/docs/BitCodeFormat.html#variable-width-value
    public long readVBR(int numBits) {
        if (numBits < 0 || numBits > 8) {
            throw new ParseException("no or too many bits to read VBR: " + numBits);
        }

        long msb = 1 << (numBits - 1);
        long mask = ~(-1 << (numBits - 1));

        long result = 0;
        for (int shift = 0; shift < 64; shift += numBits - 1) {
            long n = readFixed(numBits);
            result |= ((n & mask) << shift);
            if ((n & msb) == 0) {
                return result;
            }
        }

        throw new ParseException("invalid VBR ?");
    }

}
