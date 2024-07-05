package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ForgotPasswordPage;

import java.time.Duration;

public class ForgotPasswordSteps {
    private WebDriver driver;
    private ForgotPasswordPage forgotPasswordPage;
    private WebDriverWait wait;

    public ForgotPasswordSteps(WebDriver driver) {
        this.driver = driver;
        this.forgotPasswordPage = new ForgotPasswordPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step("Открытие страницы восстановления пароля")
    public void openForgotPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Проверка наличие текста «Восстановление пароля» на странице восстановления пароля")
    public boolean isTextPasswordRecoverDisplayed() {
        try {
            WebElement element =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordPage.passwordRecoveryLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Нажатие кнопки «Войти» на старнице восстановления пароля")
    public void clickButtonInForgotPasswordForm() {
        WebElement loginButton = driver.findElement(forgotPasswordPage.loginButtonInForgotPasswordLocator);
        loginButton.click();
    }
}