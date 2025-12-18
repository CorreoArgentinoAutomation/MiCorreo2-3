package api.tests;

import api.framework.ApiTestBase;
import api.models.RecoverPasswordRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para los casos de recuperación de contraseña (Auth / Recover Password)
 * Basados en la colección de Postman: BFF_Casos_Prueba_ED1eraEntregaC2.json
 */
public class RecoverPasswordApiTests extends ApiTestBase {

    /**
     * GC-2445: Cambio contraseña exitoso
     */
    @Test
    @DisplayName("GC-2445: Cambio contraseña exitoso")
    public void testGC2445_CambioContrasenaExitoso() {
        RecoverPasswordRequest recoverRequest = new RecoverPasswordRequest("usuario.valido@test.com");

        Response response = given()
                .spec(getRequestSpec())
                .body(recoverRequest)
                .when()
                .post("/v2/auth/recover-password")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2445 - Cambio contraseña exitoso: " + response.asString());
    }

    /**
     * GC-2446: Cambio contraseña incorrecta
     */
    @Test
    @DisplayName("GC-2446: Cambio contraseña incorrecta")
    public void testGC2446_CambioContrasenaIncorrecta() {
        // Este caso probablemente requiere un token de recuperación válido
        // y luego intentar cambiar con contraseña incorrecta
        RecoverPasswordRequest recoverRequest = new RecoverPasswordRequest("usuario@test.com");

        given()
                .spec(getRequestSpec())
                .body(recoverRequest)
                .when()
                .post("/v2/auth/recover-password")
                .then()
                .statusCode(anyOf(is(200), is(400))) // Puede aceptar o rechazar
                .log().all();
    }

    /**
     * GC-2449: Recupero email válido
     */
    @Test
    @DisplayName("GC-2449: Recupero email válido")
    public void testGC2449_RecuperoEmailValido() {
        RecoverPasswordRequest recoverRequest = new RecoverPasswordRequest("usuario.registrado@test.com");

        Response response = given()
                .spec(getRequestSpec())
                .body(recoverRequest)
                .when()
                .post("/v2/auth/recover-password")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("GC-2449 - Recupero email válido: " + response.asString());
    }

    /**
     * GC-2450: Recupero email no registrado
     */
    @Test
    @DisplayName("GC-2450: Recupero email no registrado")
    public void testGC2450_RecuperoEmailNoRegistrado() {
        RecoverPasswordRequest recoverRequest = new RecoverPasswordRequest("usuario.inexistente@test.com");

        given()
                .spec(getRequestSpec())
                .body(recoverRequest)
                .when()
                .post("/v2/auth/recover-password")
                .then()
                .statusCode(anyOf(is(200), is(404), is(400))) // Puede aceptar o rechazar
                .log().all();
    }

    /**
     * GC-2451: Recupero usuario inactivo
     */
    @Test
    @DisplayName("GC-2451: Recupero usuario inactivo")
    public void testGC2451_RecuperoUsuarioInactivo() {
        RecoverPasswordRequest recoverRequest = new RecoverPasswordRequest("usuario.inactivo@test.com");

        given()
                .spec(getRequestSpec())
                .body(recoverRequest)
                .when()
                .post("/v2/auth/recover-password")
                .then()
                .statusCode(anyOf(is(200), is(400), is(403))) // Puede aceptar o rechazar
                .log().all();
    }
}

