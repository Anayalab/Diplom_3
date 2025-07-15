package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.stellarburgers.model.LoginPage;
import ru.yandex.praktikum.stellarburgers.model.ProfilePage;
import ru.yandex.praktikum.stellarburgers.utils.BaseTest;
import ru.yandex.praktikum.stellarburgers.utils.Constants;
import ru.yandex.praktikum.stellarburgers.utils.User;
import ru.yandex.praktikum.stellarburgers.utils.UserApiClient;

import java.time.Duration;

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
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/login"),
            ExpectedConditions.urlToBe(Constants.BASE_URL)
        ));
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after logout: " + currentUrl);
        
        Assert.assertTrue("Пользователь должен быть перенаправлен после выхода. URL: " + currentUrl,
                currentUrl.contains("/login") || currentUrl.equals(Constants.BASE_URL));
    }

    @After
    public void cleanUpUser() {
        if (testUser != null) {
            UserApiClient.deleteTestUser(testUser);
        }
    }
}