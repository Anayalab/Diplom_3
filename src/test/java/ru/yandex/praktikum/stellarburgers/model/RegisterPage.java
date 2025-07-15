package ru.yandex.praktikum.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "(//input[@name='name'])[1]")
    private WebElement nameField;

    @FindBy(xpath = "(//input[@name='name'])[2]")
    private WebElement emailField;

    @FindBy(css = "input[name='Пароль']")
    private WebElement passwordField;

    @FindBy(css = "button.button_button__33qZ0")
    private WebElement registerButton;

    @FindBy(xpath = "//a[contains(text(), 'Войти')]")
    private WebElement loginLink;

    @FindBy(xpath = "//p[contains(text(), 'Некорректный пароль')]")
    private WebElement passwordErrorMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени: {name}")
    public RegisterPage enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).clear();
        nameField.sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegisterPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public LoginPage clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        return new LoginPage(driver);
    }

    @Step("Клик по ссылке 'Войти'")
    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }

    @Step("Регистрация пользователя: {name}, {email}")
    public LoginPage register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        return clickRegisterButton();
    }

    @Step("Проверка наличия ошибки валидации пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
            return passwordErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(nameField));
    }
}