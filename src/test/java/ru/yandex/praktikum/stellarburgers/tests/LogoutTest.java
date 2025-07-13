package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.model.LoginPage;
import ru.yandex.praktikum.stellarburgers.model.ProfilePage;
import ru.yandex.praktikum.stellarburgers.utils.BaseTest;
import ru.yandex.praktikum.stellarburgers.utils.User;
import ru.yandex.praktikum.stellarburgers.utils.UserApiClient;

public class LogoutTest extends BaseTest {
    
    private User testUser;

    @Before
    public void createAndLoginUser() {
        testUser = UserApiClient.createTestUser();
        
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        
        mainPage = loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.waitForPageLoad();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверяем выход по кнопке 'Выйти' в личном кабинете")
    public void testLogout() {
        ProfilePage profilePage = mainPage.clickPersonalAccountButtonLoggedIn();
        profilePage.waitForPageLoad();
        
        profilePage.clickLogoutButton();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after logout: " + currentUrl);
        
        Assert.assertTrue("Пользователь должен быть перенаправлен после выхода. URL: " + currentUrl,
                currentUrl.contains("/login") || currentUrl.equals("https://stellarburgers.nomoreparties.site/"));
    }

    @After
    public void cleanUpUser() {
        if (testUser != null) {
            UserApiClient.deleteTestUser(testUser);
        }
    }
}