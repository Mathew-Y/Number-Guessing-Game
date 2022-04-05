package com.example.myapplicationnew.authUtils;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class PasswordEncryption {
    public static String encrypt(String password) {
        return Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
    }

    public static String decrypt(String hash) {
        return new String(Base64.decode(hash, Base64.DEFAULT));
    }
}
