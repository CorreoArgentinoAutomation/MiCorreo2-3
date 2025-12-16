package stepsDefinitions.EcosistemaDigital;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.EcosistemaDigital.PageHomeLoginED;
import page.EcosistemaDigital.PageYopMailED;

public class EcosistemaDigitalSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("urlED");
    private PageHomeLoginED pageHomeLoginED = new PageHomeLoginED(driver);
    private PageYopMailED pageYopMail = new PageYopMailED(driver);

    public String email;
    public String numeroSeguimiento;

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
}
