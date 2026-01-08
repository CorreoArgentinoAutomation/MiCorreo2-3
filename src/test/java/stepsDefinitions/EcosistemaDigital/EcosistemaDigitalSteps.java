package stepsDefinitions.EcosistemaDigital;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import page.EcosistemaDigital.PageHomeLoginED;
import page.EcosistemaDigital.PageYopMailED;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class EcosistemaDigitalSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("urlED");
    private PageHomeLoginED pageHomeLoginED = new PageHomeLoginED(driver);
    private PageYopMailED pageYopMail = new PageYopMailED(driver);

    public String email;
    public String numeroSeguimiento;
    public String cuitValido;
    public String nombreUsuario;
    public String apellidoUsuario;

    /*
    @Given("^el usuario se situa en los campos email y password$")
    public void elUsuarioSeSituaEnLosCamposEmailYPassword() {
        driver.get(baseUrl);
        //pageHomeLogin.assertURL("https://wcpzt.correo.local/MiCorreo/public/");
    }

     */

    @Given("que el usuario está en la página de login")
    public void queElUsuarioEstaEnLaPaginaDeLogin() {
        driver.get(baseUrl);
    }

    @And("el sistema valida que el campo de correo existe y está visible")
    public void elSistemaValidaQueElCampoDeCorreoExisteYEstaVisible() {
        pageHomeLoginED.validarCampoCorreoExiste();
    }

    @And("el sistema valida que el campo de contraseña existe y está visible")
    public void elSistemaValidaQueElCampoDeContrasenaExisteYEstaVisible() {
        pageHomeLoginED.validarCampoContrasenaExiste();
    }

    @And("el sistema valida que el botón Ingresar existe y está visible")
    public void elSistemaValidaQueElBotonIngresarExisteYEstaVisible() {
        pageHomeLoginED.validarBotonIngresarExiste();
    }

    @And("el usuario ingresa el correo {string}")
    public void elUsuarioIngresaElCorreo(String email) {
        pageHomeLoginED.ingresoEmail(email);
    }

    @And("el usuario ingresa la contraseña {string}")
    public void elUsuarioIngresaLaContrasena(String password) {
        pageHomeLoginED.ingresoPassword(password);
    }

    @When("el usuario hace clic en el botón Ingresar")
    public void elUsuarioHaceClicEnElBotonIngresar() {
        pageHomeLoginED.btnIngresar();
    }

    @Then("el sistema muestra el mensaje {string}")
    public void elSistemaMuestraElMensaje(String mensajeEsperado) {
        pageHomeLoginED.msjEsperado(mensajeEsperado);
    }

    @When("que el usuario ingresa al sitio de ecosistema digital")
    public void queElUsuarioIngresaAlSitioDeEcosistemaDigital() {
        driver.get(baseUrl);
    }

    // Step definitions para recuperación de contraseña
    @When("el usuario hace clic en el enlace {string}")
    public void elUsuarioHaceClicEnElEnlace(String enlace) {
        if (enlace.contains("Olvidaste") || enlace.contains("olvidaste") || enlace.contains("contraseña")) {
            pageHomeLoginED.clicOlvidasteContrasena();
        } else {
            throw new AssertionError("Enlace no reconocido: " + enlace);
        }
    }

    @And("el usuario ingresa su correo electrónico {string} para recuperar contraseña")
    public void elUsuarioIngresaSuCorreoElectronicoParaRecuperarContrasena(String email) {
        pageHomeLoginED.ingresarEmailRecuperacion(email);
    }

    @And("el usuario solicita la recuperación de contraseña")
    public void elUsuarioSolicitaLaRecuperacionDeContrasena() {
        pageHomeLoginED.solicitarRecuperacionContrasena();
    }

    @Then("el sistema muestra un mensaje de confirmación")
    public void elSistemaMuestraUnMensajeDeConfirmacion() {
        pageHomeLoginED.validarMensajeConfirmacion();
    }

    @When("el usuario accede a su correo electrónico {string}")
    public void elUsuarioAccedeASuCorreoElectronico(String email) {
        // Guardar el email para usarlo en el siguiente paso
        this.email = email;
        System.out.println("Preparando acceso al correo: " + email);
    }

    @And("el usuario busca el email de recuperación de contraseña")
    public void elUsuarioBuscaElEmailDeRecuperacionDeContrasena() {
        // Usar el email guardado o el del feature
        String emailABuscar = (this.email != null && !this.email.isEmpty()) ? this.email : "cf_tester02@yopmail.com";
        pageYopMail.buscarEmailRecuperacionContrasena(emailABuscar);
    }

    @Then("el sistema valida que el email de recuperación fue recibido")
    public void elSistemaValidaQueElEmailDeRecuperacionFueRecibido() {
        pageYopMail.validarEmailRecuperacionRecibido();
    }

    @And("el sistema valida que el email contiene un enlace para recuperar contraseña")
    public void elSistemaValidaQueElEmailContieneUnEnlaceParaRecuperarContrasena() {
        pageYopMail.validarEnlaceRecuperacionEnEmail();
    }

    @When("el usuario hace clic en el enlace de recuperación de contraseña del email")
    public void elUsuarioHaceClicEnElEnlaceDeRecuperacionDeContrasenaDelEmail() {
        pageYopMail.clicEnEnlaceRecuperacion();
    }

    @And("el usuario ingresa una nueva contraseña aleatoria")
    public void elUsuarioIngresaUnaNuevaContrasenaAleatoria() {
        pageHomeLoginED.ingresarNuevaContrasena();
    }

    @And("el usuario confirma la nueva contraseña")
    public void elUsuarioConfirmaLaNuevaContrasena() {
        pageHomeLoginED.confirmarNuevaContrasena();
    }

    @And("el usuario hace clic en el botón para reiniciar la contraseña")
    public void elUsuarioHaceClicEnElBotonParaReiniciarLaContrasena() {
        pageHomeLoginED.clicReiniciarContrasena();
    }

    // Step definitions para registro de usuario
    @Given("el usuario crea un email temporal")
    public void elUsuarioCreaUnEmailTemporal() {
        driver.get("https://yopmail.com/es/");
        email = pageYopMail.crearMailTemporal();
        System.out.println("✓ Email temporal creado: " + email);
    }

    @When("el usuario hace clic en el boton Registrarme")
    public void elUsuarioHaceClicEnElBotonRegistrarme() {
        pageHomeLoginED.clicRegistrarme();
    }

    @And("el usuario llena el formulario de registro")
    public void elUsuarioLlenaElFormularioDeRegistro() {
        pageHomeLoginED.llenarFormularioRegistro(email);
    }

    @And("el usuario abre el servicio de correo electronico")
    public void elUsuarioAbreElServicioDeCorreoElectronico() {
        driver.get("https://yopmail.com/es/");
        pageYopMail.buscarEmailTemporal(email);
    }

    @And("el usuario hace clic en el boton Activa tu usuario")
    public void elUsuarioHaceClicEnElBotonActivaTuUsuario() {
        pageYopMail.clicEnEnlaceActivacion(email);
    }

    @Then("el usuario deberia ver el mensaje de bienvenida")
    public void elUsuarioDeberiaVerElMensajeDeBienvenida() {
        pageHomeLoginED.validarMensajeBienvenida();
    }

    // Step definitions para validaciones de campos
    @When("el usuario ingresa {string} con el valor {string}")
    public void elUsuarioIngresaConElValor(String campo, String valor) {
        // Si el valor está vacío en el feature, convertirlo a null
        String valorFinal = (valor == null || valor.trim().isEmpty()) ? null : valor;
        pageHomeLoginED.ingresarValorEnCampo(campo, valorFinal);
    }

    @And("el usuario hace clic en el boton Siguiente")
    public void elUsuarioHaceClicEnElBotonSiguiente() {
        pageHomeLoginED.clicBotonSiguiente();
    }

    @Then("el sistema debe mostrar el mensaje de validación {string}")
    public void elSistemaDebeMostrarElMensajeDeValidacion(String mensajeEsperado) {
        pageHomeLoginED.validarMensajeValidacion(mensajeEsperado);
    }

    // Step definitions para registro con CUIT
    @And("el usuario valida y obtiene un CUIT válido")
    public void elUsuarioValidaYObtieneUnCUITValido() {
        try {
            // Cargar configuración de API
            Properties config = new Properties();
            try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
                config.load(fis);
            }
            String apiBaseUrl = config.getProperty("apiBaseUrl", "https://bff.comunidadcorreo.site");
            
            // Leer lista de CUITs desde el archivo
            List<String> cuits = leerCuitsDesdeArchivo("src/test/resources/cuits_lista.txt");
            
            // Intentar validar CUITs hasta encontrar uno válido
            String cuitEncontrado = null;
            for (String cuit : cuits) {
                cuit = cuit.trim();
                if (cuit.isEmpty() || cuit.startsWith("#")) {
                    continue;
                }
                
                System.out.println("Validando CUIT: " + cuit);
                
                try {
                    Response response = given()
                            .baseUri(apiBaseUrl)
                            .queryParam("cuit", cuit)
                            .when()
                            .get("/v2/integration/validate-cuit")
                            .then()
                            .extract()
                            .response();
                    
                    int statusCode = response.getStatusCode();
                    if (statusCode == 200) {
                        cuitEncontrado = cuit;
                        System.out.println("✓ CUIT válido encontrado: " + cuit);
                        break;
                    } else {
                        System.out.println("✗ CUIT inválido: " + cuit + " (Status: " + statusCode + ")");
                    }
                } catch (Exception e) {
                    System.out.println("✗ Error al validar CUIT " + cuit + ": " + e.getMessage());
                    continue;
                }
            }
            
            if (cuitEncontrado == null) {
                throw new AssertionError("✗ No se encontró ningún CUIT válido en la lista");
            }
            
            this.cuitValido = cuitEncontrado;
            System.out.println("✓ CUIT válido seleccionado para el registro: " + cuitValido);
            
        } catch (Exception e) {
            throw new AssertionError("✗ Error al validar y obtener CUIT válido: " + e.getMessage());
        }
    }

    @And("el usuario llena el formulario de registro con CUIT")
    public void elUsuarioLlenaElFormularioDeRegistroConCUIT() {
        if (cuitValido == null || cuitValido.isEmpty()) {
            throw new AssertionError("✗ No hay CUIT válido disponible. Debe ejecutarse primero el paso de validación de CUIT.");
        }
        pageHomeLoginED.llenarFormularioRegistroConCUIT(email, cuitValido);
        // Guardar nombre y apellido para el archivo
        this.nombreUsuario = pageHomeLoginED.getNombreUsuario();
        this.apellidoUsuario = pageHomeLoginED.getApellidoUsuario();
    }

    @And("el sistema guarda los datos del usuario creado con CUIT en un archivo")
    public void elSistemaGuardaLosDatosDelUsuarioCreadoConCUITEnUnArchivo() {
        try {
            String archivoPath = "src/main/resources/usuarios_registrados_cuit.txt";
            Path path = Paths.get(archivoPath);
            
            // Crear el archivo si no existe
            if (!Files.exists(path)) {
                Files.createFile(path);
                // Escribir encabezado
                String encabezado = "========================================\n";
                encabezado += "USUARIOS REGISTRADOS CON CUIT\n";
                encabezado += "========================================\n";
                encabezado += "Fecha|Email|CUIT|Nombre|Apellido|Contraseña\n";
                encabezado += "========================================\n";
                Files.write(path, encabezado.getBytes(), StandardOpenOption.WRITE);
            }
            
            // Preparar datos del usuario
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fecha = ahora.format(formatter);
            String contraseña = "123123"; // Contraseña por defecto usada en el registro
            
            // Formato: Fecha|Email|CUIT|Nombre|Apellido|Contraseña
            String linea = String.format("%s|%s|%s|%s|%s|%s\n",
                    fecha,
                    email != null ? email : "N/A",
                    cuitValido != null ? cuitValido : "N/A",
                    nombreUsuario != null ? nombreUsuario : "N/A",
                    apellidoUsuario != null ? apellidoUsuario : "N/A",
                    contraseña);
            
            // Agregar al archivo
            Files.write(path, linea.getBytes(), StandardOpenOption.APPEND);
            
            System.out.println("✓ Datos del usuario guardados en: " + archivoPath);
            System.out.println("  Email: " + email);
            System.out.println("  CUIT: " + cuitValido);
            System.out.println("  Nombre: " + nombreUsuario);
            System.out.println("  Apellido: " + apellidoUsuario);
            
        } catch (Exception e) {
            System.err.println("⚠ Error al guardar datos del usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lee la lista de CUITs desde un archivo
     */
    private List<String> leerCuitsDesdeArchivo(String rutaArchivo) {
        List<String> cuits = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                // Ignorar comentarios y líneas vacías
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                // Si tiene formato con pipes, extraer solo el CUIT
                if (linea.contains("|")) {
                    String[] partes = linea.split("\\|");
                    if (partes.length > 0) {
                        cuits.add(partes[0].trim());
                    }
                } else {
                    // Formato simple: solo el CUIT
                    cuits.add(linea);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de CUITs: " + e.getMessage(), e);
        }
        return cuits;
    }
}
