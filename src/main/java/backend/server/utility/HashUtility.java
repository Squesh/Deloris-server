package backend.server.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashUtility {
    private static final int START_SALT_LENGTH = 10;
    private static final int ENDING_SALT_LENGTH = 20;

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
        String noSaltSampleHash = sampleHash.substring(START_SALT_LENGTH, sampleHash.length() - ENDING_SALT_LENGTH);
        String noSaltHash = hash.substring(START_SALT_LENGTH, hash.length() - ENDING_SALT_LENGTH);
        return noSaltSampleHash.equals(noSaltHash);
    }

    private String appendSalt(String hashed) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < START_SALT_LENGTH; i++) {
            builder.append(alphabet[randomInt(0, alphabet.length - 1)]);
        }
        builder.append(hashed);
        for (int i = 0; i < ENDING_SALT_LENGTH; i++) {
            builder.append(alphabet[randomInt(0, alphabet.length - 1)]);
        }

        return builder.toString();
    }

    private int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
