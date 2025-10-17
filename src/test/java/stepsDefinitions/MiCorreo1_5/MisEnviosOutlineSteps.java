package stepsDefinitions.MiCorreo1_5;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.MiCorreo1_5.*;

import java.io.IOException;

public class MisEnviosOutlineSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("urlTest");
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
        //pageNuevoEnvio.tipoEntrega(tipoEntrega);//funcion vieja
        pageNuevoEnvio.tipoEntregaZonas(tipoEntrega);
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
        pageHomeLogin.logout();
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

    //usuarioInvitado
    @Given("el usuario se situa en la pestaña agregar usuarios")
    public void el_usuario_se_situa_en_la_pestaña_agregar_usuarios() { pageHomeLogin.agregarUI("Operador con Pago");
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @And("un administrador accede a la gestion de usuarios")
    public void un_administrador_accede_a_la_gestion_de_usuarios() { pageHomeLogin.miCuentaUI();
        // Write code here that turns the phrase above into concrete actions

        
    }
    @And("selecciona al usuario {string}")
    public void seleccionaAlUsuarioTipoDeRol(String tipoDeRol) { pageHomeLogin.agregarUI(tipoDeRol);
    }

    @And("activa la cuenta del usuario")
    public void activaLaCuentaDelUsuario() {
        System.out.println("Falta desarrollar esta parte");
    }

    @Then("muestra un mensaje usuario activado correctamente")
    public void muestraUnMensajeUsuarioActivadoCorrectamente() {
        System.out.println("Mensaje de confirmación: Usuario activado correctamente");
        System.out.println("Falta desarrola esta parte");
    }


    @And("el usuario ingresa la pantalla de Oficios Judiciales")
    public void elUsuarioIngresaLaPantallaDeOficiosJudiciales() {
        pageServicios.menuDeServiciosOficiosJudiciales();
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


    @And("el usuario genera el sello digital para el {string} seleccionado")
    public void elUsuarioGeneraElSelloDigitalParaElProductoSeleccionado(String producto) throws IOException {
        pageFranquicia.generarSelloDigital(producto, numeroSeguimiento);
    }

    @And("editar el {string} del envío individual")
    public void editarElOrigenDelEnvíoIndividual(String origen) {
        pageNuevoEnvio.origenDelEnvioIndividualConZonas(origen);
    }

    @And("el usuario rellena el formulario de oficios con el tipo de camara {string}")
    public void elUsuarioRellenaElFormularioDeOficiosConElTipoDeCamaraTipoCamara(String tipoCamara) {
        pageServicios.formularioOficioJudicial(tipoCamara);
    }


    @When("el usuario hace clic en el boton Recargar saldo")
    public void elUsuarioHaceClicEnElBotonRecargarSaldo() {
        pageHomeLogin.recargarSaldo();
    }


    @And("el usuario seleccionar el {string} y el {string} para la recarga")
    public void elUsuarioSeleccionarElMedioPagoYElValorRecargaParaLaRecarga(String medioPago,String valorRecarga) {
        pageHomeLogin.seleccionarMedioPago(medioPago,valorRecarga);
        pageHomeLogin.logout();
    }

    @And("llena los campos de paquete segun el {string} y las dimensiones")
    public void llenaLosCamposDePaqueteSegunElYLasDimensiones(String codigoMaterial) {
        pageNuevoEnvio.caracteristicasDelPaqueteCM(codigoMaterial);
    }
}
