package api.tests;

import api.framework.ApiTestBase;
import api.models.UserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para los endpoints de usuarios
 * - POST /v2/users/register
 * - POST /v2/users/register/resend-email
 * - PUT /v2/users/validate
 * - GET /v2/users/exists
 * - GET /v2/users/email
 * - GET /v2/user/document-types
 */
public class UsersApiTests extends ApiTestBase {

    @Test
    @DisplayName("GET /v2/user/document-types - Obtener tipos de documento")
    public void testGetDocumentTypes() {
        Response response = given()
                .spec(getRequestSpec())
                .when()
                .get("/v2/user/document-types")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Tipos de documento: " + response.asString());
    }

    @Test
    @DisplayName("GET /v2/users/exists - Verificar si usuario existe")
    public void testUserExists() {
        // Este endpoint probablemente requiere parámetros de query
        Response response = given()
                .spec(getRequestSpec())
                .queryParam("email", "test@example.com")
                .when()
                .get("/v2/users/exists")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Usuario existe: " + response.asString());
    }

    @Test
    @DisplayName("GET /v2/users/email - Obtener usuario por email")
    public void testGetUserByEmail() {
        Response response = given()
                .spec(getRequestSpec())
                .queryParam("email", "apitestjava@yopmail.com")
                .when()
                .get("/v2/users/email")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Usuario: " + response.asString());
    }

    @Test
    @DisplayName("POST /v2/users/register - Registrar nuevo usuario")
    public void testRegisterUser() {
        UserRequest userRequest = createTestUserRequest();

        Response response = given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .spec(getResponseSpec())
                .statusCode(anyOf(is(200), is(201)))
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Usuario registrado: " + response.asString());
    }

    @Test
    @DisplayName("POST /v2/users/register/resend-email - Reenviar email de registro")
    public void testResendRegisterEmail() {
        String email = "test@example.com";

        Response response = given()
                .spec(getRequestSpec())
                .body("{\"email\": \"" + email + "\"}")
                .when()
                .post("/v2/users/register/resend-email")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Email reenviado: " + response.asString());
    }

    @Test
    @DisplayName("PUT /v2/users/validate - Validar usuario")
    public void testValidateUser() {
        String notificationToken = "test-token-123";

        Response response = given()
                .spec(getRequestSpec())
                .body("{\"notificationToken\": \"" + notificationToken + "\"}")
                .when()
                .put("/v2/users/validate")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Usuario validado: " + response.asString());
    }

    /**
     * Crea un objeto UserRequest de prueba
     */
    private UserRequest createTestUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Federación");
        userRequest.setLastname("Patronal");
        userRequest.setEmail("edmcregistroseisko@yopmail.com");
        userRequest.setPassword("Federacion123");
        userRequest.setPhone("541151327544");

        UserRequest.Document document = new UserRequest.Document();
        document.setType("DNI"); // Cambiado a String
        document.setId("368774588");
        userRequest.setDocument(document);

        userRequest.setProvince("CAPITAL FEDERAL");
        userRequest.setCity("CIUDAD AUTONOMA BUENOS AIRES");
        userRequest.setAddress("Juncal 2200");
        userRequest.setPostalCode("1116");
        userRequest.setCategory(2);
        userRequest.setTermsAndConditions(true);
        userRequest.setTaxCondition(1);
        userRequest.setCompanyName("company");
        userRequest.setCuit("30708902507");

        return userRequest;
    }
}

