package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public final By loginButtonInForgotPasswordLocator =
            By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/login']");
    public final By passwordRecoveryLocator = By.xpath("//h2[text()='Восстановление пароля']");

    //конструктор для инициализации WebDriver
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}