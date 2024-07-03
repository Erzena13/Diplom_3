package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PersonalAccountPage;

import java.time.Duration;

public class PersonalAccountSteps {
    private WebDriver driver;
    private PersonalAccountPage personalAccountPage;
    private WebDriverWait wait;

    public PersonalAccountSteps(WebDriver driver) {
        this.driver = driver;
        this.personalAccountPage = new PersonalAccountPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step("Проверяем наличие текста «Профиль» на странице Личный кабинет")
    public boolean isTextProfileDisplayed() {
        try {
            WebElement element =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.textProfileLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Проверяем наличие текста «История заказов» на странице Личный кабинет")
    public boolean isTextOrdersHistoryDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.textOrdersHistoryLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Нажатие на кнопку «Выход»")
    public void clickExitButton() {
        driver.findElement(personalAccountPage.exitButtonLocator).click();
    }
}