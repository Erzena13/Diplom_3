package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public final By personalAccountButtonLocator =
            By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va')" +
                    "and contains(@class, 'ml-2') and text()='Личный Кабинет']\n");
    public final By loginButtonLocator = By.xpath("//button[text()='Войти в аккаунт']");
    public final By logoLocator =
            By.xpath("//*[@id=\"root\"]/div/header/nav/div");
    public final By constructorButtonLocator =
            By.cssSelector("#root > div > header > nav > div > a > svg");
    public final By assembleBurgerTitleLocator = By.xpath("//h1[text()='Соберите бургер']");
    public final By sectionBurgersLocator = By.xpath("//div[.='Булки']");
    public final By sectionSaucesLocator = By.xpath("//div[.='Соусы']");
    public final By sectionFillingsLocator = By.xpath("//div[.='Начинки']");

    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginButton.click();
    }

    public void clickSectionBurgers() {
        WebElement burgersSection = driver.findElement(sectionBurgersLocator);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        burgersSection.click();
    }

    public void clickSectionSauces() {
        WebElement saucesSection = driver.findElement(sectionSaucesLocator);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        saucesSection.click();
    }

    public void clickSectionFillings() {
        WebElement fillingsSection = driver.findElement(sectionFillingsLocator);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fillingsSection.click();
    }
}