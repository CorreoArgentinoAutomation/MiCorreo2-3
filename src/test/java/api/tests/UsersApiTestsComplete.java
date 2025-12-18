package api.tests;

import api.framework.ApiTestBase;
import api.models.UserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests completos para los casos de usuarios (Users)
 * Basados en la colección de Postman: BFF_Casos_Prueba_ED1eraEntregaC2.json
 */
public class UsersApiTestsComplete extends ApiTestBase {

    /**
     * GC-2438: Alta usuario duplicado
     */
    @Test
    @DisplayName("GC-2438: Alta usuario duplicado")
    public void testGC2438_AltaUsuarioDuplicado() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setEmail("usuario.duplicado@test.com");
        userRequest.getDocument().setId("12345678");

        // Primero registrar
        given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(200), is(201)));

        // Intentar registrar nuevamente
        given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(400), is(409), is(422))) // Bad Request o Conflict
                .log().all();
    }

    /**
     * GC-2439: Alta usuario datos faltantes
     */
    @Test
    @DisplayName("GC-2439: Alta usuario datos faltantes")
    public void testGC2439_AltaUsuarioDatosFaltantes() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan");
        // Faltan otros campos obligatorios

        given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(400), is(422))) // Bad Request
                .log().all();
    }

    /**
     * GC-2440: Validación usuarios token válido
     */
    @Test
    @DisplayName("GC-2440: Validación usuarios token válido")
    public void testGC2440_ValidacionUsuariosTokenValido() {
        // Primero hacer login para obtener token
        api.models.LoginRequest loginRequest = new api.models.LoginRequest("usuario@test.com", "Password123!");
        
        Response loginResponse = given()
                .spec(getRequestSpec())
                .body(loginRequest)
                .when()
                .post("/v2/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Extraer token (ajustar según la estructura de respuesta)
        String token = loginResponse.jsonPath().getString("token");
        if (token == null) {
            token = loginResponse.jsonPath().getString("accessToken");
        }

        // Validar usuario con token
        if (token != null) {
            Response response = given()
                    .spec(getRequestSpec())
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("/v2/users")
                    .then()
                    .statusCode(200)
                    .body(notNullValue())
                    .extract()
                    .response();

            System.out.println("GC-2440 - Validación con token válido: " + response.asString());
        }
    }

    /**
     * GC-2441: Validación token inválido
     */
    @Test
    @DisplayName("GC-2441: Validación token inválido")
    public void testGC2441_ValidacionTokenInvalido() {
        String tokenInvalido = "token.invalido.12345";

        given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + tokenInvalido)
                .when()
                .get("/v2/users")
                .then()
                .statusCode(anyOf(is(401), is(403))) // Unauthorized o Forbidden
                .log().all();
    }

    /**
     * GC-2443: Registro código postal inválido
     */
    @Test
    @DisplayName("GC-2443: Registro código postal inválido")
    public void testGC2443_RegistroCodigoPostalInvalido() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setPostalCode("00000"); // Código postal inválido

        given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(400), is(422))) // Bad Request
                .log().all();
    }

    /**
     * GC-2444: Validación DNI EMAIL duplicados
     */
    @Test
    @DisplayName("GC-2444: Validación DNI EMAIL duplicados")
    public void testGC2444_ValidacionDNIEmailDuplicados() {
        // Verificar si usuario existe con DNI y EMAIL duplicados
        String email = "usuario.duplicado@test.com";
        String dni = "12345678";

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("email", email)
                .queryParam("documentNumber", dni)
                .when()
                .get("/v2/users/exists")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2444 - Validación duplicados: " + response.asString());
    }

    /**
     * GC-2447: Reenvío mail registro
     */
    @Test
    @DisplayName("GC-2447: Reenvío mail registro")
    public void testGC2447_ReenvioMailRegistro() {
        String email = "usuario.registrado@test.com";

        Response response = given()
                .spec(getRequestSpec())
                .body("{\"email\": \"" + email + "\"}")
                .when()
                .post("/v2/users/register/resend-email")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2447 - Reenvío mail registro: " + response.asString());
    }

    /**
     * GC-2448: Reenvío mail user inexistente
     */
    @Test
    @DisplayName("GC-2448: Reenvío mail user inexistente")
    public void testGC2448_ReenvioMailUserInexistente() {
        String email = "usuario.inexistente@test.com";

        given()
                .spec(getRequestSpec())
                .body("{\"email\": \"" + email + "\"}")
                .when()
                .post("/v2/users/register/resend-email")
                .then()
                .statusCode(anyOf(is(200), is(404), is(400))) // Puede aceptar o rechazar
                .log().all();
    }

    // ========== Métodos auxiliares ==========

    private UserRequest createPersonaFisicaRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan");
        userRequest.setLastname("Perez");
        userRequest.setEmail("juan.perez+" + System.currentTimeMillis() + "@test.com");
        userRequest.setPassword("Password123!");
        
        UserRequest.Document document = new UserRequest.Document();
        document.setType("DNI");
        document.setId("30123456");
        userRequest.setDocument(document);
        
        userRequest.setCuit("20301234567");
        userRequest.setProvince("Buenos Aires");
        userRequest.setCity("Mar del Plata");
        userRequest.setAddress("Calle Falsa 123");
        userRequest.setPostalCode("7600");
        userRequest.setTermsAndConditions(true);
        
        return userRequest;
    }
}

