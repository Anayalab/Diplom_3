package ru.yandex.praktikum.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = "input[name='name']")
    private WebElement emailField;

    @FindBy(css = "input[name='Пароль']")
    private WebElement passwordField;

    @FindBy(css = "button.button_button__33qZ0")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(), 'Зарегистрироваться')]")
    private WebElement registerLink;

    @FindBy(xpath = "//a[contains(text(), 'Восстановить пароль')]")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод email: {email}")
    public LoginPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public MainPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new MainPage(driver);
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public RegisterPage clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new RegisterPage(driver);
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public ForgotPasswordPage clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Вход в систему с данными: {email}")
    public MainPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickLoginButton();
    }

    @Step("Ожидание загрузки страницы входа")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
    }
}