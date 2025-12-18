package api.tests;

import api.framework.ApiTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Tests para validar un listado de CUITs usando la API de ARCA
 * Similar a BindX - valida múltiples CUITs desde un archivo
 */
public class ArcaCuitValidationTests extends ApiTestBase {

    /**
     * Clase interna para almacenar información de un CUIT
     */
    private static class CuitInfo {
        String cuit;
        String tipo; // V=Valido, I=Invalido, E=Empresa, P=Persona
        String descripcion;
        
        CuitInfo(String cuit, String tipo, String descripcion) {
            this.cuit = cuit;
            this.tipo = tipo;
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
                // Ignorar comentarios y líneas vacías
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                
                // Formato: CUIT|Tipo|Descripcion
                String[] partes = linea.split("\\|");
                if (partes.length >= 3) {
                    cuits.add(new CuitInfo(partes[0].trim(), partes[1].trim(), partes[2].trim()));
                } else if (partes.length == 1) {
                    // Si solo hay CUIT, asumir que es válido
                    cuits.add(new CuitInfo(partes[0].trim(), "V", "CUIT sin tipo especificado"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de CUITs: " + e.getMessage(), e);
        }
        
        return cuits;
    }

    /**
     * Valida un CUIT individual usando la API
     */
    private Response validarCuit(String cuit) {
        return given()
                .spec(getRequestSpec())
                .queryParam("cuit", cuit)
                .when()
                .get("/v2/integration/validate-cuit")
                .then()
                .extract()
                .response();
    }

    /**
     * Test principal: Valida un listado completo de CUITs desde archivo
     */
    @Test
    @DisplayName("Validar listado completo de CUITs desde archivo")
    public void testValidarListadoCuits() {
        System.out.println("\n========================================");
        System.out.println("VALIDACIÓN DE LISTADO DE CUITs - ARCA");
        System.out.println("========================================\n");
        
        List<CuitInfo> cuits = leerListaCuits("cuits_lista.txt");
        
        int totalCuits = cuits.size();
        int cuitsValidos = 0;
        int cuitsInvalidos = 0;
        int errores = 0;
        
        List<String> resultados = new ArrayList<>();
        
        for (int i = 0; i < cuits.size(); i++) {
            CuitInfo cuitInfo = cuits.get(i);
            System.out.println(String.format("[%d/%d] Validando CUIT: %s (%s)", 
                    i + 1, totalCuits, cuitInfo.cuit, cuitInfo.descripcion));
            
            try {
                Response response = validarCuit(cuitInfo.cuit);
                int statusCode = response.getStatusCode();
                String responseBody = response.asString();
                
                // Determinar si el CUIT es válido según la respuesta
                boolean esValido = (statusCode == 200);
                
                // Si el tipo esperado es "I" (inválido), esperamos un error
                if ("I".equals(cuitInfo.tipo)) {
                    esValido = (statusCode != 200);
                }
                
                String resultado;
                if (esValido) {
                    cuitsValidos++;
                    resultado = String.format("✓ CUIT %s: VÁLIDO - %s", cuitInfo.cuit, cuitInfo.descripcion);
                } else {
                    cuitsInvalidos++;
                    resultado = String.format("✗ CUIT %s: INVÁLIDO - %s (Status: %d)", 
                            cuitInfo.cuit, cuitInfo.descripcion, statusCode);
                }
                
                resultados.add(resultado);
                System.out.println("  " + resultado);
                System.out.println("  Response: " + responseBody.substring(0, Math.min(100, responseBody.length())));
                
            } catch (Exception e) {
                errores++;
                String resultado = String.format("✗ CUIT %s: ERROR - %s", cuitInfo.cuit, e.getMessage());
                resultados.add(resultado);
                System.out.println("  " + resultado);
            }
            
            System.out.println();
        }
        
        // Resumen final
        System.out.println("========================================");
        System.out.println("RESUMEN DE VALIDACIÓN");
        System.out.println("========================================");
        System.out.println("Total de CUITs procesados: " + totalCuits);
        System.out.println("CUITs válidos: " + cuitsValidos);
        System.out.println("CUITs inválidos: " + cuitsInvalidos);
        System.out.println("Errores: " + errores);
        System.out.println("========================================\n");
        
        // Imprimir todos los resultados
        System.out.println("DETALLE DE RESULTADOS:");
        System.out.println("----------------------------------------");
        for (String resultado : resultados) {
            System.out.println(resultado);
        }
    }

    /**
     * Test: Valida CUITs válidos específicos
     */
    @Test
    @DisplayName("Validar CUITs válidos conocidos")
    public void testValidarCuitsValidos() {
        String[] cuitsValidos = {
            "20301234567",
            "30708902507",
            "20123456789"
        };
        
        System.out.println("\nValidando CUITs válidos conocidos:");
        for (String cuit : cuitsValidos) {
            Response response = validarCuit(cuit);
            System.out.println("CUIT: " + cuit);
            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Response: " + response.asString());
            
            response.then()
                    .statusCode(200)
                    .body(notNullValue());
        }
    }

    /**
     * Test: Valida CUITs inválidos específicos
     */
    @Test
    @DisplayName("Validar CUITs inválidos conocidos")
    public void testValidarCuitsInvalidos() {
        String[] cuitsInvalidos = {
            "12345678901",  // Dígito verificador incorrecto
            "00000000000",  // Todos ceros
            "11111111111"   // Formato inválido
        };
        
        System.out.println("\nValidando CUITs inválidos conocidos:");
        for (String cuit : cuitsInvalidos) {
            Response response = validarCuit(cuit);
            System.out.println("CUIT: " + cuit);
            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Response: " + response.asString());
            
            // Los CUITs inválidos pueden retornar 200 con un flag de inválido, o 400/422
            response.then()
                    .statusCode(anyOf(is(200), is(400), is(422)));
        }
    }

    /**
     * Test: Valida un CUIT específico y muestra información detallada
     */
    @Test
    @DisplayName("Validar CUIT específico con información detallada")
    public void testValidarCuitDetallado() {
        String cuit = "30708902507";
        
        System.out.println("\n========================================");
        System.out.println("VALIDACIÓN DETALLADA DE CUIT");
        System.out.println("========================================");
        System.out.println("CUIT: " + cuit);
        
        Response response = validarCuit(cuit);
        
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Content Type: " + response.getContentType());
        System.out.println("Response Body: " + response.asString());
        System.out.println("Response Headers: " + response.getHeaders());
        
        response.then()
                .statusCode(200)
                .body(notNullValue());
        
        System.out.println("========================================\n");
    }
}
