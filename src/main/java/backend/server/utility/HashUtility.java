package backend.server.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashUtility {
    private static final int SALT_LENGTH = 15;

    private MessageDigest md;
    private Random random;
    private char[] alphabet;
    private static final String ALPHABET_BASE = "1234567890abcdef";

    public HashUtility() {
        random = new Random();
        alphabet = new char[ALPHABET_BASE.length()];
        for (int i = 0; i < ALPHABET_BASE.length(); i++) {
            alphabet[i] = ALPHABET_BASE.charAt(i);
        }
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ignored) {}
    }

    public String hashPassword(String password) {
        byte[] digest = md.digest(password.getBytes());
        String noSaltHash = String.format("%064x", new java.math.BigInteger(1, digest));
        return appendSalt(noSaltHash);
    }

    public boolean isHashEquals(String sample, String hash) {
        String sampleHash = hashPassword(sample);
        return sampleHash.substring(0, sampleHash.length() - SALT_LENGTH).equals(hash.substring(0, hash.length() - SALT_LENGTH));
    }

    private String appendSalt(String hashed) {
        StringBuilder builder = new StringBuilder(hashed);
        for (int i = 0; i < SALT_LENGTH; i++) {
            builder.append(alphabet[randomInt(0, alphabet.length - 1)]);
        }
        return builder.toString();
    }

    private int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
