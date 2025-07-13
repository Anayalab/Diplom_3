package ru.yandex.praktikum.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Войти')]")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по ссылке 'Войти'")
    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(loginLink));
    }
}