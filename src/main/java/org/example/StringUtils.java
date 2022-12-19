package org.example;

import java.util.Random;

public class StringUtils {
    public static String getRandomString() {
        String random = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * random.length());
            salt.append(random.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
