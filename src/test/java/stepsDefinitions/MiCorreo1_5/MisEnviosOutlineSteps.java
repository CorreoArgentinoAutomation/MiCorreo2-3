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
    private PageServicios pageServicios = new PageServicios(driver);
    private PageFranquicia pageFranquicia = new PageFranquicia(driver);
    private PageMiniPaqAr pageMiniPaqAr = new PageMiniPaqAr(driver);

    public String email;
    public String numeroSeguimiento;

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

    @Given("el usuario se situa en la pestaña agregar usuarios")
    public void el_usuario_se_situa_en_la_pestaña_agregar_usuarios() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("selecciona Mi cuenta")
    public void selecciona_mi_cuenta() {
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


    @Then("muestra un mensaje usuario activado correctamente")
    public void muestraUnMensajeUsuarioActivadoCorrectamente() {
        
    }

    @And("un usuario existente con estado {string}")
    public void unUsuarioExistenteConEstado(String arg0) {
        
    }

    @When("un administrador accede a la gestión de usuarios")
    public void unAdministradorAccedeALaGestiónDeUsuarios() {
        
    }

    @And("selecciona al usuario")
    public void seleccionaAlUsuario() {
        
    }

    @And("activa la cuenta del usuario")
    public void activaLaCuentaDelUsuario() {
    }

    @And("el usuario ingresa la pantalla de Oficios Judiciales")
    public void elUsuarioIngresaLaPantallaDeOficiosJudiciales() {
        pageServicios.menuDeServiciosOficiosJudiciales();
    }

    @And("el usuario rellena el formulario de oficios")
    public void elUsuarioRellenaElFormularioDeOficios() {
        pageServicios.formularioOficioJudicial();
    }

    @Then("el usuario visualiza el message de oficio creado correctamente")
    public void elUsuarioVisualizaElMessageDeOficioCreadoCorrectamente() {
        pageServicios.msjConfirmacionOficio();
    }

    @And("el usuario realiza el pago con el {string} seleccionado")
    public void elUsuarioRealizaElPagoConElMedioPagoSeleccionado(String medioPago) {
        pageServicios.pagarOficioJudicial(medioPago);
    }

    @Then("el usuario visualiza el mensaje de pago correctamente")
    public void elUsuarioVisualizaElMensajeDePagoCorrectamente() {
        pageServicios.msjConfirmacionPagoRealizado();
    }

    @And("el usuario selecciona y elimina todos los oficios")
    public void elUsuarioSeleccionaYEliminaTodosLosOficios() {
        pageServicios.eliminarTodosLosOficios();
    }

    @Then("el usuario visualiza el mensaje de eliminacion correctamente")
    public void elUsuarioVisualizaElMensajeDeEliminacionCorrectamente() {
        pageServicios.msjConfirmacionEliminado();
    }

    @When("el usuario accede a Punto Correo, Nuevo Pedido")
    public void elUsuarioAccedeAPuntoCorreoNuevoPedido() {
        pageFranquicia.menuPanel();
    }

    @And("el usuario selecciona un {string}, la {string} y presiona Guardar")
    public void elUsuarioSeleccionaUnProductoLaCantidadYPresionaGuardar(String Producto, String Cantidad) {
        pageFranquicia.Producto(Producto, Cantidad);
    }


    @And("el usuario hace clic en Ir a pendientes")
    public void elUsuarioHaceClicEnIrAPendientes() {
        pageFranquicia.pantallaPendientes();
    }

    @And("el usuario selecciona los productos y presiona Cotizar")
    public void elUsuarioSeleccionaLosProductosYPresionaCotizar() {
        pageFranquicia.cotizarPendientes();
    }

    @And("el usuario presiona Generar")
    public void elUsuarioPresionaGenerar() {
        pageFranquicia.generar();
        numeroSeguimiento = pageFranquicia.obtenerNumeroSeguimiento();

    }

    @And("el usuario hace clic en Imprimi los sellos digitales que pagaste aqui")
    public void elUsuarioHaceClicEnImprimiLosSellosDigitalesQuePagasteAqui() {
        pageFranquicia.imprimirSellosDigitales();
    }

    @And("el usuario busca el primer producto generado y hace clic en consultar")
    public void elUsuarioBuscaElPrimerProductoGeneradoYHaceClicEnConsultar() {
        pageFranquicia.escribirNumeroSeguimiento(numeroSeguimiento);
    }

    @And("el usuario accede a Punto Correo Acceso a MiniPaqar")
    public void elUsuarioAccedeAPuntoCorreoAccesoAMiniPaqar() {
        pageMiniPaqAr.irAMiniPaqAr();

    }

    @And("el usuario accede a Imposicion Envios Imposicion Postal")
    public void elUsuarioAccedeAImposicionEnviosImposicionPostal() {
        pageMiniPaqAr.menuImposicionEnvios();
    }

    @And("el usuario llena el formulario de Imposicion Postal y confirma")
    public void elUsuarioLlenaElFormularioDeImposicionPostalYConfirma() {
        pageMiniPaqAr.llenarFormularioImposicionPostal(numeroSeguimiento);
    }

    @Then("el pedido se impone correctamente.")
    public void elPedidoSeImponeCorrectamente() {
        pageMiniPaqAr.validarMsjConfirmacion();
        pageMiniPaqAr.cerrarSesion();

    }


    @Given("el usuario accede a Mi Correo con el usuario Franquicia tipo {int}")
    public void el_usuario_accede_a_mi_correo_con_el_usuario_franquicia_tipo(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        pageHomeLogin.loginOutline("Franquicia tipo 2");
    }
}
