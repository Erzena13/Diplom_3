package steps;

import io.qameta.allure.Step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;
    private MainPage mainPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.mainPage = new MainPage(driver);
    }

    @Step("Открытие главной страницы")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Открытие страницы авторизации")
    public void openAuthPage() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Step("Авторизация с email: {email} и password: {password}")
    public void login(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Авторизация по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPage(String email, String password) {
        MainPage mainPage = new MainPage(driver);
        openMainPage();
        mainPage.clickLoginButton();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Авторизация через кнопку «Личный кабинет»")
    public void loginFromPersonalCabinet(String email, String password) {
        openMainPage();

        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Авторизация через кнопку в форме регистрации")
    public void loginFromRegistrationPage(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Нажатие кнопки «Восстановить пароль»")
    public void clickForgotPasswordInLoginPage() {
        driver.findElement(loginPage.forgotPasswordPageLoginButtonLocator).click();
    }

    @Step("Проверка наличие кнопки «Вход» на странице авторизации")
    public boolean isTextAuthDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.textAuthLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}