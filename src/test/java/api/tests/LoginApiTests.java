package api.tests;

import api.framework.ApiTestBase;
import api.models.LoginRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para los casos de login (Auth / Login)
 * Basados en la colección de Postman: BFF_Casos_Prueba_ED1eraEntregaC2.json
 */
public class LoginApiTests extends ApiTestBase {

    /**
     * GC-2423: Login exitoso usuario migrado
     */
    @Test
    @DisplayName("GC-2423: Login exitoso usuario migrado")
    public void testGC2423_LoginExitosoUsuarioMigrado() {
        LoginRequest loginRequest = new LoginRequest("usuario.migrado@test.com", "Password123!");

        Response response = given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2423 - Login exitoso usuario migrado: " + response.asString());
    }

    /**
     * GC-2425: Login denegado usuario franquicia
     */
    @Test
    @DisplayName("GC-2425: Login denegado usuario franquicia")
    public void testGC2425_LoginDenegadoUsuarioFranquicia() {
        LoginRequest loginRequest = new LoginRequest("usuario.franquicia@test.com", "Password123!");

        given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(401), is(403), is(400))) // Unauthorized o Forbidden
                .log().all();
    }

    /**
     * GC-2426: Login denegado usuario sucursal
     */
    @Test
    @DisplayName("GC-2426: Login denegado usuario sucursal")
    public void testGC2426_LoginDenegadoUsuarioSucursal() {
        LoginRequest loginRequest = new LoginRequest("usuario.sucursal@test.com", "Password123!");

        given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(401), is(403), is(400))) // Unauthorized o Forbidden
                .log().all();
    }

    /**
     * GC-2427: Login usuario nuevo
     */
    @Test
    @DisplayName("GC-2427: Login usuario nuevo")
    public void testGC2427_LoginUsuarioNuevo() {
        LoginRequest loginRequest = new LoginRequest("usuario.nuevo@test.com", "Password123!");

        Response response = given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(200), is(401))) // Puede ser exitoso o fallar si no está validado
                .extract()
                .response();

        System.out.println("GC-2427 - Login usuario nuevo: " + response.asString());
    }

    /**
     * GC-2428: Login usuario ED
     */
    @Test
    @DisplayName("GC-2428: Login usuario ED")
    public void testGC2428_LoginUsuarioED() {
        LoginRequest loginRequest = new LoginRequest("usuario.ed@test.com", "Password123!");

        Response response = given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2428 - Login usuario ED: " + response.asString());
    }

    /**
     * GC-2430: Login credenciales incorrectas
     */
    @Test
    @DisplayName("GC-2430: Login credenciales incorrectas")
    public void testGC2430_LoginCredencialesIncorrectas() {
        LoginRequest loginRequest = new LoginRequest("usuario@test.com", "PasswordIncorrecto123!");

        given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(401), is(400))) // Unauthorized
                .log().all();
    }

    /**
     * GC-2431: Login sesión activa
     */
    @Test
    @DisplayName("GC-2431: Login sesión activa")
    public void testGC2431_LoginSesionActiva() {
        LoginRequest loginRequest = new LoginRequest("usuario@test.com", "Password123!");

        // Primero hacer login
        given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(200);

        // Intentar login nuevamente (sesión activa)
        Response secondLogin = given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(200), is(400), is(409))) // Puede aceptar o rechazar
                .extract()
                .response();

        System.out.println("GC-2431 - Login con sesión activa: " + secondLogin.asString());
    }

    /**
     * GC-2433: Login faltan parámetros
     */
    @Test
    @DisplayName("GC-2433: Login faltan parámetros")
    public void testGC2433_LoginFaltanParametros() {
        // Request sin email
        LoginRequest loginRequestSinEmail = new LoginRequest();
        loginRequestSinEmail.setPassword("Password123!");

        given()
                .spec(getRequestSpec())
                .body(loginRequestSinEmail)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(400), is(422))) // Bad Request
                .log().all();

        // Request sin password
        LoginRequest loginRequestSinPassword = new LoginRequest();
        loginRequestSinPassword.setEmail("usuario@test.com");

        given()
                .spec(getRequestSpec())
                .body(loginRequestSinPassword)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(anyOf(is(400), is(422))) // Bad Request
                .log().all();
    }
}

