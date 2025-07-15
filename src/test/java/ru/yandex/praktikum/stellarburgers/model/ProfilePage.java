package ru.yandex.praktikum.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Выход')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[contains(@class, 'AppHeader_header__link__3D_hX')]/p[contains(text(), 'Конструктор')]/..")
    private WebElement constructorButton;

    @FindBy(css = "div[class*='AppHeader_header__logo']")
    private WebElement stellarBurgersLogo;

    @FindBy(css = "a.Account_link__2ETsJ")
    private WebElement profileLink;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Выйти'")
    public LoginPage clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Конструктор'")
    public MainPage clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
        return new MainPage(driver);
    }

    @Step("Клик по логотипу Stellar Burgers")
    public MainPage clickStellarBurgersLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(stellarBurgersLogo)).click();
        return new MainPage(driver);
    }

    @Step("Ожидание загрузки страницы профиля")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
    }

    @Step("Проверка нахождения на странице профиля")
    public boolean isProfilePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profileLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}