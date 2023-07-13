package com.spring.boot.utils;

import java.util.Base64;

public class ImageUtil {
    private static String byteArrayToString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] stringToByteArray(String string) {
        return Base64.getDecoder().decode(string);
    }
}
