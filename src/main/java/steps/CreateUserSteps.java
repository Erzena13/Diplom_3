package steps;

import dto.UserCreateRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static endpoints.EndPoints.DELETE_USER;
import static endpoints.EndPoints.REGISTRATION;
import static io.restassured.RestAssured.given;

public class CreateUserSteps {
    @Step
    @Description("Создание пользователя с email '{email}', паролем '{password}' и именем '{name}'")
    public Response register(String email, String password, String name) {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmail(email);
        userCreateRequest.setPassword(password);
        userCreateRequest.setName(name);

        return given()
                .header("Content-Type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site")
                .body(userCreateRequest)
                .when()
                .post(REGISTRATION)
                .then()
                .extract()
                .response();
    }

    @Step
    @Description("Удаление пользователя с accessToken '{accessToken}'")
    public Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .baseUri("https://stellarburgers.nomoreparties.site")
                .when()
                .delete(DELETE_USER)
                .then()
                .extract()
                .response();
    }
}