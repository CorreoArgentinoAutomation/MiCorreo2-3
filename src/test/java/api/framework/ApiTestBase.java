package api.framework;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase base para todos los tests de API
 * Configura RestAssured con la URL base y especificaciones comunes
 */
public class ApiTestBase {

    protected static String apiBaseUrl;
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        // Cargar configuración desde config.properties
        loadConfig();

        // Configurar RestAssured
        RestAssured.baseURI = apiBaseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Configurar Request Specification común
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(apiBaseUrl)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        // Configurar Response Specification común
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**
     * Carga la configuración desde config.properties
     */
    private static void loadConfig() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            // Obtener la URL base de la API (por defecto bff.comunidadcorreo.site)
            apiBaseUrl = properties.getProperty("apiBaseUrl", "https://bff.comunidadcorreo.site");
            System.out.println("API Base URL configurada: " + apiBaseUrl);

        } catch (IOException e) {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
            // URL por defecto si no se puede cargar el archivo
            apiBaseUrl = "https://bff.comunidadcorreo.site";
            System.out.println("Usando URL por defecto: " + apiBaseUrl);
        }
    }

    /**
     * Obtiene el RequestSpecification configurado
     */
    protected static RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    /**
     * Obtiene el ResponseSpecification configurado
     */
    protected static ResponseSpecification getResponseSpec() {
        return responseSpec;
    }
}

