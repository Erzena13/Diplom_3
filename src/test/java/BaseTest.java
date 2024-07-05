import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import steps.CreateUserSteps;

public class BaseTest {
    protected static WebDriver driver;
    protected static String accessToken;
    protected static CreateUserSteps createUserSteps;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        createUserSteps = new CreateUserSteps();
    }
}