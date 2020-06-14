package com.rt.common.utils.sign;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ParamSignUtils {
    public ParamSignUtils() {
    }

    public static String genSign(HashMap<String, String> params, String secretKey) {
        Map<String, String> sortParams = new TreeMap(params);
        Set<Map.Entry<String, String>> entrys = sortParams.entrySet();
        StringBuilder baseString = new StringBuilder();
        Iterator i$ = entrys.iterator();

        while(i$.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)i$.next();
            baseString.append((String)entry.getKey()).append("=").append((String)entry.getValue());
        }

        baseString.append(secretKey);
        byte[] bytes = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            try {
                bytes = md5.digest(baseString.toString().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException var10) {
                var10.printStackTrace();
            }
        } catch (NoSuchAlgorithmException var11) {
            var11.printStackTrace();
        }

        StringBuilder sign = new StringBuilder();

        for(int i = 0; i < bytes.length; ++i) {
            String hex = Integer.toHexString(bytes[i] & 255);
            if (hex.length() == 1) {
                sign.append("0");
            }

            sign.append(hex);
        }

        return sign.toString();
    }
}

