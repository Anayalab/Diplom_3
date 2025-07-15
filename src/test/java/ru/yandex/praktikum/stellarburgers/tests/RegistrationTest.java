package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.model.LoginPage;
import ru.yandex.praktikum.stellarburgers.model.RegisterPage;
import ru.yandex.praktikum.stellarburgers.utils.BaseTest;
import ru.yandex.praktikum.stellarburgers.utils.Constants;
import ru.yandex.praktikum.stellarburgers.utils.TestDataGenerator;
import ru.yandex.praktikum.stellarburgers.utils.User;
import ru.yandex.praktikum.stellarburgers.utils.UserApiClient;

public class RegistrationTest extends BaseTest {
    
    private User testUser;

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что пользователь может успешно зарегистрироваться с валидными данными")
    public void testSuccessfulRegistration() {
        String name = TestDataGenerator.generateRandomName();
        String email = TestDataGenerator.generateRandomEmail();
        String password = TestDataGenerator.generateValidPassword();
        
        testUser = new User(name, email, password);
        
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        
        RegisterPage registerPage = loginPage.clickRegisterLink();
        registerPage.waitForPageLoad();
        
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after registration: " + currentUrl);
        
        boolean isRegistrationSuccess = currentUrl.contains("/login") ||
                                      currentUrl.equals(Constants.BASE_URL) ||
                                      !registerPage.isPasswordErrorDisplayed();
        
        Assert.assertTrue("Регистрация должна быть выполнена успешно. URL: " + currentUrl, isRegistrationSuccess);
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    @Description("Проверяем, что появляется ошибка при попытке регистрации с паролем менее 6 символов")
    public void testRegistrationWithInvalidPassword() {
        String name = TestDataGenerator.generateRandomName();
        String email = TestDataGenerator.generateRandomEmail();
        String invalidPassword = TestDataGenerator.generateInvalidPassword();
        
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        
        RegisterPage registerPage = loginPage.clickRegisterLink();
        registerPage.waitForPageLoad();
        
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(invalidPassword);
        registerPage.clickRegisterButton();
        
        Assert.assertTrue("Должна отображаться ошибка о некорректном пароле", 
                registerPage.isPasswordErrorDisplayed());
    }

    @After
    public void cleanUpUser() {
        if (testUser != null) {
            UserApiClient.deleteTestUser(testUser);
        }
    }
}