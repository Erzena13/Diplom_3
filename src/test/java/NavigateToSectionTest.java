import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import steps.MainPageSteps;

public class NavigateToSectionTest extends BaseTest {
    @Test
    @Description("Тест на переход к разделу «Булки»")
    public void navigationToSectionBurgers() {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        MainPage mainPage = new MainPage(driver);

        //открытие главной страницы
        mainPage.openMainPage();

        //переход на другую вкладку, тк при открытии главной страницы всегда активна вкладка «Булки»
        mainPageSteps.clickSectionSauces();

        //переход на вкладку «Булки»
        mainPageSteps.clickSectionBurgers();

        //проверка что открыта вкладка «Булки»
        WebElement element =
                driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]"));
        Assert.assertEquals("Булки", element.getText());
    }

    @Test
    @Description("Тест на переход к разделу «Соусы»")
    public void navigationToSectionSauces() {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        MainPage mainPage = new MainPage(driver);

        //открытие главной страницы
        mainPage.openMainPage();

        //переход на вкладку «Соусы»
        mainPageSteps.clickSectionSauces();

        //проверка что открыта вкладка «Соусы»
        WebElement element =
                driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]"));
        Assert.assertEquals("Соусы", element.getText());
    }

    @Test
    @Description("Тест на переход к разделу «Начинки»")
    public void navigationToSectionFillings() {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        MainPage mainPage = new MainPage(driver);

        //открытие главной страницы
        mainPage.openMainPage();

        //переход на вкладку «Начинки»
        mainPageSteps.clickSectionFillings();

        //проверка что открыта вкладка «Начинки»
        WebElement element =
                driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]"));
        Assert.assertEquals("Начинки", element.getText());
    }

    @After
    public void tearDown() {
        DriverFactory.closeDriver();
    }
}