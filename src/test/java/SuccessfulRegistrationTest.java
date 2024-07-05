import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import steps.LoginSteps;
import steps.RegistrationSteps;

public class SuccessfulRegistrationTest extends BaseTest {

    @Test
    @Description("Тест успешной регистрации")
    public void testSuccessfulRegistration() {
        RegistrationSteps registrationSteps = new RegistrationSteps(driver);
        LoginSteps loginSteps = new LoginSteps(driver);

        //создание данных для пользователя
        String name = RandomStringUtils.randomAlphabetic(6);
        String email = RandomStringUtils.randomAlphabetic(10) + "@example.com";
        String password = RandomStringUtils.randomAlphabetic(6);

        //регистрация нового пользователя
        registrationSteps.openRegistrationPage();
        registrationSteps.registerNewUser(name, email, password);

        //авторизация пользователем
        loginSteps.openAuthPage();
        loginSteps.login(email, password);

        //проверка успешной авторизации созданым пользователем
        Assert.assertTrue("Login was not successful", registrationSteps.isButtonCreateOrderDisplayed());
    }

    @After
    public void tearDown() {
        DriverFactory.closeDriver();
    }
}
