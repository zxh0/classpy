package com.github.zxh.classpy.ethereum;

public class EvmHelper {

    public static byte[] decodeHexStr(String hexStr) {
        if (hexStr.startsWith("0x")) {
            hexStr = hexStr.substring(2);
        }
        byte[] data = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            String hex = hexStr.substring(i * 2, i * 2 + 2);
            data[i] = (byte) Integer.parseInt(hex, 16);
        }
        return data;
    }

    public static String encodeHexStr(byte[] data) {
        StringBuilder sb = new StringBuilder("0x");
        for (byte b : data) {
            sb.append(Integer.toHexString(b & 0xFF).toUpperCase());
        }
        return sb.toString();
    }

}
