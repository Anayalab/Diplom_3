package ru.yandex.praktikum.stellarburgers.utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    
    private static final String REGISTER_ENDPOINT = "/auth/register";
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String USER_ENDPOINT = "/auth/user";

    static {
        RestAssured.baseURI = Constants.API_BASE_URL;
    }

    @Step("Регистрация пользователя через API: {user.email}")
    public static Response registerUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(REGISTER_ENDPOINT);
    }

    @Step("Авторизация пользователя через API: {email}")
    public static Response loginUser(String email, String password) {
        User loginData = new User(null, email, password);
        return given()
                .header("Content-Type", "application/json")
                .body(loginData)
                .post(LOGIN_ENDPOINT);
    }

    @Step("Удаление пользователя через API")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(USER_ENDPOINT);
    }

    @Step("Создание тестового пользователя")
    public static User createTestUser() {
        String name = TestDataGenerator.generateRandomName();
        String email = TestDataGenerator.generateRandomEmail();
        String password = TestDataGenerator.generateValidPassword();
        
        User user = new User(name, email, password);
        Response response = registerUser(user);
        
        if (response.getStatusCode() == 200) {
            return user;
        } else {
            throw new RuntimeException("Failed to create test user: " + response.asString());
        }
    }

    @Step("Удаление тестового пользователя")
    public static void deleteTestUser(User user) {
        try {
            Response loginResponse = loginUser(user.getEmail(), user.getPassword());
            if (loginResponse.getStatusCode() == 200) {
                String accessToken = loginResponse.path("accessToken");
                deleteUser(accessToken);
            }
        } catch (Exception e) {
            System.out.println("Warning: Could not delete test user: " + e.getMessage());
        }
    }
}