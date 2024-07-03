package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInputLocator =
            By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginButtonLocator = By.xpath(".//button[text()='Войти']");
    private final By personalCabinetButtonLocator = By.xpath("//a[@href='/account']");
    public final By forgotPasswordPageLoginButtonLocator = By.xpath("//a[text()='Восстановить пароль']");
    public final By textAuthLocator = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButtonLocator).click();
    }
}