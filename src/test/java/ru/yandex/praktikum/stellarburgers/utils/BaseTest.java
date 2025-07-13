package ru.yandex.praktikum.stellarburgers.utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.stellarburgers.model.MainPage;

public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.createDriver(browser);
        driver.get(BASE_URL);
        mainPage = new MainPage(driver);
        mainPage.waitForPageLoad();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}