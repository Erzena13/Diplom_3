package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPage;

import java.time.Duration;

public class RegistrationSteps {
    private WebDriver driver;
    private RegisterPage registerPage;
    private WebDriverWait wait;

    public RegistrationSteps(WebDriver driver) {
        this.driver = driver;
        this.registerPage = new RegisterPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открытие страницы регистрации")
    public void openRegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Регистрация нового юзера с username: {username}, email: {email}, password: {password}")
    public void registerNewUser(String username, String email, String password) {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterUsername(username);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
    }

    @Step("Регистрация с неверным password: {password}")
    public void registernWithInvalidPassword(String username, String email, String password) {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterUsername(username);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
    }

    @Step("Получение ошибки для некорректного пароля")
    public String getErrorMessage() {
        return registerPage.getErrorMessage();
    }

    @Step("Проверка успешной регистрации, после авторизации")
    //проверяем наличие кнопки " Оформить заказ"
    public boolean isButtonCreateOrderDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.buttonCreateOrderLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка что зашли на страницу Регистрация")
    public boolean isTextRegisterDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.textRegisterLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Нажатие на кнопку «Войти» на странице регистрации")
    public void clickAuthButton() {
        driver.findElement(registerPage.registrationPageLoginButtonLocator).click();
    }
}