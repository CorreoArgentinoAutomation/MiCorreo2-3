package stepsDefinitions.MiCorreo2;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.MiCorreo2.MiCorreo2;



public class MiCorreo2Steps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("urlMiCorreo2");
    private MiCorreo2 miCorreo2 = new MiCorreo2(driver);


    @Given("el usuario {string} se situa en los campos email y password")
    public void elUsuarioSeSituaEnLosCamposEmailYPassword(String arg0) {
        driver.get(baseUrl);
        System.out.println("el usuario se situa en el ambiente de pruebas");
        miCorreo2.loginMiCorreo2();
    }

    @And("el usuario realiza un envio {string}")
    public void elUsuarioRealizaUnEnvio(String arg0) {
        miCorreo2.tipoDeEnvio();
    }

    @And("el usuario seleciona el tipo de {string}")
    public void elUsuarioSelecionaElTipoDe(String arg0) {
        miCorreo2.tipoDeEntrega();
    }

    @And("el usuario valida que se encuentre pagado el envio")
    public void elUsuarioValidaQueSeEncuentrePagadoElEnvio() {
        miCorreo2.validarPago();

    }

    @And("el usuario se dirige a las configuraciones de la cuenta")
    public void el_usuario_se_dirige_a_las_configuraciones_de_la_cuenta() {
        miCorreo2.configuracionCuenta();
    }

    @And("el usuario cambia los datos de la cuenta")
    public void elUsuarioCambiaLosDatosDeLaCuenta() {
        miCorreo2.cambioDeDatos();
    }

    @And("el usuario cambia la informacion de la cuenta")
    public void elUsuarioCambiaLaInformacionDeLaCuenta() {
        miCorreo2.cambioInfoCuenta();
    }

    @And("el usuario configura una Pick Up")
    public void elUsuarioConfiguraUnaPickUp() {
        miCorreo2.cambioDomiciliosPickUp();
        System.out.println("Campos faltantes");
    }

    @And("el usuario agrega una medida frecuente")
    public void elUsuarioAgregaUnaMedidaFrecuente() {
        miCorreo2.configuracionMedidasFrecuentes();
    }

<<<<<<< HEAD
    @When("el usuario valida el visualiza el popup de confirmacion")
    public void elUsuarioValidaElVisualizaElPopupDeConfirmacion() {
        miCorreo2.validarMsjConfirmacion();
    }

    @And("el usuario configura un remitente")
    public void elUsuarioConfiguraUnRemitente() {
        miCorreo2.configuracionDomiciliosRemitente();
    }

    @And("el usuario se dirige a los envios pendientes")
    public void elUsuarioSeDirigeALosEnviosPendientes() {
        miCorreo2.enviosPendientes();
=======
    @And("el usuario se dirige a servicios y oficios judiciales")
    public void el_usuario_se_dirige_a_servicios_y_oficios_judiciales() {
        miCorreo2.serviciosOficiosJudiciales();
        
    }

    @And("el usuario rellena el formulario y guarda el oficio")
    public void el_usuario_rellena_el_formulario_y_guarda_el_oficio() {
        miCorreo2.llenarFormularioOficios();



>>>>>>> 5d653f7f0174a0d56b32828edbbeda1460b04f79
    }

    @When("el usuario valida el popup de confirmacion")
    public void elUsuarioValidaElPopupDeConfirmacion() {
        miCorreo2.confirmacionOficio();
    }
}
