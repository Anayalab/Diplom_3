package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.model.LoginPage;
import ru.yandex.praktikum.stellarburgers.model.MainPage;
import ru.yandex.praktikum.stellarburgers.model.ProfilePage;
import ru.yandex.praktikum.stellarburgers.utils.BaseTest;
import ru.yandex.praktikum.stellarburgers.utils.Constants;
import ru.yandex.praktikum.stellarburgers.utils.User;
import ru.yandex.praktikum.stellarburgers.utils.UserApiClient;

public class NavigationTest extends BaseTest {
    
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
    @DisplayName("Переход из личного кабинета в конструктор по кнопке 'Конструктор'")
    @Description("Проверяем переход по клику на 'Конструктор' из личного кабинета")
    public void testNavigationFromProfileToConstructorByButton() {
        ProfilePage profilePage = mainPage.clickPersonalAccountButtonLoggedIn();
        profilePage.waitForPageLoad();
        
        MainPage resultPage = profilePage.clickConstructorButton();
        resultPage.waitForPageLoad();
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after navigation to constructor by button: " + currentUrl);
        Assert.assertTrue("Пользователь должен быть перенаправлен на главную страницу. URL: " + currentUrl, 
                currentUrl.equals(Constants.BASE_URL) || 
                (currentUrl.contains("/") && !currentUrl.contains("/profile") && !currentUrl.contains("/account")));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по логотипу")
    @Description("Проверяем переход по клику на логотип Stellar Burgers из личного кабинета")
    public void testNavigationFromProfileToConstructorByLogo() {
        ProfilePage profilePage = mainPage.clickPersonalAccountButtonLoggedIn();
        profilePage.waitForPageLoad();
        
        MainPage resultPage = profilePage.clickStellarBurgersLogo();
        resultPage.waitForPageLoad();
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after navigation to constructor by logo: " + currentUrl);
        Assert.assertTrue("Пользователь должен быть перенаправлен на главную страницу. URL: " + currentUrl, 
                currentUrl.equals(Constants.BASE_URL) || 
                (currentUrl.contains("/") && !currentUrl.contains("/profile") && !currentUrl.contains("/account")));
    }

    @After
    public void cleanUpUser() {
        if (testUser != null) {
            UserApiClient.deleteTestUser(testUser);
        }
    }
}