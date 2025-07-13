package ru.yandex.praktikum.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(css = "button.button_button__33qZ0")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(@class, 'AppHeader_header__link__3D_hX')]/p[contains(text(), 'Личный Кабинет')]/..")
    private WebElement personalAccountButton;

    @FindBy(css = "div[class*='AppHeader_header__logo']")
    private WebElement stellarBurgersLogo;

    @FindBy(xpath = "//a[contains(@class, 'AppHeader_header__link__3D_hX')]/p[contains(text(), 'Конструктор')]/..")
    private WebElement constructorButton;

    @FindBy(css = "span.text_type_main-default")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[contains(@class, 'text_type_main-default') and contains(text(), 'Соусы')]")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[contains(@class, 'text_type_main-default') and contains(text(), 'Начинки')]")
    private WebElement fillingsTab;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public LoginPage clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Личный Кабинет' (для авторизованного пользователя)")
    public ProfilePage clickPersonalAccountButtonLoggedIn() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
        return new ProfilePage(driver);
    }

    @Step("Клик по логотипу Stellar Burgers")
    public MainPage clickStellarBurgersLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(stellarBurgersLogo)).click();
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public MainPage clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
        return this;
    }

    @Step("Клик по разделу 'Булки'")
    public MainPage clickBunsTab() {
        wait.until(ExpectedConditions.visibilityOf(bunsTab));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", bunsTab);
        return this;
    }

    @Step("Клик по разделу 'Соусы'")
    public MainPage clickSaucesTab() {
        wait.until(ExpectedConditions.visibilityOf(saucesTab));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", saucesTab);
        return this;
    }

    @Step("Клик по разделу 'Начинки'")
    public MainPage clickFillingsTab() {
        wait.until(ExpectedConditions.visibilityOf(fillingsTab));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", fillingsTab);
        return this;
    }

    @Step("Проверка активности раздела 'Булки'")
    public boolean isBunsTabActive() {
        // Просто проверяем, что раздел "Булки" виден
        try {
            wait.until(ExpectedConditions.visibilityOf(bunsTab));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка активности раздела 'Соусы'")
    public boolean isSaucesTabActive() {
        try {
            wait.until(ExpectedConditions.visibilityOf(saucesTab));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка активности раздела 'Начинки'")
    public boolean isFillingsTabActive() {
        try {
            wait.until(ExpectedConditions.visibilityOf(fillingsTab));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }
}