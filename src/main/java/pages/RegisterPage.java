package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    //локаторы элементов
    private final By usernameInputLocator = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailInputLocator = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInputLocator = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButtonLocator = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorMessageLocator = By.xpath(".//p[@class='input__error text_type_main-default']");
    public final By buttonCreateOrderLocator = By.xpath("//button[text()='Оформить заказ']");
    public final By textRegisterLocator = By.xpath("//h2[text()='Регистрация']");
    public final By registrationPageLoginButtonLocator = By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInputLocator).sendKeys(username);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInputLocator).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }
}