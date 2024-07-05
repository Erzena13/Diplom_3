import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import steps.ForgotPasswordSteps;
import steps.LoginSteps;
import steps.RegistrationSteps;


public class LoginTest extends BaseTest {
    @Test
    @Description("Тест входа по кнопке «Войти в аккаунт» на главной странице")
    public void testLoginFromMainPage() {
        LoginSteps loginSteps = new LoginSteps(driver);
        RegistrationSteps registrationSteps = new RegistrationSteps(driver);

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
    }

    @Test
    @Description("Тест входа через кнопку «Личный кабинет»")
    public void testLoginFromPersonalCabinet() {
        LoginSteps loginSteps = new LoginSteps(driver);
        RegistrationSteps registrationSteps = new RegistrationSteps(driver);

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

        //авторизация пользователем по кнопке «Войти» на странице «Личный кабинет»
        loginSteps.loginFromPersonalCabinet(email, password);

        //проверка успешного входа
        Assert.assertTrue("Login was not successful", registrationSteps.isButtonCreateOrderDisplayed());
    }

    @Test
    @Description("Тест входа через кнопку «Войти» в форме регистрации")
    public void testLoginFromRegistrationPage() {
        LoginSteps loginSteps = new LoginSteps(driver);
        RegistrationSteps registrationSteps = new RegistrationSteps(driver);

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

        //авторизация пользователем по кнопке «Войти» в форме регистрации
        registrationSteps.openRegistrationPage();
        registrationSteps.isTextRegisterDisplayed();

        registrationSteps.clickAuthButton();
        Assert.assertTrue("Text «Вход» is not displayed", loginSteps.isTextAuthDisplayed());
        loginSteps.loginFromRegistrationPage(email, password);

        //проверка успешного входа
        Assert.assertTrue("Login was not successful", registrationSteps.isButtonCreateOrderDisplayed());
    }

    @Test
    @Description("Тест входа через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordPage() {
        LoginSteps loginSteps = new LoginSteps(driver);
        RegistrationSteps registrationSteps = new RegistrationSteps(driver);
        ForgotPasswordSteps forgotPasswordSteps = new ForgotPasswordSteps(driver);

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

        //авторизация пользователем через кнопку в форме восстановления пароля
        loginSteps.openAuthPage();
        loginSteps.clickForgotPasswordInLoginPage();

        forgotPasswordSteps.isTextPasswordRecoverDisplayed();
        forgotPasswordSteps.clickButtonInForgotPasswordForm();

        loginSteps.loginFromRegistrationPage(email, password);

        //проверка успешного входа
        Assert.assertTrue("Login was not successful", registrationSteps.isButtonCreateOrderDisplayed());
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
