package api.tests;

import api.framework.ApiTestBase;
import api.models.LoginRequest;
import api.models.RecoverPasswordRequest;
import api.models.UpdatePasswordRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para los endpoints de autenticación
 * - POST /v2/auth/login
 * - POST /v2/auth/logout
 * - POST /v2/auth/recover-password
 * - PATCH /v2/auth/profile/password
 */
public class AuthApiTests extends ApiTestBase {

    @Test
    @DisplayName("POST /v2/auth/login - Login exitoso")
    public void testLoginExitoso() {
        LoginRequest loginRequest = new LoginRequest("apitestjava@yopmail.com", "123123");

        Response response = given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Response: " + response.asString());
    }

    @Test
    @DisplayName("POST /v2/auth/login - Login con credenciales inválidas")
    public void testLoginCredencialesInvalidas() {
        LoginRequest loginRequest = new LoginRequest("invalid@example.com", "wrongpassword");

        given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(401), is(400), is(404)))
                .log().all();
    }

    @Test
    @DisplayName("POST /v2/auth/logout - Logout exitoso")
    public void testLogout() {
        given()
                .spec(getRequestSpec())
                .when()
                .post("/v2/auth/logout")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .log().all();
    }

    @Test
    @DisplayName("POST /v2/auth/recover-password - Recuperación de contraseña")
    public void testRecoverPassword() {
        RecoverPasswordRequest recoverRequest = new RecoverPasswordRequest("apitestjava@yopmail.com");

        Response response = given()
                .spec(getRequestSpec())
                .body(recoverRequest)
                .when()
                .post("/v2/auth/recover-password")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Response: " + response.asString());
    }

    @Test
    @DisplayName("PATCH /v2/auth/profile/password - Actualizar contraseña")
    public void testUpdatePassword() {
        UpdatePasswordRequest updateRequest = new UpdatePasswordRequest("apitestjava@yopmail.com", "12341234");

        given()
                .spec(getRequestSpec())
                .body(updateRequest)
                .when()
                .patch("/v2/auth/profile/password")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .log().all();
    }
}

