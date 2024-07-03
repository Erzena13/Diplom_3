import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import steps.RegistrationSteps;

public class InvalidPasswordInRegisterTest extends BaseTest {
    @Test
    @Description("Тест наличия ошибки при некорректном пароле")
    public void testInvalidPassword() {
        RegistrationSteps registrationSteps = new RegistrationSteps(driver);

        //создание данных для пользователя
        String name = RandomStringUtils.randomAlphabetic(6);
        String email = RandomStringUtils.randomAlphabetic(10) + "@example.com";
        String invalidPassword = RandomStringUtils.randomAlphabetic(4);

        //регистрация нового пользователя с паролем меньше 6 символов
        registrationSteps.openRegistrationPage();
        registrationSteps.registernWithInvalidPassword(name, email, invalidPassword);

        //проверка ошибки
        String errorMessage = registrationSteps.getErrorMessage();
        Assert.assertTrue("Expected error message not found", errorMessage.contains("Некорректный пароль"));
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            Response response = createUserSteps.deleteUser(accessToken);
            response.then().statusCode(202);
            Assert.assertEquals("User deletion was not successful", 202, response.statusCode());
        }
        DriverFactory.closeDriver();
    }
}
