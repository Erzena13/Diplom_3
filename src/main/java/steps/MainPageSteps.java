package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.time.Duration;

public class MainPageSteps {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    public MainPageSteps(WebDriver driver) {
        this.driver = driver;
        this.mainPage = new MainPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие кнопки «Личный Кабинет»")
    public void clickPersonalAccountButton() {
        driver.findElement(mainPage.personalAccountButtonLocator).click();
    }

    @Step("Проверка отображения кнопки «Войти в аккаунт» на главной странице")
    public boolean isLoginButtonDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.loginButtonLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка отображения заголовка «Соберите бургер» на главной странице")
    public boolean isAssembleBurgerTitleDisplayed() {
        try {
            WebElement element =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.assembleBurgerTitleLocator));
            return element.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Клик на лого")
    public void clickLogo() {
        driver.findElement(mainPage.logoLocator).click();
    }

    @Step("Нажатие на кнопку «Конструктор»")
    public void clickConstructorButton() {
        driver.findElement(mainPage.constructorButtonLocator).click();
    }

    @Step("Клик на раздел «Булки»")
    public void clickSectionBurgers() {
        mainPage.clickSectionBurgers();
    }

    @Step("Клик на раздел «Соусы»")
    public void clickSectionSauces() {
        mainPage.clickSectionSauces();
    }

    @Step("Клик на раздел «Начинки»")
    public void clickSectionFillings() {
        mainPage.clickSectionFillings();
    }
}