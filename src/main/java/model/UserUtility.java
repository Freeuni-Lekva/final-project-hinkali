package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtility {

    public static String generateHash(String password) {
        MessageDigest md = null;
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(passwordBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] bytes = md.digest();
        StringBuilder builder = new StringBuilder();
        for (int aByte : bytes) {
            int val = aByte;
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) builder.append('0'); // leading 0
            builder.append(Integer.toString(val, 16));
        }
        return builder.toString();
    }
}
