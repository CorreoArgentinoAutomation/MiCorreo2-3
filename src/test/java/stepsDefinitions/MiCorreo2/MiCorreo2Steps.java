package stepsDefinitions.MiCorreo2;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.MiCorreo2.*;


public class MiCorreo2Steps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("urlMiCorreo2");
    private MiCorreo2 miCorreo2 = new MiCorreo2(driver);
    private MiCorreo2Login miCorreo2Login = new MiCorreo2Login(driver);
    private MiCorreo2Home miCorreo2Home = new MiCorreo2Home(driver);
    private MiCorreo2NuevoEnvio miCorreo2NuevoEnvio = new MiCorreo2NuevoEnvio(driver);
    private MiCorreo2Configuraciones miCorreo2Configuraciones = new MiCorreo2Configuraciones(driver);
    private MiCorreo2MisEnvios miCorreo2MisEnvios = new MiCorreo2MisEnvios(driver);
    private MiCorreo2Servicios miCorreo2Servicios = new MiCorreo2Servicios(driver);


    @Given("el usuario {string} se situa en los campos email y password")
    public void elUsuarioSeSituaEnLosCamposEmailYPassword(String arg0) {
        driver.get(baseUrl);
        System.out.println("el usuario se situa en el ambiente de pruebas");
        miCorreo2Login.loginMiCorreo2();
    }

    @And("el usuario realiza un envio {string}")
    public void elUsuarioRealizaUnEnvio(String arg0) {
        miCorreo2Home.tipoDeEnvio();
    }

    @And("el usuario seleciona el tipo de {string}")
    public void elUsuarioSelecionaElTipoDe(String arg0) {
        miCorreo2NuevoEnvio.tipoDeEntrega();
    }

    @And("el usuario valida que se encuentre pagado el envio")
    public void elUsuarioValidaQueSeEncuentrePagadoElEnvio() {
        miCorreo2.validarPago();

    }

    @And("el usuario se dirige a las configuraciones de la cuenta")
    public void el_usuario_se_dirige_a_las_configuraciones_de_la_cuenta() {
        miCorreo2Home.configuracionCuenta();
    }

    @And("el usuario cambia los datos de la cuenta")
    public void elUsuarioCambiaLosDatosDeLaCuenta() {
        miCorreo2Configuraciones.cambioDeDatos();
    }

    @And("el usuario cambia la informacion de la cuenta")
    public void elUsuarioCambiaLaInformacionDeLaCuenta() {
        miCorreo2Configuraciones.cambioInfoCuenta();
    }

    @And("el usuario configura una Pick Up")
    public void elUsuarioConfiguraUnaPickUp() {
        miCorreo2Configuraciones.cambioDomiciliosPickUp();
    }

    @And("el usuario agrega una medida frecuente")
    public void elUsuarioAgregaUnaMedidaFrecuente() {
        miCorreo2Configuraciones.configuracionMedidasFrecuentes();
    }


    @When("el usuario valida el visualiza el popup de confirmacion")
    public void elUsuarioValidaElVisualizaElPopupDeConfirmacion() {
        miCorreo2Configuraciones.validarMsjConfirmacion();
    }

    @And("el usuario configura un remitente")
    public void elUsuarioConfiguraUnRemitente() {
        miCorreo2Configuraciones.configuracionDomiciliosRemitente();
    }

    @And("el usuario se dirige a los envios pendientes")
    public void elUsuarioSeDirigeALosEnviosPendientes() {
        miCorreo2MisEnvios.enviosPendientes();
    }

    @And("el usuario se dirige a servicios y oficios judiciales")
    public void el_usuario_se_dirige_a_servicios_y_oficios_judiciales() {
        miCorreo2Servicios.serviciosOficiosJudiciales();
    }

    @And("el usuario rellena el formulario y guarda el oficio")
    public void el_usuario_rellena_el_formulario_y_guarda_el_oficio() {
        miCorreo2Servicios.llenarFormularioOficios();
    }

    @When("el usuario valida el popup de confirmacion")
    public void el_usuario_valida_el_popup_de_confirmacion() {
        miCorreo2Servicios.confirmacionOficio();
    }

}
