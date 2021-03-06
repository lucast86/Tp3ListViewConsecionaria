package com.example.tp3listviewconsecionaria;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String convertirSHA256(String passs) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(passs.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash){
            sb.append(String.format("%02x",b));
        }

        return sb.toString();
    }
}
