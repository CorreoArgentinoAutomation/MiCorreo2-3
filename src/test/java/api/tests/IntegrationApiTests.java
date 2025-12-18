package api.tests;

import api.framework.ApiTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para los endpoints de integración
 * - GET /v2/integration/provinces
 * - GET /v2/integration/cities/:provinceId
 * - GET /v2/integration/provinces/validate-postal-code
 * - GET /v2/integration/validate-cuit
 * - GET /v2/accounts/categories
 */
public class IntegrationApiTests extends ApiTestBase {

    @Test
    @DisplayName("GET /v2/integration/provinces - Obtener provincias")
    public void testGetProvinces() {
        Response response = given()
                .spec(getRequestSpec())
                .when()
                .get("/v2/integration/provinces")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Provincias: " + response.asString());
    }

    @Test
    @DisplayName("GET /v2/integration/cities/:provinceId - Obtener ciudades por provincia")
    public void testGetCitiesByProvince() {
        String provinceId = "1"; // ID de ejemplo, ajustar según la API real

        Response response = given()
                .spec(getRequestSpec())
                .pathParam("provinceId", provinceId)
                .when()
                .get("/v2/integration/cities/{provinceId}")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Ciudades: " + response.asString());
    }

    @Test
    @DisplayName("GET /v2/integration/provinces/validate-postal-code - Validar código postal")
    public void testValidatePostalCode() {
        String postalCode = "1020";

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("postalCode", postalCode)
                .when()
                .get("/v2/integration/provinces/validate-postal-code")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Validación código postal: " + response.asString());
    }

    @Test
    @DisplayName("GET /v2/integration/validate-cuit - Validar CUIT")
    public void testValidateCuit() {
        String cuit = "30708902507";

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("cuit", cuit)
                .when()
                .get("/v2/integration/validate-cuit")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Validación CUIT: " + response.asString());
    }

    @Test
    @DisplayName("GET /v2/accounts/categories - Obtener categorías de cuenta")
    public void testGetAccountCategories() {
        Response response = given()
                .spec(getRequestSpec())
                .when()
                .get("/v2/accounts/categories")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Categorías: " + response.asString());
    }
}

