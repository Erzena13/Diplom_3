import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import steps.LoginSteps;
import steps.MainPageSteps;
import steps.PersonalAccountSteps;
import steps.RegistrationSteps;

public class PersonalAccountTests extends BaseTest {
    @Test
    @Description("Переход по клику «Личный кабинет» в Личный кабинет")
    public void testNavigateToPersonalAccount() {
        RegistrationSteps registrationSteps = new RegistrationSteps(driver);
        LoginSteps loginSteps = new LoginSteps(driver);
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        PersonalAccountSteps personalAccountSteps = new PersonalAccountSteps(driver);

        //создание данных для пользователя
        String name = RandomStringUtils.randomAlphabetic(6);
        String email = RandomStringUtils.randomAlphabetic(10) + "@example.com";
        String password = RandomStringUtils.randomAlphabetic(6);

        //регистрация нового пользователя через API
        Response registrationResponse = createUserSteps.register(email, password, name);

        //проверка успешной регистрации
        registrationResponse.then()
                .statusCode(200)
                .body("success", Matchers.is(true))
                .body("accessToken", Matchers.notNullValue());

        //сохранение accessToken для последующего удаления пользователя
        accessToken = registrationResponse.path("accessToken");

        //авторизация пользователем по кнопке «Войти в аккаунт» на главной странице
        loginSteps.loginFromMainPage(email, password);

        //проверка успешного входа
        Assert.assertTrue("Login was not successful", registrationSteps.isButtonCreateOrderDisplayed());

        //переход в личный кабинет
        mainPageSteps.clickPersonalAccountButton();
        personalAccountSteps.isTextProfileDisplayed();
        personalAccountSteps.isTextOrdersHistoryDisplayed();
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
