package stepsDefinitions.EcosistemaDigital;

import framework.CsvDataLoader;
import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.EcosistemaDigital.PageHomeLoginED;
import page.MiCorreo1_5.*;

import java.util.Map;

/**
 * Step definitions específicos para nuevoEnvioED_normal.feature
 * Estos steps leen los datos desde un archivo CSV y son independientes de los otros features
 */
public class NuevoEnvioNormalSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("urlED");
    private PageHomeLoginED pageHomeLoginED = new PageHomeLoginED(driver);
    
    // Páginas de MiCorreo1_5 (reutilizadas para el flujo de envío)
    private PageMessageHome pageMessageHome = new PageMessageHome(driver);
    private PageNuevoEnvio pageNuevoEnvio = new PageNuevoEnvio(driver);
    private PageCheckOut pageCheckOut = new PageCheckOut(driver);
    private PagePayment pagePayment = new PagePayment(driver);
    private PageHomeLogin pageHomeLogin = new PageHomeLogin(driver); // Para logout
    
    // Datos cargados desde CSV
    private Map<String, String> datosEnvio;
    private static final String RUTA_CSV = "src/test/resources/datos_nuevo_envio_ED.csv";
    
    /**
     * Carga los datos desde el CSV al inicio del escenario
     */
    @Given("que el usuario carga los datos desde CSV y está en la página de login")
    public void queElUsuarioCargaLosDatosDesdeCSVYEstaEnLaPaginaDeLogin() {
        // Cargar datos desde CSV
        datosEnvio = CsvDataLoader.obtenerPrimeraFila(RUTA_CSV);
        System.out.println("✓ Datos cargados desde CSV: " + datosEnvio);
        
        driver.get(baseUrl);
    }
    
    @And("el usuario ingresa el correo desde CSV")
    public void elUsuarioIngresaElCorreoDesdeCSV() {
        String email = datosEnvio.get("email");
        if (email == null || email.isEmpty()) {
            throw new AssertionError("✗ El campo 'email' no está presente en el CSV o está vacío");
        }
        pageHomeLoginED.ingresoEmail(email);
        System.out.println("✓ Email ingresado desde CSV: " + email);
    }
    
    @And("el usuario ingresa la contraseña desde CSV")
    public void elUsuarioIngresaLaContrasenaDesdeCSV() {
        String pass = datosEnvio.get("pass");
        if (pass == null || pass.isEmpty()) {
            throw new AssertionError("✗ El campo 'pass' no está presente en el CSV o está vacío");
        }
        pageHomeLoginED.ingresoPassword(pass);
        System.out.println("✓ Contraseña ingresada desde CSV");
    }
    
    @When("el usuario hace clic en el botón Ingresar desde CSV")
    public void elUsuarioHaceClicEnElBotonIngresarDesdeCSV() {
        pageHomeLoginED.btnIngresar();
    }
    
    @When("ingresa en nuevo envío individual desde CSV")
    public void ingresaEnNuevoEnvioIndividualDesdeCSV() {
        pageMessageHome.ingresarANuevoEnvio();
    }
    
    @And("editar el origen del envío individual desde CSV")
    public void editarElOrigenDelEnvioIndividualDesdeCSV() {
        String tipoOrigen = datosEnvio.get("tipoOrigen");
        if (tipoOrigen == null || tipoOrigen.isEmpty()) {
            throw new AssertionError("✗ El campo 'tipoOrigen' no está presente en el CSV o está vacío");
        }
        pageNuevoEnvio.origenDelEnvioIndividualConZonas(tipoOrigen);
        System.out.println("✓ Tipo de origen ingresado desde CSV: " + tipoOrigen);
    }
    
    @And("llena los campos de paquete desde CSV")
    public void llenaLosCamposDePaqueteDesdeCSV() {
        String peso = datosEnvio.get("peso");
        if (peso == null || peso.isEmpty()) {
            throw new AssertionError("✗ El campo 'peso' no está presente en el CSV o está vacío");
        }
        pageNuevoEnvio.caracteristicasDelPaqueteCM(peso);
        System.out.println("✓ Peso ingresado desde CSV: " + peso);
    }
    
    @And("selecciona el tipo de entrega desde CSV y completa el formulario de destino")
    public void seleccionaElTipoDeEntregaDesdeCSVYCompletaElFormularioDeDestino() {
        String tipoEntrega = datosEnvio.get("tipoEntrega");
        if (tipoEntrega == null || tipoEntrega.isEmpty()) {
            throw new AssertionError("✗ El campo 'tipoEntrega' no está presente en el CSV o está vacío");
        }
        pageNuevoEnvio.tipoEntregaZonas(tipoEntrega);
        System.out.println("✓ Tipo de entrega seleccionado desde CSV: " + tipoEntrega);
    }
    
    @And("selecciona el tipo de producto desde CSV y procede a pagar")
    public void seleccionaElTipoDeProductoDesdeCSVYProcedeAPagar() {
        String tipoProducto = datosEnvio.get("tipoProducto");
        if (tipoProducto == null || tipoProducto.isEmpty()) {
            throw new AssertionError("✗ El campo 'tipoProducto' no está presente en el CSV o está vacío");
        }
        pageNuevoEnvio.tipoProducto(tipoProducto);
        System.out.println("✓ Tipo de producto seleccionado desde CSV: " + tipoProducto);
    }
    
    @And("se muestra la grilla de checkout desde CSV")
    public void seMuestraLaGrillaDeCheckoutDesdeCSV() {
        pageCheckOut.validarFormularioCheckout();
    }
    
    @Then("realiza el pago con el medio de pago desde CSV")
    public void realizaElPagoConElMedioDePagoDesdeCSV() {
        String medioPago = datosEnvio.get("medioPago");
        if (medioPago == null || medioPago.isEmpty()) {
            throw new AssertionError("✗ El campo 'medioPago' no está presente en el CSV o está vacío");
        }
        pageCheckOut.medioPago(medioPago);
        System.out.println("✓ Medio de pago seleccionado desde CSV: " + medioPago);
    }
    
    @And("se confirma que el pago se ha realizado con exito desde CSV")
    public void seConfirmaQueElPagoSeHaRealizadoConExitoDesdeCSV() {
        pagePayment.verificarPago();
        pageHomeLogin.logout();
    }
}

