package stepsDefinitions.EcosistemaDigital;

import framework.CsvDataLoader;
import framework.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.EcosistemaDigital.PageHomeLoginED;
import page.MiCorreo1_5.*;

import java.util.List;
import java.util.Map;

/**
 * Step definitions específicos para nuevoEnvioED_normal.feature
 * Estos steps leen los datos desde un archivo CSV y son independientes de los otros features
 * Se ejecuta una vez por cada fila del CSV
 */
public class NuevoEnvioNormalSteps {
    private String baseUrl = DriverManager.config.getProperty("urlED");
    
    // Páginas - inicialización lazy (solo cuando se necesiten)
    private PageHomeLoginED pageHomeLoginED;
    private PageMessageHome pageMessageHome;
    private PageNuevoEnvio pageNuevoEnvio;
    private PageCheckOut pageCheckOut;
    private PagePayment pagePayment;
    private PageHomeLogin pageHomeLogin; // Para logout
    
    /**
     * Obtiene el driver (lazy initialization)
     */
    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }
    
    /**
     * Obtiene la página PageHomeLoginED (lazy initialization)
     */
    private PageHomeLoginED getPageHomeLoginED() {
        if (pageHomeLoginED == null) {
            pageHomeLoginED = new PageHomeLoginED(getDriver());
        }
        return pageHomeLoginED;
    }
    
    /**
     * Obtiene la página PageMessageHome (lazy initialization)
     */
    private PageMessageHome getPageMessageHome() {
        if (pageMessageHome == null) {
            pageMessageHome = new PageMessageHome(getDriver());
        }
        return pageMessageHome;
    }
    
    /**
     * Obtiene la página PageNuevoEnvio (lazy initialization)
     */
    private PageNuevoEnvio getPageNuevoEnvio() {
        if (pageNuevoEnvio == null) {
            pageNuevoEnvio = new PageNuevoEnvio(getDriver());
        }
        return pageNuevoEnvio;
    }
    
    /**
     * Obtiene la página PageCheckOut (lazy initialization)
     */
    private PageCheckOut getPageCheckOut() {
        if (pageCheckOut == null) {
            pageCheckOut = new PageCheckOut(getDriver());
        }
        return pageCheckOut;
    }
    
    /**
     * Obtiene la página PagePayment (lazy initialization)
     */
    private PagePayment getPagePayment() {
        if (pagePayment == null) {
            pagePayment = new PagePayment(getDriver());
        }
        return pagePayment;
    }
    
    /**
     * Obtiene la página PageHomeLogin (lazy initialization)
     */
    private PageHomeLogin getPageHomeLogin() {
        if (pageHomeLogin == null) {
            pageHomeLogin = new PageHomeLogin(getDriver());
        }
        return pageHomeLogin;
    }
    
    // Datos cargados desde CSV
    private Map<String, String> datosEnvio;
    private static final String RUTA_CSV = "src/test/resources/datos_nuevo_envio_ED.csv";
    
    // Lista estática con todas las filas del CSV
    private static List<Map<String, String>> todasLasFilasCSV;
    private static boolean csvCargado = false;
    
    /**
     * Hook que se ejecuta antes de cada escenario del feature nuevoEnvioEDNormal
     * Carga todas las filas del CSV una sola vez
     */
    @Before("@nuevoEnvioEDNormal")
    public void inicializarCSV() {
        // Cargar todas las filas del CSV solo la primera vez
        if (!csvCargado) {
            todasLasFilasCSV = CsvDataLoader.cargarDatosDesdeCSV(RUTA_CSV);
            csvCargado = true;
            System.out.println("✓ Total de filas cargadas desde CSV: " + todasLasFilasCSV.size());
        }
        
        // Verificar que hay filas disponibles
        if (todasLasFilasCSV == null || todasLasFilasCSV.isEmpty()) {
            throw new RuntimeException("✗ No se encontraron datos en el CSV: " + RUTA_CSV);
        }
    }
    
    /**
     * Carga los datos de una fila específica del CSV basándose en el índice proporcionado
     */
    @Given("que el usuario carga los datos de la fila {int} desde CSV y está en la página de login")
    public void queElUsuarioCargaLosDatosDeLaFilaDesdeCSVYEstaEnLaPaginaDeLogin(int indiceFila) {
        // Verificar que el CSV fue cargado
        if (todasLasFilasCSV == null || todasLasFilasCSV.isEmpty()) {
            throw new AssertionError("✗ No se pudieron cargar los datos desde el CSV");
        }
        
        // Verificar que el índice es válido
        if (indiceFila < 0 || indiceFila >= todasLasFilasCSV.size()) {
            throw new AssertionError("✗ Índice de fila inválido: " + indiceFila + ". El CSV tiene " + todasLasFilasCSV.size() + " filas (índices 0-" + (todasLasFilasCSV.size() - 1) + ")");
        }
        
        // Cargar la fila correspondiente
        datosEnvio = todasLasFilasCSV.get(indiceFila);
        System.out.println("========================================");
        System.out.println("Ejecutando escenario con fila " + (indiceFila + 1) + " de " + todasLasFilasCSV.size());
        System.out.println("Datos: " + datosEnvio);
        System.out.println("========================================");
        
        getDriver().get(baseUrl);
    }
    
    @And("el usuario ingresa el correo desde CSV")
    public void elUsuarioIngresaElCorreoDesdeCSV() {
        String email = datosEnvio.get("email");
        if (email == null || email.isEmpty()) {
            throw new AssertionError("✗ El campo 'email' no está presente en el CSV o está vacío");
        }
        getPageHomeLoginED().ingresoEmail(email);
        System.out.println("✓ Email ingresado desde CSV: " + email);
    }
    
    @And("el usuario ingresa la contraseña desde CSV")
    public void elUsuarioIngresaLaContrasenaDesdeCSV() {
        String pass = datosEnvio.get("pass");
        if (pass == null || pass.isEmpty()) {
            throw new AssertionError("✗ El campo 'pass' no está presente en el CSV o está vacío");
        }
        getPageHomeLoginED().ingresoPassword(pass);
        System.out.println("✓ Contraseña ingresada desde CSV");
    }
    
    @When("el usuario hace clic en el botón Ingresar desde CSV")
    public void elUsuarioHaceClicEnElBotonIngresarDesdeCSV() {
        getPageHomeLoginED().btnIngresar();
    }
    
    @When("ingresa en nuevo envío individual desde CSV")
    public void ingresaEnNuevoEnvioIndividualDesdeCSV() {
        getPageMessageHome().ingresarANuevoEnvio();
    }
    
    @And("editar el origen del envío individual desde CSV")
    public void editarElOrigenDelEnvioIndividualDesdeCSV() {
        String tipoOrigen = datosEnvio.get("tipoOrigen");
        if (tipoOrigen == null || tipoOrigen.isEmpty()) {
            throw new AssertionError("✗ El campo 'tipoOrigen' no está presente en el CSV o está vacío");
        }
        getPageNuevoEnvio().origenDelEnvioIndividualConZonas(tipoOrigen);
        System.out.println("✓ Tipo de origen ingresado desde CSV: " + tipoOrigen);
    }
    
    @And("llena los campos de paquete desde CSV")
    public void llenaLosCamposDePaqueteDesdeCSV() {
        String peso = datosEnvio.get("peso");
        if (peso == null || peso.isEmpty()) {
            throw new AssertionError("✗ El campo 'peso' no está presente en el CSV o está vacío");
        }
        getPageNuevoEnvio().caracteristicasDelPaqueteCM(peso);
        System.out.println("✓ Peso ingresado desde CSV: " + peso);
    }
    
    @And("selecciona el tipo de entrega desde CSV y completa el formulario de destino")
    public void seleccionaElTipoDeEntregaDesdeCSVYCompletaElFormularioDeDestino() {
        String tipoEntrega = datosEnvio.get("tipoEntrega");
        if (tipoEntrega == null || tipoEntrega.isEmpty()) {
            throw new AssertionError("✗ El campo 'tipoEntrega' no está presente en el CSV o está vacío");
        }
        getPageNuevoEnvio().tipoEntregaZonas(tipoEntrega);
        System.out.println("✓ Tipo de entrega seleccionado desde CSV: " + tipoEntrega);
    }
    
    @And("selecciona el tipo de producto desde CSV y procede a pagar")
    public void seleccionaElTipoDeProductoDesdeCSVYProcedeAPagar() {
        String tipoProducto = datosEnvio.get("tipoProducto");
        if (tipoProducto == null || tipoProducto.isEmpty()) {
            throw new AssertionError("✗ El campo 'tipoProducto' no está presente en el CSV o está vacío");
        }
        getPageNuevoEnvio().tipoProducto(tipoProducto);
        System.out.println("✓ Tipo de producto seleccionado desde CSV: " + tipoProducto);
    }
    
    @And("se muestra la grilla de checkout desde CSV")
    public void seMuestraLaGrillaDeCheckoutDesdeCSV() {
        getPageCheckOut().validarFormularioCheckout();
    }
    
    @Then("realiza el pago con el medio de pago desde CSV")
    public void realizaElPagoConElMedioDePagoDesdeCSV() {
        String medioPago = datosEnvio.get("medioPago");
        if (medioPago == null || medioPago.isEmpty()) {
            throw new AssertionError("✗ El campo 'medioPago' no está presente en el CSV o está vacío");
        }
        getPageCheckOut().medioPago(medioPago);
        System.out.println("✓ Medio de pago seleccionado desde CSV: " + medioPago);
    }
    
    @And("se confirma que el pago se ha realizado con exito desde CSV")
    public void seConfirmaQueElPagoSeHaRealizadoConExitoDesdeCSV() {
        getPagePayment().verificarPago();
        getPageHomeLogin().logout();
    }
}

