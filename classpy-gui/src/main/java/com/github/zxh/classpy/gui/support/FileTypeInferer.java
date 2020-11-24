package com.github.zxh.classpy.gui.support;

import java.util.Arrays;

public class FileTypeInferer {

    private static final byte[] wasmMagicNumber = {0, 'a', 's', 'm'};
    private static final byte[] binaryChunkSig = {0x1B, 'L', 'u', 'a'};
    private static final byte[] classMagicNumber = {
            (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE
    };

    public static FileType inferFileType(String url) {
        url = url.toLowerCase();
        if (url.startsWith("https://blockchain.info/rawblock")) {
            return FileType.BITCOIN_BLOCK;
        } else if (url.startsWith("https://blockchain.info/rawtx")) {
            return FileType.BITCOIN_TX;
        } else if (url.endsWith(".jar")) {
            return FileType.JAVA_JAR;
        } else if (url.endsWith(".jmod")) {
            return FileType.JAVA_JMOD;
        } else if (url.endsWith(".class")) {
            return FileType.JAVA_CLASS;
        } else if (url.endsWith(".luac")) {
            return FileType.LUA_BC;
        } else if (url.endsWith(".wasm")) {
            return FileType.WASM;
        } else if (url.endsWith(".bc")) {
            return FileType.BITCODE;
        } else {
            return FileType.UNKNOWN;
        }
    }

    public static FileType inferFileType(byte[] data) {
        if (data.length >= 4) {
            byte[] magicNumber = Arrays.copyOf(data, 4);
            if (Arrays.equals(magicNumber, classMagicNumber)) {
                return FileType.JAVA_CLASS;
            } else if (Arrays.equals(magicNumber, binaryChunkSig)) {
                return FileType.LUA_BC;
            } else if (Arrays.equals(magicNumber, wasmMagicNumber)) {
                return FileType.WASM;
            }
        }
        return FileType.UNKNOWN;
    }

}
