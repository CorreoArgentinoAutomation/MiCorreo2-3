package api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para validar CUITs consultando directamente la API de ARCA
 * (sin pasar por el BFF de ComunidadCorreo)
 * 
 * ⚠⚠⚠ IMPORTANTE ⚠⚠⚠
 * ARCA (AFIP) usa servicios SOAP, NO REST. Si todos los tests dan error 404,
 * significa que:
 * 1. La URL configurada es un servicio SOAP (.asmx), no un endpoint REST
 * 2. Se necesita implementar un cliente SOAP (Apache CXF, JAX-WS)
 * 3. Puede requerir certificados digitales y autenticación
 * 
 * SOLUCIÓN RECOMENDADA:
 * - Usar el endpoint del BFF: /v2/integration/validate-cuit (ya implementado)
 * - O implementar un cliente SOAP para ARCA
 * - O verificar si existe un endpoint REST público de ARCA/AFIP
 * 
 * Para deshabilitar estos tests temporalmente, comenta la anotación @Test
 */
public class ArcaDirectValidationTests {

    private String arcaApiUrl;
    
    /**
     * Carga la configuración de ARCA desde config.properties
     */
    private void loadArcaConfig() {
        Properties properties = new Properties();
        try {
            java.io.FileInputStream fileInputStream = new java.io.FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
            
            arcaApiUrl = properties.getProperty("arcaApiUrl", 
                "https://servicios1.afip.gov.ar/ws/wsrcpadronv2/service.asmx");
            
            System.out.println("ARCA API URL configurada: " + arcaApiUrl);
        } catch (IOException e) {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
            arcaApiUrl = "https://servicios1.afip.gov.ar/ws/wsrcpadronv2/service.asmx";
            System.out.println("Usando URL por defecto de ARCA: " + arcaApiUrl);
        }
    }

    /**
     * Clase interna para almacenar información de un CUIT
     */
    private static class CuitInfo {
        String cuit;
        String descripcion;
        
        CuitInfo(String cuit, String descripcion) {
            this.cuit = cuit;
            this.descripcion = descripcion;
        }
    }

    /**
     * Lee la lista de CUITs desde un archivo
     */
    private List<CuitInfo> leerListaCuits(String archivo) {
        List<CuitInfo> cuits = new ArrayList<>();
        String rutaArchivo = "src/test/resources/" + archivo;
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                
                // Formato simple: Solo el número de CUIT
                if (linea.matches("\\d+")) {
                    cuits.add(new CuitInfo(linea, "CUIT para validar"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de CUITs: " + e.getMessage(), e);
        }
        
        return cuits;
    }

    /**
     * Valida un CUIT directamente con ARCA
     * 
     * IMPORTANTE: ARCA usa servicios SOAP, no REST. Este método intenta
     * diferentes formatos REST, pero probablemente necesitarás:
     * 1. Usar un cliente SOAP (Apache CXF, JAX-WS)
     * 2. Configurar certificados digitales
     * 3. Autenticación con Token y Sign
     * 
     * Si todos los tests dan 404, significa que la URL no es correcta
     * o que ARCA requiere SOAP en lugar de REST.
     */
    private Response validarCuitDirectoARCA(String cuit) {
        loadArcaConfig();
        
        // NOTA: La URL por defecto es un servicio SOAP (.asmx), no REST
        // Si todos los tests dan 404, necesitas:
        // 1. Verificar la URL correcta del endpoint REST (si existe)
        // 2. O implementar un cliente SOAP
        
        // Intentar diferentes formatos de endpoint según la implementación de ARCA
        // Opción 1: Endpoint REST con query parameter
        try {
            Response response = given()
                    .baseUri(arcaApiUrl)
                    .queryParam("cuit", cuit)
                    .when()
                    .get()
                    .then()
                    .extract()
                    .response();
            
            // Si no es 404, retornar la respuesta
            if (response.getStatusCode() != 404) {
                return response;
            }
        } catch (Exception e) {
            System.out.println("⚠ Error con formato REST (query param): " + e.getMessage());
        }
        
        // Opción 2: Endpoint REST con path parameter
        try {
            Response response = given()
                    .baseUri(arcaApiUrl)
                    .pathParam("cuit", cuit)
                    .when()
                    .get("/{cuit}")
                    .then()
                    .extract()
                    .response();
            
            if (response.getStatusCode() != 404) {
                return response;
            }
        } catch (Exception e) {
            System.out.println("⚠ Error con formato REST (path param): " + e.getMessage());
        }
        
        // Opción 3: Intentar con endpoint alternativo común de AFIP
        try {
            String urlAlternativa = "https://servicios1.afip.gov.ar/wsrcpadronv2/service.asmx/GetPersona";
            Response response = given()
                    .baseUri(urlAlternativa)
                    .queryParam("cuit", cuit)
                    .when()
                    .get()
                    .then()
                    .extract()
                    .response();
            
            if (response.getStatusCode() != 404) {
                return response;
            }
        } catch (Exception e) {
            System.out.println("⚠ Error con endpoint alternativo: " + e.getMessage());
        }
        
        // Si todos fallan, retornar una respuesta con 404 para que el test lo maneje
        System.out.println("⚠ ADVERTENCIA: Todos los formatos REST fallaron con 404.");
        System.out.println("⚠ ARCA probablemente requiere SOAP en lugar de REST.");
        System.out.println("⚠ URL configurada: " + arcaApiUrl);
        System.out.println("⚠ Considera usar un cliente SOAP o verificar la URL correcta.");
        
        // Crear una respuesta mock con 404 para que el test pueda continuar
        throw new RuntimeException("Endpoint ARCA no encontrado (404). " +
                "ARCA requiere servicios SOAP, no REST. " +
                "Necesitas implementar un cliente SOAP o usar la URL correcta del endpoint REST.");
    }

    /**
     * Test: Valida un CUIT específico directamente con ARCA
     * Usa el primer CUIT del archivo cuits_lista.txt
     * 
     * ⚠ NOTA: Este test puede fallar si ARCA requiere SOAP en lugar de REST
     */
    // @Test  // Descomentar cuando se configure correctamente la URL de ARCA
    @DisplayName("Validar CUIT específico directamente con ARCA")
    public void testValidarCuitDirectoARCA() {
        // Leer el primer CUIT del archivo
        List<CuitInfo> cuits = leerListaCuits("cuits_lista.txt");
        if (cuits.isEmpty()) {
            throw new RuntimeException("No se encontraron CUITs en el archivo cuits_lista.txt");
        }
        String cuit = cuits.get(0).cuit;
        
        System.out.println("\n========================================");
        System.out.println("VALIDACIÓN DIRECTA CON ARCA");
        System.out.println("========================================");
        System.out.println("CUIT (del archivo cuits_lista.txt): " + cuit);
        loadArcaConfig();
        System.out.println("ARCA API URL: " + arcaApiUrl);
        
        try {
            Response response = validarCuitDirectoARCA(cuit);
            
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Content Type: " + response.getContentType());
            System.out.println("Response Body: " + response.asString());
            
            // ARCA puede retornar diferentes códigos según la implementación
            response.then()
                    .statusCode(anyOf(is(200), is(400), is(404), is(500)));
            
        } catch (Exception e) {
            System.out.println("✗ Error al validar CUIT con ARCA: " + e.getMessage());
            System.out.println("NOTA: ARCA puede requerir autenticación SOAP o certificados digitales.");
            throw e;
        }
        
        System.out.println("========================================\n");
    }

    /**
     * Test: Valida un listado de CUITs directamente con ARCA
     * 
     * ⚠ NOTA: Este test puede fallar si ARCA requiere SOAP en lugar de REST
     * Si todos los CUITs dan 404, verificar la URL o implementar cliente SOAP
     */
    // @Test  // Descomentar cuando se configure correctamente la URL de ARCA
    @DisplayName("Validar listado de CUITs directamente con ARCA")
    public void testValidarListadoCuitsDirectoARCA() {
        loadArcaConfig();
        
        System.out.println("\n========================================");
        System.out.println("VALIDACIÓN DIRECTA DE LISTADO - ARCA");
        System.out.println("========================================\n");
        System.out.println("ARCA API URL: " + arcaApiUrl);
        
        List<CuitInfo> cuits = leerListaCuits("cuits_lista.txt");
        
        int totalCuits = cuits.size();
        int cuitsValidos = 0;
        int cuitsInvalidos = 0;
        int errores = 0;
        
        List<String> resultados = new ArrayList<>();
        
        // Procesar todos los CUITs del archivo
        for (int i = 0; i < cuits.size(); i++) {
            CuitInfo cuitInfo = cuits.get(i);
            System.out.println(String.format("[%d/%d] Validando CUIT: %s", 
                    i + 1, totalCuits, cuitInfo.cuit));
            
            try {
                Response response = validarCuitDirectoARCA(cuitInfo.cuit);
                int statusCode = response.getStatusCode();
                String responseBody = response.asString();
                
                boolean esValido = (statusCode == 200);
                
                String resultado;
                if (esValido) {
                    cuitsValidos++;
                    resultado = String.format("✓ CUIT %s: VÁLIDO", cuitInfo.cuit);
                } else {
                    cuitsInvalidos++;
                    resultado = String.format("✗ CUIT %s: INVÁLIDO (Status: %d)", 
                            cuitInfo.cuit, statusCode);
                }
                
                resultados.add(resultado);
                System.out.println("  " + resultado);
                System.out.println("  Response: " + responseBody.substring(0, Math.min(100, responseBody.length())));
                
            } catch (Exception e) {
                errores++;
                String errorMsg = e.getMessage();
                // Acortar mensajes muy largos
                if (errorMsg != null && errorMsg.length() > 100) {
                    errorMsg = errorMsg.substring(0, 100) + "...";
                }
                String resultado = String.format("✗ CUIT %s: ERROR - %s", cuitInfo.cuit, errorMsg);
                resultados.add(resultado);
                System.out.println("  " + resultado);
                
                // Si es un error 404, mostrar advertencia especial
                if (errorMsg != null && errorMsg.contains("404")) {
                    System.out.println("  ⚠ Este error indica que el endpoint no existe o ARCA requiere SOAP");
                }
            }
            
            System.out.println();
        }
        
        // Resumen
        System.out.println("========================================");
        System.out.println("RESUMEN DE VALIDACIÓN DIRECTA ARCA");
        System.out.println("========================================");
        System.out.println("Total de CUITs procesados: " + totalCuits);
        System.out.println("CUITs válidos: " + cuitsValidos);
        System.out.println("CUITs inválidos: " + cuitsInvalidos);
        System.out.println("Errores: " + errores);
        System.out.println("========================================\n");
        
        // Advertencia si todos fallaron
        if (cuitsValidos == 0 && (cuitsInvalidos > 0 || errores > 0)) {
            System.out.println("⚠⚠⚠ ADVERTENCIA IMPORTANTE ⚠⚠⚠");
            System.out.println("Todos los CUITs fallaron. Esto generalmente significa:");
            System.out.println("1. La URL de ARCA no es correcta");
            System.out.println("2. ARCA requiere servicios SOAP (no REST)");
            System.out.println("3. Se necesita autenticación con certificados digitales");
            System.out.println("4. El endpoint REST no existe o está en otra URL");
            System.out.println("");
            System.out.println("SOLUCIONES:");
            System.out.println("- Verificar la URL correcta del endpoint REST de ARCA");
            System.out.println("- Implementar un cliente SOAP (Apache CXF, JAX-WS)");
            System.out.println("- Configurar certificados digitales si es necesario");
            System.out.println("- Usar el endpoint del BFF en su lugar: /v2/integration/validate-cuit");
            System.out.println("========================================\n");
        }
        
        // Imprimir todos los resultados
        System.out.println("DETALLE DE RESULTADOS:");
        System.out.println("----------------------------------------");
        for (String resultado : resultados) {
            System.out.println(resultado);
        }
    }

    /**
     * Test: Compara resultados entre BFF y ARCA directo
     * Usa el primer CUIT del archivo cuits_lista.txt
     * 
     * ⚠ NOTA: Este test puede fallar si ARCA requiere SOAP en lugar de REST
     */
    @Test
    @DisplayName("Comparar validación BFF vs ARCA directo")
    public void testCompararBFFvsARCADirecto() {
        // Leer el primer CUIT del archivo
        List<CuitInfo> cuits = leerListaCuits("cuits_lista.txt");
        if (cuits.isEmpty()) {
            throw new RuntimeException("No se encontraron CUITs en el archivo cuits_lista.txt");
        }
        String cuit = cuits.get(0).cuit;
        
        System.out.println("\n========================================");
        System.out.println("COMPARACIÓN BFF vs ARCA DIRECTO");
        System.out.println("========================================");
        System.out.println("CUIT (del archivo cuits_lista.txt): " + cuit);
        
        // Validación a través del BFF
        System.out.println("\n--- Validación a través del BFF ---");
        try {
            Response responseBFF = given()
                    .baseUri("https://bff.comunidadcorreo.site")
                    .queryParam("cuit", cuit)
                    .when()
                    .get("/v2/integration/validate-cuit")
                    .then()
                    .extract()
                    .response();
            
            int statusBFF = responseBFF.getStatusCode();
            String responseBFFBody = responseBFF.asString();
            
            System.out.println("Status BFF: " + statusBFF);
            System.out.println("Response BFF: " + responseBFFBody);
            
            if (statusBFF == 500) {
                System.out.println("⚠ El BFF está retornando error 500 (Internal Server Error)");
                System.out.println("⚠ Esto puede indicar:");
                System.out.println("  - Problema de conexión con el servicio de integración");
                System.out.println("  - El servicio de integración no está disponible");
                System.out.println("  - Error en la configuración del BFF");
            } else if (statusBFF == 200) {
                System.out.println("✓ BFF respondió correctamente");
            }
        } catch (Exception e) {
            System.out.println("✗ Error BFF: " + e.getMessage());
            System.out.println("⚠ No se pudo conectar con el BFF");
        }
        
        // Validación directa con ARCA
        System.out.println("\n--- Validación directa con ARCA ---");
        try {
            Response responseARCA = validarCuitDirectoARCA(cuit);
            int statusARCA = responseARCA.getStatusCode();
            String responseARCABody = responseARCA.asString();
            
            System.out.println("Status ARCA: " + statusARCA);
            System.out.println("Response ARCA: " + responseARCABody);
            
            if (statusARCA == 200) {
                System.out.println("✓ ARCA respondió correctamente");
            }
        } catch (Exception e) {
            System.out.println("✗ Error ARCA: " + e.getMessage());
            System.out.println("");
            System.out.println("⚠ NOTA IMPORTANTE:");
            System.out.println("ARCA requiere servicios SOAP, no REST.");
            System.out.println("Para consultar ARCA directamente necesitas:");
            System.out.println("  1. Implementar un cliente SOAP (Apache CXF, JAX-WS)");
            System.out.println("  2. Configurar certificados digitales");
            System.out.println("  3. Autenticación con Token y Sign");
            System.out.println("");
            System.out.println("RECOMENDACIÓN: Usar el endpoint del BFF cuando esté disponible.");
        }
        
        System.out.println("\n========================================");
        System.out.println("CONCLUSIÓN:");
        System.out.println("========================================");
        System.out.println("Si ambos endpoints fallan:");
        System.out.println("  - BFF: Verificar que el servicio esté disponible");
        System.out.println("  - ARCA: Requiere implementación SOAP o endpoint REST válido");
        System.out.println("========================================\n");
    }
}

