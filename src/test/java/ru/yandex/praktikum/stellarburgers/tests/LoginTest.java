package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.model.ForgotPasswordPage;
import ru.yandex.praktikum.stellarburgers.model.LoginPage;
import ru.yandex.praktikum.stellarburgers.model.MainPage;
import ru.yandex.praktikum.stellarburgers.model.RegisterPage;
import ru.yandex.praktikum.stellarburgers.utils.BaseTest;
import ru.yandex.praktikum.stellarburgers.utils.User;
import ru.yandex.praktikum.stellarburgers.utils.UserApiClient;

public class LoginTest extends BaseTest {
    
    private User testUser;

    @Before
    public void createTestUser() {
        testUser = UserApiClient.createTestUser();
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Проверяем вход через кнопку 'Войти в аккаунт' на главной странице")
    public void testLoginFromMainPageButton() {
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        
        MainPage resultPage = loginPage.login(testUser.getEmail(), testUser.getPassword());
        resultPage.waitForPageLoad();
        
        Assert.assertTrue("Пользователь должен быть перенаправлен на главную страницу после входа", 
                driver.getCurrentUrl().contains("/"));
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверяем вход через кнопку 'Личный кабинет'")
    public void testLoginFromPersonalAccountButton() {
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        loginPage.waitForPageLoad();
        
        MainPage resultPage = loginPage.login(testUser.getEmail(), testUser.getPassword());
        resultPage.waitForPageLoad();
        
        Assert.assertTrue("Пользователь должен быть перенаправлен на главную страницу после входа", 
                driver.getCurrentUrl().contains("/"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяем вход через ссылку 'Войти' в форме регистрации")
    public void testLoginFromRegistrationForm() {
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        
        RegisterPage registerPage = loginPage.clickRegisterLink();
        registerPage.waitForPageLoad();
        
        LoginPage loginPageFromRegister = registerPage.clickLoginLink();
        loginPageFromRegister.waitForPageLoad();
        
        MainPage resultPage = loginPageFromRegister.login(testUser.getEmail(), testUser.getPassword());
        resultPage.waitForPageLoad();
        
        Assert.assertTrue("Пользователь должен быть перенаправлен на главную страницу после входа", 
                driver.getCurrentUrl().contains("/"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяем вход через ссылку 'Войти' в форме восстановления пароля")
    public void testLoginFromForgotPasswordForm() {
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        
        ForgotPasswordPage forgotPasswordPage = loginPage.clickForgotPasswordLink();
        forgotPasswordPage.waitForPageLoad();
        
        LoginPage loginPageFromForgot = forgotPasswordPage.clickLoginLink();
        loginPageFromForgot.waitForPageLoad();
        
        MainPage resultPage = loginPageFromForgot.login(testUser.getEmail(), testUser.getPassword());
        resultPage.waitForPageLoad();
        
        Assert.assertTrue("Пользователь должен быть перенаправлен на главную страницу после входа", 
                driver.getCurrentUrl().contains("/"));
    }

    @After
    public void cleanUpUser() {
        if (testUser != null) {
            UserApiClient.deleteTestUser(testUser);
        }
    }
}