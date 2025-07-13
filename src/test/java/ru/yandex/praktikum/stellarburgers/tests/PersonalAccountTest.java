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

public class PersonalAccountTest extends BaseTest {
    
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
    @DisplayName("Переход в личный кабинет")
    @Description("Проверяем переход по клику на 'Личный кабинет' для авторизованного пользователя")
    public void testNavigationToPersonalAccount() {
        ProfilePage profilePage = mainPage.clickPersonalAccountButtonLoggedIn();
        profilePage.waitForPageLoad();
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after clicking Personal Account: " + currentUrl);
        Assert.assertTrue("Пользователь должен находиться на странице профиля. URL: " + currentUrl, 
                currentUrl.contains("/account/profile") || profilePage.isProfilePageDisplayed());
    }

    @After
    public void cleanUpUser() {
        if (testUser != null) {
            UserApiClient.deleteTestUser(testUser);
        }
    }
}