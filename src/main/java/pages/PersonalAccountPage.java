package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private WebDriver driver;
    public final By textProfileLocator = By.xpath("//a[contains(@class, 'Account_link_active')" +
            "and contains(@href, '/account/profile')]");
    public final By textOrdersHistoryLocator =
            By.xpath("//a[contains(@class, 'Account_link__2ETsJ')" +
                    "and contains(@href, '/account/order-history') and text()='История заказов']");

    public final By exitButtonLocator = By.xpath("//button[contains(@class, 'Account_button__14Yp3')" +
            "and text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }
}