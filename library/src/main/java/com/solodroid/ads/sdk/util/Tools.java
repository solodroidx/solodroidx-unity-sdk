package com.solodroid.ads.sdk.util;

import static com.solodroid.ads.sdk.util.Constant.TOKEN;
import static com.solodroid.ads.sdk.util.Constant.VALUE;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Tools {

    public static String decode(String code) {
        return decodeBase64(decodeBase64(decodeBase64(code)));
    }

    public static String decodeBase64(String code) {
        byte[] valueDecoded = Base64.decode(code.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        return new String(valueDecoded);
    }

    public static String jsonDecode(String code) {
        String data = code.replace(TOKEN, VALUE);
        byte[] valueDecoded = Base64.decode(data.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        return new String(valueDecoded);
    }

}
