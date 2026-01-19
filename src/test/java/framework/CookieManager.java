package framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * Clase utilitaria para guardar y cargar cookies de sesión
 * Útil para reutilizar sesiones de login y evitar tener que autenticarse en cada test
 */
public class CookieManager {

    private static final String COOKIES_DIR = "cookies";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Configurar el ObjectMapper para manejar fechas
        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new com.fasterxml.jackson.databind.JsonSerializer<Date>() {
            @Override
            public void serialize(Date date, com.fasterxml.jackson.core.JsonGenerator gen,
                                  com.fasterxml.jackson.databind.SerializerProvider serializers) throws IOException {
                gen.writeNumber(date.getTime());
            }
        });
        module.addDeserializer(Date.class, new com.fasterxml.jackson.databind.JsonDeserializer<Date>() {
            @Override
            public Date deserialize(com.fasterxml.jackson.core.JsonParser p,
                                   com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
                return new Date(p.getLongValue());
            }
        });
        objectMapper.registerModule(module);
    }

    /**
     * Guarda las cookies actuales del driver en un archivo JSON
     * @param driver WebDriver con las cookies activas
     * @param fileName Nombre del archivo donde guardar las cookies (sin extensión)
     */
    public static void saveCookies(WebDriver driver, String fileName) {
        try {
            // Crear directorio si no existe
            File dir = new File(COOKIES_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Obtener todas las cookies
            Set<Cookie> cookies = driver.manage().getCookies();

            // Guardar en archivo JSON
            String filePath = COOKIES_DIR + File.separator + fileName + ".json";
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), cookies);

            System.out.println("Cookies guardadas exitosamente en: " + filePath);
            System.out.println("Total de cookies guardadas: " + cookies.size());
        } catch (IOException e) {
            System.err.println("Error al guardar cookies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carga cookies desde un archivo JSON y las agrega al driver
     * IMPORTANTE: El driver debe estar en la misma URL donde se guardaron las cookies
     * @param driver WebDriver donde se cargarán las cookies
     * @param fileName Nombre del archivo de cookies (sin extensión)
     * @param baseUrl URL base donde se deben cargar las cookies (debe ser la misma donde se guardaron)
     */
    public static void loadCookies(WebDriver driver, String fileName, String baseUrl) {
        try {
            String filePath = COOKIES_DIR + File.separator + fileName + ".json";
            File file = new File(filePath);

            if (!file.exists()) {
                System.err.println("Archivo de cookies no encontrado: " + filePath);
                return;
            }

            // Primero navegar a la URL base para establecer el dominio
            driver.get(baseUrl);

            // Leer y deserializar las cookies
            Set<Cookie> cookies = objectMapper.readValue(file, new TypeReference<Set<Cookie>>() {});

            // Agregar cada cookie al driver
            for (Cookie cookie : cookies) {
                try {
                    driver.manage().addCookie(cookie);
                } catch (Exception e) {
                    System.err.println("Error al agregar cookie: " + cookie.getName() + " - " + e.getMessage());
                }
            }

            System.out.println("Cookies cargadas exitosamente desde: " + filePath);
            System.out.println("Total de cookies cargadas: " + cookies.size());

            // Refrescar la página para aplicar las cookies
            driver.navigate().refresh();

        } catch (IOException e) {
            System.err.println("Error al cargar cookies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Elimina un archivo de cookies guardado
     * @param fileName Nombre del archivo de cookies (sin extensión)
     */
    public static void deleteCookieFile(String fileName) {
        try {
            String filePath = COOKIES_DIR + File.separator + fileName + ".json";
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                System.out.println("Archivo de cookies eliminado: " + filePath);
            } else {
                System.out.println("Archivo de cookies no existe: " + filePath);
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar archivo de cookies: " + e.getMessage());
        }
    }
}
