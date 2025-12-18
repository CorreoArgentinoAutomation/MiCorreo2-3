package api.tests;

import api.framework.ApiTestBase;
import api.models.UserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para los casos de registro de usuarios (Auth / Register)
 * Basados en la colección de Postman: BFF_Casos_Prueba_ED1eraEntregaC2.json
 */
public class RegisterApiTests extends ApiTestBase {

    /**
     * GC-2411: Registro exitoso de persona física
     */
    @Test
    @DisplayName("GC-2411: Registro exitoso de persona física")
    public void testGC2411_RegistroExitosoPersonaFisica() {
        UserRequest userRequest = createPersonaFisicaRequest();

        Response response = given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2411 - Registro exitoso: " + response.asString());
    }

    /**
     * GC-2412: Registro exitoso de Empresa
     */
    @Test
    @DisplayName("GC-2412: Registro exitoso de Empresa")
    public void testGC2412_RegistroExitosoEmpresa() {
        UserRequest userRequest = createEmpresaRequest();

        Response response = given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2412 - Registro empresa exitoso: " + response.asString());
    }

    /**
     * GC-2413: Registro con DNI ya registrado
     */
    @Test
    @DisplayName("GC-2413: Registro con DNI ya registrado")
    public void testGC2413_RegistroDNIYaRegistrado() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.getDocument().setId("30123456"); // DNI que ya existe

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
     * GC-2414: Registro con EMAIL ya registrado
     */
    @Test
    @DisplayName("GC-2414: Registro con EMAIL ya registrado")
    public void testGC2414_RegistroEmailYaRegistrado() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setEmail("usuario.existente@test.com"); // Email que ya existe

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
     * GC-2415: Registro con CUIT ya registrado
     */
    @Test
    @DisplayName("GC-2415: Registro con CUIT ya registrado")
    public void testGC2415_RegistroCUITYaRegistrado() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setCuit("20301234567"); // CUIT que ya existe

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
     * GC-2416: Registro con CUIT inválido
     */
    @Test
    @DisplayName("GC-2416: Registro con CUIT inválido")
    public void testGC2416_RegistroCUITInvalido() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setCuit("12345678901"); // CUIT inválido

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
     * GC-2417: Registro sin aceptar T&C
     */
    @Test
    @DisplayName("GC-2417: Registro sin aceptar T&C")
    public void testGC2417_RegistroSinAceptarTC() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setTermsAndConditions(false); // No acepta términos

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
     * GC-2418: Registro con campos obligatorios vacíos
     */
    @Test
    @DisplayName("GC-2418: Registro con campos obligatorios vacíos")
    public void testGC2418_RegistroCamposObligatoriosVacios() {
        UserRequest userRequest = new UserRequest();
        // Campos vacíos o null

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
     * GC-2419: Registro con contraseña vacía
     */
    @Test
    @DisplayName("GC-2419: Registro con contraseña vacía")
    public void testGC2419_RegistroContrasenaVacia() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setPassword(""); // Contraseña vacía

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
     * GC-2421: Registro con Rubro exitoso
     */
    @Test
    @DisplayName("GC-2421: Registro con Rubro exitoso")
    public void testGC2421_RegistroConRubroExitoso() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setCategory(2); // Rubro específico

        Response response = given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2421 - Registro con rubro: " + response.asString());
    }

    /**
     * GC-2422: Registro con Rubro vacío
     */
    @Test
    @DisplayName("GC-2422: Registro con Rubro vacío")
    public void testGC2422_RegistroConRubroVacio() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setCategory(null); // Rubro vacío

        given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post("/v2/users/register")
                .then()
                .statusCode(anyOf(is(200), is(201), is(400), is(422))) // Puede aceptar o rechazar
                .log().all();
    }

    // ========== Métodos auxiliares ==========

    /**
     * Crea un request de persona física con datos válidos
     */
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

    /**
     * Crea un request de empresa con datos válidos
     */
    private UserRequest createEmpresaRequest() {
        UserRequest userRequest = createPersonaFisicaRequest();
        userRequest.setCompanyName("Empresa Test S.A.");
        userRequest.setTaxCondition(1);
        return userRequest;
    }
}

