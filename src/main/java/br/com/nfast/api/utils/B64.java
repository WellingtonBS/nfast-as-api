package br.com.nfast.api.utils;

import java.util.Base64;

public class B64 {

    public static String encode(byte[] content) {
        return Base64.getEncoder().encodeToString(content);
    }

    public static String decode(String content) {
        return new String(Base64.getDecoder().decode(content));
    }

    public static byte[] decodeToBytes(String content) {
        return Base64.getDecoder().decode(content);
    }

}
