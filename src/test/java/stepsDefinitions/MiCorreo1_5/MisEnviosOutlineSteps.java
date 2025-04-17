package stepsDefinitions.MiCorreo1_5;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.MiCorreo1_5.*;

public class MisEnviosOutlineSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("url");
    private PageHomeLogin pageHomeLogin = new PageHomeLogin(driver);
    private PageMessageHome pageMessageHome = new PageMessageHome(driver);
    private PageNuevoEnvio pageNuevoEnvio = new PageNuevoEnvio(driver);
    private PageCheckOut pageCheckOut = new PageCheckOut(driver);
    private PageForm pageForm = new PageForm(driver);
    private PagePayment pagePayment = new PagePayment(driver);
    private PageYopMail pageYopMail = new PageYopMail(driver);


    public String email;

    @Given("^el usuario se situa en los campos email y password$")
    public void elUsuarioSeSituaEnLosCamposEmailYPassword() {
        driver.get(baseUrl);
        //pageHomeLogin.assertURL("https://wcpzt.correo.local/MiCorreo/public/");
    }
    @Given("^el usuario '(.*)' está logueado y en la page home$")
    public void elUsuarioEstáLogueadoYEnLaPageHome(String tipoUsuario) {
        pageHomeLogin.loginOutline(tipoUsuario);
    }
    @When("^ingresa en nuevo envío individual$")
    public void ingresaEnNuevoEnvíoIndividual() {
        pageMessageHome.ingresarANuevoEnvio();
    }
    @And("^llena los campos de paquete$")
    public void llenaLosCamposDePaquete() {
        pageNuevoEnvio.caracteristicasDelPaquete();
    }
    @And("selecciona el {string} completa el formulario de destino")
    public void seleccionaElCompletaElFormularioDeDestino(String tipoEntrega) {
        pageNuevoEnvio.tipoEntrega(tipoEntrega);
    }
    @And("selecciona el {string} y procede a pagar")
    public void seleccionaElYProcedeAPagar(String tipoProducto) {
        pageNuevoEnvio.tipoProducto(tipoProducto);
    }
    @And("se muestra la grilla de checkout")
    public void seMuestraLaGrillaDeCheckout() {
        pageCheckOut.validarFormularioCheckout();
    }
    @Then("realiza el pago con {string} del envío")
    public void realizaElPagoConDelEnvío(String medioPago) {
        pageCheckOut.medioPago(medioPago);
    }
    @And("se confirma que el pago se ha realizado con éxito")
    public void seConfirmaQueElPagoSeHaRealizadoConÉxito() {
        pagePayment.verificarPago();
    }

    //Registro nuevo usuario
    @Given("el usuario crea un email temporal")
    public void elUsuarioCreaUnEmailTemporal() {
        driver.get("https://yopmail.com/es/");
        email = pageYopMail.crearMailTemporal();

    }
    @Given("que el usuario ingresa al sitio")
    public void que_el_usuario_ingresa_al_sitio() {
        driver.get(baseUrl);
    }

    @When("el usuario hace clic en el boton Registrarme")
    public void el_usuario_hace_clic_en_el_boton_registrarme() {
        pageHomeLogin.registro();
    }

    @And("el usuario llena el formulario de registro")
    public void el_usuario_llena_el_formulario_de_registro() {
        pageHomeLogin.llenarFormulario(email);
    }

    @And("el usuario abre el servicio de correo electronico")
    public void el_usuario_abre_el_servicio_de_correo_electronico() {
        driver.get("https://yopmail.com/es/");
        pageYopMail.buscarEmailTemporal(email);
    }

    @And("el usuario hace clic en el boton Activa tu usuario")
    public void el_usuario_hace_clic_en_el_boton_activa_tu_usuario() {

    }

    @Then("el usuario deberia ver el mensaje de bienvenida")
    public void el_usuario_deberia_ver_el_mensaje_de_bienvenida() {
    }

    //usuarioInvitado
    @Given("selecciona Mi cuenta")
    public void selecciona_mi_cuenta() { pageHomeLogin.miCuentaUI();
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("el usuario se situa en la pestaña agregar usuarios")
    public void el_usuario_se_situa_en_la_pestaña_agregar_usuarios() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("selecciona un usuario")
    public void selecciona_un_usuario() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("cambia el rol del usuario a {string}")
    public void cambia_el_rol_del_usuario_a(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("muestra un mensaje de rol cambiado correctamente")
    public void muestra_un_mensaje_de_rol_cambiado_correctamente() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
