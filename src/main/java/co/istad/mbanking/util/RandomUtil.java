package co.istad.mbanking.util;

import java.util.Random;

public class RandomUtil {
    public static String generateRandom9Digits() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10)); // 0–9
        }

        return sb.toString();
    }
}
