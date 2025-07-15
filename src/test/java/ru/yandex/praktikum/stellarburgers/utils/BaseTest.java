package ru.yandex.praktikum.stellarburgers.utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.stellarburgers.model.MainPage;

public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.createDriver(browser);
        driver.get(Constants.BASE_URL);
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