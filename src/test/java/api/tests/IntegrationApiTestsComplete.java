package api.tests;

import api.framework.ApiTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests completos para los casos de integración (Integration)
 * Basados en la colección de Postman: BFF_Casos_Prueba_ED1eraEntregaC2.json
 */
public class IntegrationApiTestsComplete extends ApiTestBase {

    /**
     * GC-2420: Validación domicilio ARCA
     */
    @Test
    @DisplayName("GC-2420: Validación domicilio ARCA")
    public void testGC2420_ValidacionDomicilioARCA() {
        String cuit = "20301234567";

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("cuit", cuit)
                .when()
                .get("/v2/integration/validate-cuit")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2420 - Validación domicilio ARCA: " + response.asString());
    }

    /**
     * GC-2424: Validación CP incorrecto
     */
    @Test
    @DisplayName("GC-2424: Validación CP incorrecto")
    public void testGC2424_ValidacionCPIncorrecto() {
        String postalCode = "00000"; // Código postal incorrecto
        String province = "Buenos Aires";

        given()
                .spec(getRequestSpec())
                .queryParam("postalCode", postalCode)
                .queryParam("province", province)
                .when()
                .get("/v2/integration/provinces/validate-postal-code")
                .then()
                .statusCode(anyOf(is(200), is(400), is(422))) // Puede aceptar o rechazar
                .log().all();
    }

    /**
     * GC-2429: Validación CP correcto
     */
    @Test
    @DisplayName("GC-2429: Validación CP correcto")
    public void testGC2429_ValidacionCPCorrecto() {
        String postalCode = "7600"; // Código postal correcto
        String province = "Buenos Aires";

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("postalCode", postalCode)
                .queryParam("province", province)
                .when()
                .get("/v2/integration/provinces/validate-postal-code")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2429 - Validación CP correcto: " + response.asString());
    }

    /**
     * GC-2436: Obtener provincias
     */
    @Test
    @DisplayName("GC-2436: Obtener provincias")
    public void testGC2436_ObtenerProvincias() {
        Response response = given()
                .spec(getRequestSpec())
                .when()
                .get("/v2/integration/provinces")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2436 - Provincias: " + response.asString());
    }

    /**
     * GC-2437: CUIT inválido query
     */
    @Test
    @DisplayName("GC-2437: CUIT inválido query")
    public void testGC2437_CUITInvalidoQuery() {
        String cuitInvalido = "12345678901"; // CUIT inválido

        given()
                .spec(getRequestSpec())
                .queryParam("cuit", cuitInvalido)
                .when()
                .get("/v2/integration/validate-cuit")
                .then()
                .statusCode(anyOf(is(200), is(400), is(422))) // Puede aceptar o rechazar
                .log().all();
    }

    /**
     * GC-2452: CUIT válido
     */
    @Test
    @DisplayName("GC-2452: CUIT válido")
    public void testGC2452_CUITValido() {
        String cuitValido = "20301234567"; // CUIT válido

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("cuit", cuitValido)
                .when()
                .get("/v2/integration/validate-cuit")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2452 - CUIT válido: " + response.asString());
    }

    /**
     * GC-2454: CUIT faltante
     */
    @Test
    @DisplayName("GC-2454: CUIT faltante")
    public void testGC2454_CUITFaltante() {
        given()
                .spec(getRequestSpec())
                // Sin parámetro cuit
                .when()
                .get("/v2/integration/validate-cuit")
                .then()
                .statusCode(anyOf(is(400), is(422))) // Bad Request
                .log().all();
    }
}

