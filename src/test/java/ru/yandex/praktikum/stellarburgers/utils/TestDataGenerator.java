package ru.yandex.praktikum.stellarburgers.utils;

import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();

    public static String generateRandomEmail() {
        return "testuser" + System.currentTimeMillis() + "@test.com";
    }

    public static String generateRandomName() {
        return "TestUser" + random.nextInt(10000);
    }

    public static String generateValidPassword() {
        return "password" + random.nextInt(1000);
    }

    public static String generateInvalidPassword() {
        return "123";
    }
}