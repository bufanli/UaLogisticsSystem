package com.rt.common.vo;

import com.rt.common.utils.Digests;
import com.rt.common.utils.Encodes;

public class HashPassword {


    public static final int INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final String ALGORITHM = "SHA-1";


    public String salt;
    public String password;


    public static HashPassword encrypt(String plainText) {
        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        result.salt = Encodes.encodeHex(salt);

        byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
        result.password = Encodes.encodeHex(hashPassword);
        return result;

    }

    public static String encryptPasswort(String pwd,String salt){
        byte[] _salt = Encodes.decodeHex(salt);
        byte[] hashPassword = Digests.sha1(pwd.getBytes(), _salt, INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }
}
