package com.example.numberguessinggame.authUtils;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordEncryption {
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }
}
