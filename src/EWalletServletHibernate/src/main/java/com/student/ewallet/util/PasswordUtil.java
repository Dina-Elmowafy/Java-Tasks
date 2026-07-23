package com.student.ewallet.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public final class PasswordUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    private PasswordUtil() {}

    public static String hash(String password) {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt) + ":" + calculate(password, salt);
    }

    public static boolean matches(String password, String saved) {
        if (saved == null || !saved.contains(":")) return false;
        String[] parts = saved.split(":", 2);
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        return slowEquals(parts[1], calculate(password, salt));
    }

    private static String calculate(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65_536, 256);
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            throw new IllegalStateException("Could not hash password", e);
        }
    }

    private static boolean slowEquals(String a, String b) {
        int difference = a.length() ^ b.length();
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            difference |= a.charAt(i) ^ b.charAt(i);
        }
        return difference == 0;
    }
}
