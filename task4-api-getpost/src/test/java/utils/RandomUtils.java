package utils;

import java.util.Random;

public class RandomUtils {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz ";

    public static int getRandomInt(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomChar = CHARACTERS.charAt(getRandomInt(CHARACTERS.length()));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
