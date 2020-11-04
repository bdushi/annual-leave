package de.dlh.lhind.annualleave.common;

import java.security.SecureRandom;

public class Common {
    public static String generateRandomPassword(long length, int randNumOrigin, int randNumBound) {
        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(value -> Character.isAlphabetic(value) || Character.isDigit(value))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    public static final String PATH = "/error";
}
