package page.PlataformaIntegracion;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard extends BasePage {

    private By usrlocator = By.xpath("//input[@id='user_login']");
    private By passlocator = By.xpath("//input[@id='user_pass']");
    private By btnLogin = By.xpath("//input[@id='wp-submit']");
    private By lblCorreo = By.xpath("//div[@class='wp-menu-name' and contains(text(),'Correo')]");
    private By SubCorreoArg = By.xpath("//a[@class='wp-first-item' and normalize-space()='Correo Argentino']");
    private By SubConexionApi = By.xpath("//*[@id='toplevel_page_correoargentino-orders']/ul/li[3]/a");
    private By SubDatosComerciales = By.xpath("//*[@id='toplevel_page_correoargentino-orders']/ul/li[4]/a");
    private By myWordpressLocator = By.xpath("//li[@id='wp-admin-bar-site-name']");
    private By btnVisitarSitio = By.xpath("//*[@id='wp-admin-bar-view-site']/a");
    private By btnVisitarTienda = By.xpath("//*[@id='wp-admin-bar-view-store']/a");
    private By numeroPedido = By.xpath("(//span[@class='wc-block-order-confirmation-summary-list-item__value'])[1]");
    private By wooCommerce = By.xpath("//div[@class='wp-menu-name' and normalize-space()='WooCommerce']");
    private By pedidosLocator = By.xpath("//a[normalize-space()='Pedidos']");
    private By dropdownLocator = By.xpath("(//select[@name='bulk-actions-top' and @class='bulk-actions-top'])[1]");
    private By btnAplicar = By.xpath("(//input[@type='submit' and @value='Aplicar'])[1]");
    private By dropdownServicio = By.xpath("(//span[@class='select2-selection__rendered'])[1]");
    private By dropdownCotizador = By.xpath("(//span[@class='select2-selection__rendered'])[2]");
    private By servicioMiCorreo = By.xpath("(//li[@class='select2-results__option'])[1]");
    private By servicioPaqAr = By.xpath("(//li[@class='select2-results__option'])[2]");
    private By opcionCotizadorSI = By.xpath("(//li[@class='select2-results__option'])[1]");
    private By btnGuardarCambios = By.xpath("//button[text()='Guardar los cambios']");
    private By txtUsuarioEnvio = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_email']");
    private By txtContrasenaEnvio = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_password']");
    private By campoNombre = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_business_name']");
    private By campoEmail = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_email']");
    private By campoCiudad = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_city_name']");
    private By campoCalle = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_street_name']");
    private By campoAltura = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_street_number']");
    private By campoCodigoPostal = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_zip_code']");
    private By txtAjustesGuardados = By.xpath("//div [@id='message']//strong[text()='Tus ajustes fueron guardados.']");
    private By menuPlugin = By.xpath(" //div[contains(text(), 'Plugins')]");
    //private By menuPlugin = By.xpath("//div[.='Plugins 4']");
    private By btnDesactivarPlugin = By.xpath("//a[@id='deactivate-correo-argentino-oficial-woocommerce']");
    private By btnActivarPlugin = By.xpath("//a[@id='activate-correo-argentino-oficial-woocommerce']");
    private By txtAcuerdo = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_agreement']");
    private By txtClaveAPI = By.xpath("//textarea[@id='woocommerce_correoargentino_shipping_method_api_key']");
    //Creacion de Usuarios
    private By btnUsuarios = By.xpath("//div[@class='wp-menu-name' and text()='Usuarios']");
    private By btncrearusuario = By.xpath("//a[@href='user-new.php' and contains(text(), 'Añadir nuevo usuario')]");
    private By campoNombreDeUsuario = By.xpath("//input[@id='user_login']");
    private By campoCorreoEletronico = By.xpath("//input[@id='email']");
    private By campoFirstName = By.xpath("//input[@id='first_name']");
    private By campoApellidos = By.xpath("//input[@id='last_name']");
    private By listaPerfil = By.xpath("//select[@name='role' and @id='role']");
    private By perfilGestorDeLaTienda = By.xpath("//select[@name='role']/option[@value='shop_manager']");
    private By perfilCliente = By.xpath("//select[@name='role']/option[@value='customer']");
    private By perfilSuscriptor = By.xpath("//select[@name='role']/option[@value='subscriber']");
    private By perfilColaborador = By.xpath("//select[@name='role']/option[@value='contributor']");
    private By perfilAutor = By.xpath("//select[@name='role']/option[@value='author']");
    private By perfilEditor = By.xpath("//select[@name='role']/option[@value='editor']");
    private By PerfilAdministrador = By.xpath("//select[@name='role']/option[@value='administrator']");
    private By btnAnanirNuevoUsuario = By.xpath("//input[@id='createusersub']");
    private By validacionCreacionUsuario = By.xpath("//p[contains(text(), 'Nuevo usuario creado')]/a");

    //Crear Cuenta Plugin
    private By btnCrearCuentaPlugin = By.xpath("//p[@class='description']//a[contains(text(), 'Crear nueva cuenta')]");
    private By listaTipoDNI = By.xpath("//select[@id='woocommerce_correoargentino_shipping_method_document_type']");
    private By seleccionarTipoDNI = By.xpath("//select[@id='woocommerce_correoargentino_shipping_method_document_type']/option[@value='DNI']");
    private By seleccionarTipoCUIT = By.xpath("//select[@id='woocommerce_correoargentino_shipping_method_document_type']/option[@value='CUIT']");
    private By validarCuentapluginmsj1 = By.xpath("//p[strong[text()='Listo, la cuenta ha sido creada']]");
    private By validarCuentapluginmsj2 = By.xpath("//p[strong[text()='Tus ajustes fueron guardados.']]");

    //llenar formulario de cuenta Plugin

    private By pluginCampoNumeroDocumento = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_document_id']");
    private By pluginCampoFirstName = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_first_name']");
    private By pluginCampoLastName = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_last_name']");
    private By pluginCampoEmail = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_email']");
    private By pluginCampoContrasena = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_password']");
    private By pluginCampoCalle = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_street_name']");
    private By pluginCampoAltura = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_street_number']");
    private By pluginCampoCiudad = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_city_name']");
    private By pluginCampoProvincia = By.xpath("//select[@id='woocommerce_correoargentino_shipping_method_state_code']/option[@value='C']");
    private By pluginCampoCodigoPostal = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_zip_code']");
    private By pluginCampoCelular = By.xpath("//input[@id='woocommerce_correoargentino_shipping_method_cellphone']");

    //Preimponer para Paq.AR

    private By btnWooCommerce = By.xpath("//div[@class='wp-menu-name' and text()='WooCommerce']");
    private By btnPedidosWooCommerce = By.xpath("//a[@href='admin.php?page=wc-orders' and text()='Pedidos']");
    private By btnDashboard = By.xpath("(//a[@class='ab-item' and @role='menuitem' and @href='https://stgwoo.integracionesco.shop/wp-admin/about.php'])[1]");
    private By listaEstadosPedidoCompletado = By.xpath("//select[@id='bulk-action-selector-top']/option[@value='mark_completed']");
    private By listaEstadosPedidoCancelado = By.xpath("//select[@id='bulk-action-selector-top']/option[@value='mark_cancelled']");
    private By checkTodosLosPedidos = By.xpath("//input[@id='cb-select-all-1']");
    private By btnAplicarPedidosPaqAr = By.xpath("//input[@id='doaction']");
    private By validacionCambioDeEstado = By.xpath("//div[@class='updated' and p='Cambiado el estado de 1 pedido.']");

    //Algoritmo de Cotizacion

    private By menuProductos = By.xpath("//div[.='Productos']");
    private By btnAnadirProducto = By.xpath("//div[@class='wrap']/a[.='Añadir nuevo']");
    private By btnAnadirProducto2 = By.xpath("//li[@id='menu-posts-product']//a[.='Añadir un nuevo producto']");//("//li[@id='menu-posts-product']//a[.='Añadir nuevo']");
    private By txtNombreProducto = By.xpath("//input[@id='title']");
    private By txtPrecioProducto = By.xpath("//input[@id='_regular_price']");
    private By menuEnvio = By.xpath("//span[.='Envío']");
    private By txtPeso = By.xpath("//input[@id='_weight']");
    private By txtLongitud = By.xpath("//input[@id='product_length']");
    private By txtAncho = By.xpath("//input[@id='product_width']");
    private By txtAlto = By.xpath("//input[@id='product_height']");
    private By btnPublicarProducto = By.xpath("//input[@id='publish']");
    private By validacionProducto = By.xpath("//p[.='Producto publicado. Ver producto']");

    //borrar productos

    private By checkProductos = By.xpath("//td[@id='cb']/input[1]");
    private By opcionPapelera = By.xpath("//select[@id='bulk-action-selector-top']/option[.='Mover a la papelera']");
    private By btnAplicarProductos = By.xpath("//input[@id='doaction']");
    private By btnPapelera = By.xpath("//li[@class='trash']");
    private By opcionBorrarPermanente = By.xpath("//select[@id='bulk-action-selector-top']/option[.='Borrar permanentemente']");

    private By btncrearProducto = By.xpath("//a[.='Crear producto']");
    private By cerrarTutorial = By.xpath("//button[@class='components-button woocommerce-tour-kit-step-controls__close-btn has-icon']");
    private By msjCrearProducto = By.xpath("//h2[@class='woocommerce-BlankState-message']");

    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");


    public Dashboard(WebDriver driver) {
        super(driver);
    }


    public void verificarUsrAndPass() {
        // Verifica la existencia del campo de usuario
        boolean usrExists = validarCampoExistente(usrlocator);
        // Verifica la existencia del campo de contrase�a
        boolean passExists = validarCampoExistente(passlocator);

        // Imprime mensajes en la consola sobre la existencia de los campos
        if (usrExists && passExists) {
            System.out.println("Los campos de usuario y contrase�a existen.");
        } else if (usrExists) {
            System.out.println("El campo de usuario existe, pero el campo de contrase�a no.");
        } else if (passExists) {
            System.out.println("El campo de contrase�a existe, pero el campo de usuario no.");
        } else {
            System.out.println("Ni el campo de usuario ni el campo de contrase�a existen.");
        }
    }


    public void login() {

//      writeText(usrlocator,"giulliana123@yopmail.com");
        writeText(usrlocator, "admin");
        writeText(passlocator, "{0n[VK*{+wR?");
        click(btnLogin);
        posicionarCursorEnElemento(lblCorreo);

    }

    public void validarSubMenu() {
        validarCampoExistente(SubCorreoArg);
        validarCampoExistente(SubConexionApi);
        validarCampoExistente(SubDatosComerciales);

        // Verifica la existencia del submenu Correo Argentino
        boolean SubmenuSubCorreoArg = validarCampoExistente(SubCorreoArg);
        // Verifica la existencia del submenu ConexionApi
        boolean SubmenuSubConexionApi = validarCampoExistente(SubConexionApi);
        // Verifica la existencia del Submenu Datos Comerciales
        boolean SubmenuSubDatosComerciales = validarCampoExistente(SubDatosComerciales);

        // Imprime mensajes en la consola sobre la existencia de los campos
        if (SubmenuSubCorreoArg && SubmenuSubConexionApi && SubmenuSubDatosComerciales) {
            System.out.println("Los Elementos buscados exiten.");
        } else if (SubmenuSubCorreoArg) {
            System.out.println("El campo de usuario existe, pero el campo de Correo Arg no.");
        } else if (SubmenuSubConexionApi) {
            System.out.println("El campo de contrase�a existe, pero el campo de Conexion APi no.");
        } else if (SubmenuSubDatosComerciales) {
            System.out.println("El campo de contrase�a existe, pero el campo de Datos Comerciales no.");
        } else {
            System.out.println("Los Elementos buscados no exiten.");
        }
    }

    public void ingresarEcommerce() {

        posicionarCursorEnElemento(myWordpressLocator);
        validarCampoExistente(btnVisitarSitio);
        validarCampoExistente(btnVisitarTienda);


        // Verifica la existencia del submenu Visitar Sitio
        boolean SubmenuSitio = validarCampoExistente(btnVisitarSitio);
        // Verifica la existencia del submenu Visitar Tienda
        boolean SubmenuTienda = validarCampoExistente(SubConexionApi);

        // Imprime mensajes en la consola sobre la existencia de los campos
        if (SubmenuSitio && SubmenuTienda) {
            System.out.println("Los Elementos buscados exiten.");
        } else if (SubmenuSitio) {
            System.out.println("El campo de usuario existe, pero el campo de Visitar Sitio no.");
        } else if (SubmenuTienda) {
            System.out.println("El campo de contrase�a existe, pero el campo de Visitar Tienda no.");
        } else {
            System.out.println("Los Elementos buscados no exiten.");
        }

        waitForSeconds(2);
        click(myWordpressLocator);
        System.out.println("Ingreso correcto al Ecommerce");
    }

    public String importarPedido() {
        String Pedido = extraerNumeros(numeroPedido);
        click(myWordpressLocator);
        posicionarCursorEnElemento(lblCorreo);
        waitForSeconds(2);
        click(By.xpath("//a[contains(text(),'Correo Argentino')]"));
        // Generar el XPath dinámico concatenando el número de pedido al XPath deseado
        String xpathPedido = "//input[@value='" + Pedido + "']";
        // Hacer clic en el elemento generado utilizando el XPath dinámico
        click(By.xpath(xpathPedido));
        waitForSeconds(2);
        seleccionarOpcionPorValor(dropdownLocator, "importar");
        waitForSeconds(1);
        click(btnAplicar);
        waitForSeconds(5);
        return Pedido;
    }

    //flujo WooLoginMiCorreo
    //1 - Ingresar al Dashboard de de wordPress
    public void ingresarAWordPres() {
        writeText(usrlocator, "automationpi@yopmail.com");
        writeText(passlocator, "xZK06VRB3pEXgZtk2tBRnb78");
        click(btnLogin);
    }

    //2 - Validar que solo alla 2 opciones "Correo Argentino" y "Conexion Api"
    public void validarSubMenuSinLogin() {
        posicionarCursorEnElemento(lblCorreo);
        validarCampoExistente(SubCorreoArg);
        validarCampoExistente(SubConexionApi);

        // Verifica la existencia del submenu Correo Argentino
        boolean SubmenuSubCorreoArg = validarCampoExistente(SubCorreoArg);
        // Verifica la existencia del submenu ConexionApi
        boolean SubmenuSubConexionApi = validarCampoExistente(SubConexionApi);

        // Imprime mensajes en la consola sobre la existencia de los campos
        if (SubmenuSubCorreoArg && SubmenuSubConexionApi) {
            System.out.println("Los Elementos buscados exiten.");
        } else if (SubmenuSubCorreoArg) {
            System.out.println("El campo de Correo Arg no existe.");
        } else if (SubmenuSubConexionApi) {
            System.out.println("El Submenu Correo Argentino exite, pero el campo de Conexion APi no.");
        } else {
            System.out.println("Los Elementos buscados no exiten.");
        }
    }

    //3 - Clic en el boton "Conexion Api"
    public void ingresarAConexionApi() {
        posicionarCursorEnElemento(lblCorreo);
        click(SubConexionApi);
    }

    //4 - clic en la lista desplegable Servicio
    public void seleccionarServicio(String servicio) {
        click(dropdownServicio);


        switch (servicio) {
 case "Mi Correo":
     seleccionoMiCorreo();
 break;
 case "PaqAr":
     seleccionoPaqAr();
 break;
default:
    System.out.println("No Esta definido ese servicio: " + servicio + "no existe");
 break;
        }

    }

    //5 - seleccionar un servicio Mi Correo
    public void seleccionoMiCorreo() {
        waitForSeconds(2);
        click(servicioMiCorreo);
    }

    public void seleccionoPaqAr() {
        click(servicioPaqAr);
    }

    //6 - clic en la lista desplegable ¿Querés usar el cotizador de Correo Argentino?
    public void seleccionarCotizador() {
        click(dropdownCotizador);
    }

    //7 - Clic en la opcion "Si"
    public void seleccionarOpcionCotizador() {
        waitForSeconds(2);
        click(opcionCotizadorSI);
        //Existe un comportamiento variable en el que se necesitaria llamar estos 2 pasos
        //seleccionarServicio();
        //seleccionoMiCorreo();
    }

    //8 - clic en el boton guardar cambios
    public void guardarCambios() {
        waitForSeconds(1);
        click(btnGuardarCambios);
    }

    //9 - Ingresar el user y pass
    public void seleccionoUsuario(String tipoUsuario) {
        switch (tipoUsuario){
            case "Consumidor final":
                consumirFinalLogin();
                break;
            case "Monotributista":
                monotributoLogin();
                break;
            case "Empresa":
                empresaLogin();
                break;
            case "PaqAr":
                ingresoDeAcuerdoYClaveAPI();
                break;
            default:
                System.out.println("No Esta definido ese usuario: " + tipoUsuario + "no existe");
        }
    }
    public void consumirFinalLogin() {
        click(txtUsuarioEnvio);
        writeText(txtUsuarioEnvio, "Automationpi@yopmail.com");
        waitForSeconds(1);
        click(txtContrasenaEnvio);
        writeText(txtContrasenaEnvio, "123123");
        click(btnGuardarCambios);
    }
    public void monotributoLogin() {
        click(txtUsuarioEnvio);
        writeText(txtUsuarioEnvio, "mono_tester@yopmail.com");
        waitForSeconds(1);
        click(txtContrasenaEnvio);
        writeText(txtContrasenaEnvio, "123123");
        click(btnGuardarCambios);
    }
    public void empresaLogin() {
        click(txtUsuarioEnvio);
        writeText(txtUsuarioEnvio, "empctacte_test@yopmail.com");
        waitForSeconds(1);
        click(txtContrasenaEnvio);
        writeText(txtContrasenaEnvio, "123123");
        click(btnGuardarCambios);
    }


    //10 - Llenar en el formulario los datos de negocio
    public void llenarFormularioDeNegocio() {
        click(campoNombre);
        writeText(campoNombre, "Prueba");
        click(campoEmail);
        //sendBorrar(25);
        writeText(campoEmail, "Automationpi@yopmail.com");
        click(campoCiudad);
        writeText(campoCiudad, "CABA");
        click(campoCalle);
        writeText(campoCalle, "calle");
        click(campoAltura);
        writeText(campoAltura, "12345");
        click(campoCodigoPostal);
        writeText(campoCodigoPostal, "1020");
        click(btnGuardarCambios);
    }

    //11 - Validar mensaje de guardado de ajuste
    public void validarMensajeGuardado() {
        compararTextoConMensajeEsperado(txtAjustesGuardados, "Tus ajustes se han guardado.");
        System.out.println("Tus ajustes se han guardado.");
    }

    //Para generar un pedido rapido
    public void generarPedidoDashboard() {
        login();
        validarSubMenu();
        ingresarEcommerce();
    }

    public String importarPedidoInsumo() {
        String Pedido = extraerNumeros(numeroPedido);
        click(myWordpressLocator);
        waitForSeconds(2);
        click(By.xpath("//a[contains(text(),'Correo Argentino')]"));
        // Generar el XPath dinámico concatenando el número de pedido al XPath deseado
        String xpathPedido = "//input[@value='" + Pedido + "']";
        // Hacer clic en el elemento generado utilizando el XPath dinámico
        click(By.xpath(xpathPedido));
        waitForSeconds(5);
        return Pedido;
    }

    //Funciones para Desactivar y Activar el pluging
    //1 Hacer clic en el boton plugin
    public void irAlMenuPlugin() {
        click(menuPlugin);
    }

    //2 Hacer clic en el boton desactivar
    public void desactivarPlugin() {
        waitForSeconds(2);
        click(btnDesactivarPlugin);
    }

    //3 Hacer clic en el boton activar
    public void activarPlugin() {
        waitForSeconds(2);
        click(btnActivarPlugin);
    }

    public void ingresoDeAcuerdoYClaveAPI() {
        click(txtAcuerdo);
        writeText(txtAcuerdo, "18018");
        waitForSeconds(1);
        click(txtClaveAPI);
        writeText(txtClaveAPI, "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQcnVlYmFzIFRJQVJHIChObyB0b2NhcikiLCJDTEFJTV9UT0tFTiI6IlBFUk1JU1NJT05fREVGQVVMVCIsImlhdCI6MTcxMjYwNzAyNCwiaXNzIjoiSVNTVUVSIn0.qE28CKfSGj63iWOS_AUxGhjtTvVYpvTbRyuePj1Heuo");
        click(btnGuardarCambios);
    }

    //Funciones para Crear Usuario
    //1 - Seleccionar menu Usuarios
    public void selecionarMenuUsarios() {
        click(btnUsuarios);
    }

    //2 - Hacer un clic en el boton Crear Usuario
    public void crearUsuario() {
        click(btncrearusuario);
    }

    //3 - llenar formulario
    public void llenarFormularioCreacionUsuario() {
        click(campoNombreDeUsuario);
        writeText(campoNombreDeUsuario, generadornombres());
        String email = generadorCorreos();
        click(campoCorreoEletronico);
        writeText(campoCorreoEletronico, email);
        System.out.println("" + email);

        click(listaPerfil);

    }

    //4 - Seleccionar perfil
    public void seleccionarPerfilGestorDeLaTienda() {
        click(perfilGestorDeLaTienda);
    }

    public void seleccionarPerfilCliente() {
        click(perfilCliente);
    }

    public void seleccionarPerfilSuscritor() {
        click(perfilSuscriptor);
    }

    public void seleccionarPerfilColaborador() {
        click(perfilColaborador);
    }

    public void seleccionarPerfilAutor() {
        click(perfilAutor);
    }

    public void seleccionarPerfilEditor() {
        click(perfilEditor);
    }

    public void seleccionarPerfilAdministrador() {
        click(PerfilAdministrador);
    }


    //5 - Hacer un clic en el boton anadir nuevo usuario
    public void hacarClicEnAnadirNuevoUsuario() {
        click(btnAnanirNuevoUsuario);
    }

    //6 - Validar que el usuario se ha creado
    public void validarUsuarioCreado() {
        compararTextoConMensajeEsperado(validacionCreacionUsuario, "Nuevo usuario creado.");
        System.out.println("Nuevo usuario creado.");
    }

    //Crear Cuenta Plugin
    //1 - Dar Clic en el boton Crea una cuenta
    public void darClicEnCreaUnaCuenta() {
        click(btnCrearCuentaPlugin);
    }

    //2 - Seleccionar la lista
    public void seleccionarLista() {
        //se necesita cambiar el foco de la ventana
        switchToNewTab2();

        System.out.println("se cambio el foco a la nueva ventana");

        click(listaTipoDNI);
    }

    //3 - Seleccionar DNI
    public void seleccionarDNI() {
        click(seleccionarTipoDNI);
        click(pluginCampoNumeroDocumento);
        writeText(pluginCampoNumeroDocumento, numerosAleatorios(8));
    }

    //3 - Seleccionar CUIT
    public void seleccionarCUIT() {
        click(seleccionarTipoCUIT);
        click(pluginCampoNumeroDocumento);
        writeText(pluginCampoNumeroDocumento, "30593315157");
    }

    public void llenarFormularioPlugin() {
        //click(pluginCampoNumeroDocumento);
        //writeText(pluginCampoNumeroDocumento,"123456789");

        String nombre = generadorNombresReales();
        click(pluginCampoFirstName);
        writeText(pluginCampoFirstName, nombre);

        String apellidos = generadorApellidosReales();
        click(pluginCampoLastName);
        writeText(pluginCampoLastName, apellidos);

        String email = generadorCorreos();
        //String email2 = "empctacte_test@yopmail.com";

        click(pluginCampoEmail);
        writeText(pluginCampoEmail, email);
        System.out.println("" + email);

        waitForSeconds(1);
        scrollPageUpDown(0, 1);
        waitForSeconds(1);

        String pass = "123123";

        click(pluginCampoContrasena);
        writeText(pluginCampoContrasena, pass);
        System.out.println("La conseña del user creado es: " + pass);

        waitForSeconds(1);
        click(pluginCampoCalle);
        writeText(pluginCampoCalle, "calle");
        click(pluginCampoAltura);
        writeText(pluginCampoAltura, "1234");
        click(pluginCampoCiudad);
        writeText(pluginCampoCiudad, "CABA");
        click(pluginCampoProvincia);
        click(pluginCampoCodigoPostal);
        writeText(pluginCampoCodigoPostal, "1020");
        click(pluginCampoCelular);
        writeText(pluginCampoCelular, "12345678");
        click(btnGuardarCambios);

    }

    //Hacer la validacion de que se creo la cuenta
    //5 - Valido que la cuenta en el plugin se alla creado
    public void validarCuentaPlugin() {
        compararTextoConMensajeEsperado(validarCuentapluginmsj1, "Listo, la cuenta ha sido creada");
        compararTextoConMensajeEsperado(validarCuentapluginmsj2, "Tus ajustes fueron guardados.");
    }


    //1 - Me Dirijo Al Dashboard
    public void irAlDashboard() {
        click(btnDashboard);
    }

    //2 - Posiciono el mouse arriba del boton de WooCommerce
    public void pocicionarCursorEnWooCommerce() {
        posicionarCursorEnElemento(btnWooCommerce);
    }

    //3 - Hacer clic en Pedido
    public void irAPedidos() {
        click(btnPedidosWooCommerce);
    }

    //4 - Seleccionar la casilla del ultimo pedido realizado
    public void seleccionarPedido() {

    }

    public void cambiarDeEstadoPedidoPaqArCompletado() {
        click(listaEstadosPedidoCompletado);
    }

    public String importarPedidoPaqAr() {
        //paso Previo
        String Pedido = extraerNumeros(numeroPedido);
        //paso 1
        click(myWordpressLocator);
        //paso2
        posicionarCursorEnElemento(btnWooCommerce);
        waitForSeconds(2);
        //paso 3
        click(btnPedidosWooCommerce);
        //click(By.xpath("//a[contains(text(),'Correo Argentino')]"));
        // Generar el XPath dinámico concatenando el número de pedido al XPath deseado
        String xpathPedido = "//input[@value='" + Pedido + "']";
        // Hacer clic en el elemento generado utilizando el XPath dinámico
        click(By.xpath(xpathPedido));
        waitForSeconds(2);

        cambiarDeEstadoPedidoPaqArCompletado();
        //click(listaEstadosPedidoCompletado);
        //seleccionarOpcionPorValor(dropdownLocator, "importar");
        waitForSeconds(1);
        click(btnAplicarPedidosPaqAr);
        waitForSeconds(5);
        return Pedido;
    }

    public void cambiarDeEstadoPedidoPaqArCancelado() {
        click(listaEstadosPedidoCancelado);
    }

    public String importarPedidoPaqArCancelar(){
        //paso Previo
        String Pedido = extraerNumeros(numeroPedido);
        //paso 1
        click(myWordpressLocator);
        //paso2
        posicionarCursorEnElemento(btnWooCommerce);
        waitForSeconds(2);
        //paso 3
        click(btnPedidosWooCommerce);
        //click(By.xpath("//a[contains(text(),'Correo Argentino')]"));
        // Generar el XPath dinámico concatenando el número de pedido al XPath deseado
        String xpathPedido = "//input[@value='" + Pedido + "']";
        // Hacer clic en el elemento generado utilizando el XPath dinámico
        click(By.xpath(xpathPedido));
        waitForSeconds(2);

        cambiarDeEstadoPedidoPaqArCancelado();
        waitForSeconds(1);
        click(btnAplicarPedidosPaqAr);
        waitForSeconds(5);
        return Pedido;
    }


    public void cambiarDeEstadoPedidoPaqArCanceladoMasivo() {
        click(myWordpressLocator);
        posicionarCursorEnElemento(btnWooCommerce);
        waitForSeconds(2);
        click(btnPedidosWooCommerce);
        click(checkTodosLosPedidos);
        waitForSeconds(1);
        cambiarDeEstadoPedidoPaqArCancelado();
        click(btnAplicarPedidosPaqAr);
    }
    public void cambiarDeEstadoPedidoPaqArCompletadoMasivo() {
        click(myWordpressLocator);
        posicionarCursorEnElemento(btnWooCommerce);
        waitForSeconds(2);
        click(btnPedidosWooCommerce);
        click(checkTodosLosPedidos);
        waitForSeconds(1);
        cambiarDeEstadoPedidoPaqArCompletado();
        click(btnAplicarPedidosPaqAr);
    }

    public String importarPedidoPaqArCancelarMasivo(){
        //paso Previo
        String Pedido = extraerNumeros(numeroPedido);
        //paso 1
        click(myWordpressLocator);
        //paso2
        posicionarCursorEnElemento(btnWooCommerce);
        waitForSeconds(2);
        //paso 3
        click(btnPedidosWooCommerce);
        //click(By.xpath("//a[contains(text(),'Correo Argentino')]"));
        // Generar el XPath dinámico concatenando el número de pedido al XPath deseado
        String xpathPedido = "//input[@value='" + Pedido + "']";
        // Hacer clic en el elemento generado utilizando el XPath dinámico
        click(By.xpath(xpathPedido));
        waitForSeconds(2);

        cambiarDeEstadoPedidoPaqArCanceladoMasivo();
        waitForSeconds(1);
        click(btnAplicarPedidosPaqAr);
        waitForSeconds(5);
        return Pedido;
    }


    public void validarCambioDeEstado(){
        compararTextoConMensajeEsperado(validacionCambioDeEstado,"Cambiado el estado de 1 pedido.");
        System.out.println("Cambiado el estado de 1 pedido.");
    }
    public void validarCambioDeMensajeDeConfirmacion(){
        validarCampoExistente(validacionCambioDeEstado);
        System.out.println("Cambio de estado de todos los pedidos");
    }

    //Algoritmo de cotizacion
    //1 scenario outline para crear los paquetes con las medidas y peso que se quiera
    //Flujo para configurar un producto
    //Desde el dashboard hacer clic
    //1 - clic en el boton productos
    //2 - clic en el boton añadir nuevo
    //3 - Escribir en el nombre del "producto"
    //4 - Hacer un sroll hacia abajo y escribir en precio 1
    //5 - hacer clic en el boton Envio
    //6 - Ingresar el "Peso"
    //7 - Ingresar las dimenciones "Longitud","Ancho","Alto"
    //8 - Hacer clic en publicar
    public void ingresarAProductos(){
        click(menuProductos);
    }
    public void anadirProducto(){
        click(btnAnadirProducto);
    }
    public void nombreProducto(String nombre){
        writeText(txtNombreProducto, nombre);
        System.out.println("\n--------------------------------");
        System.out.println("Datos del nuevo producto: ");
        System.out.println("--------------------------------");
        System.out.println("Nombre: "+ nombre);
    }
    public void precioProducto(String precio){
        click(txtPrecioProducto);
        writeText(txtPrecioProducto, precio);
        System.out.println("Precio: "+ precio);
    }
    public void menuEnvio(){
        click(menuEnvio);
    }

    public void pesoProducto(String peso){
        click(txtPeso);
        writeText(txtPeso, peso);
        System.out.println("peso: "+ peso +" kg");
    }
    public void logitudProducto(String longitud){
        click(txtLongitud);
        writeText(txtLongitud, longitud);
        System.out.println("longitud: "+ longitud +" cm");
    }
    public void anchoProducto(String ancho){
        click(txtAncho);
        writeText(txtAncho, ancho);
        System.out.println("longitud: "+ ancho +" cm");
    }
    public void altoProducto(String alto){
        click(txtAlto);
        writeText(txtAlto, alto);
        System.out.println("alto: "+ alto +" cm");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
    }
    public void publicarProducto(){
        waitForSeconds(1);
        click(btnPublicarProducto);
    }
    public void validarProducto(){
        compararTextoConMensajeEsperado(validacionProducto,"Producto publicado. Ver producto");
        System.out.println("Producto publicado.");
    }



    public void crearProductos(String nombreProducto){
        waitForSeconds(1);
        click(menuProductos);
        condicionalDeFunciones(btncrearProducto, this::borrarProductos, () -> seleccionoProducto(nombreProducto));
        //condicionalDeFunciones(btncrearProducto, borrarProductos(), seleccionoProducto(nombreProducto));
        //ejecutarCondicional(btncrearProducto, seleccionoProducto(), borrarProductos());

    }

    //Seleccionar el producto a crear


    public void seleccionoProducto(String nombreProducto) {
        //waitForSeconds(1);
        //click(menuProductos);
        //borrarProductos();


        //condicionalDeFunciones(btncrearProducto, this::borrarProductos, () -> publicarProductos("ProdQA200", "1", "50", "100", "80", "10"));

        /*
        publicarProductos("Producto 1", "1", "1", "1", "1", "1");
        click(menuProductos);
        System.out.println("se puede ejecutar aqui");
        borrarProductos();
        System.out.println("todos los productos fueron borrados");
*/

        System.out.println("se puede ejecutar aqui");
        switch (nombreProducto) {
            case "paquete1":
                publicarProductos("ProdQA1", "1", "50", "100", "80", "10");
                break;
            case "paquete2":
                publicarProductos("ProdQA2", "1", "1", "10", "10", "10");
                break;
            case "paquete3":
            {
                publicarProductos("ProdQA3", "1", "1", "50", "20", "70");
                publicarProductos("ProdQA4", "1", "1", "70", "50", "20");
            }
            break;
            case "paquete4":
            {
                publicarProductos("ProdQA5", "1", "1", "30", "90", "70");
                publicarProductos("ProdQA6", "1", "1", "10", "75", "80");
            }
            break;
            case "paquete5":
            {
                publicarProductos("ProdQA7", "1", "1", "50", "70", "20");
                publicarProductos("ProdQA8", "1", "1", "70", "20", "50");
                publicarProductos("ProdQA9", "1", "1", "35", "45", "10");
            }
            break;
            case "paquete6":
            {
                publicarProductos("ProdQA4", "1", "1", "70", "50", "20");
                publicarProductos("ProdQA44", "1", "1", "70", "50", "20");
                publicarProductos("ProdQA10", "1", "1", "35", "55", "10");
            }
            break;
            case "paquete7":
            {
                publicarProductos("ProdQA7", "1", "1", "50", "70", "20");
                publicarProductos("ProdQA8", "1", "1", "70", "20", "50");
                publicarProductos("ProdQA4", "1", "1", "70", "50", "20");
            }
            break;
            case "paquete8":
            {
                publicarProductos("ProdQA11", "1", "1", "50", "60", "10");
                publicarProductos("ProdQA12", "1", "1", "20", "40", "5");
                publicarProductos("ProdQA13", "1", "1", "10", "15", "10");
            }
            break;
            case "paquete9":
            {
                publicarProductos("ProdQA7", "1", "1", "50", "70", "20");
                publicarProductos("ProdQA77", "1", "1", "50", "70", "20");
                publicarProductos("ProdQA14", "1", "1", "75", "45", "10");
                publicarProductos("ProdQA15", "1", "1", "65", "55", "10");
            }
            break;
            case "paquete10":
            {
                publicarProductos("ProdQA4", "1", "1", "70", "50", "20");
                publicarProductos("ProdQA9", "1", "1", "35", "45", "10");
                publicarProductos("ProdQA10", "1", "1", "35", "55", "10");
                publicarProductos("ProdQA11", "1", "1", "50", "60", "10");
                publicarProductos("ProdQA16", "1", "1", "100", "50", "20");
            }
            break;
            case "paquete11": {
                publicarProductos("ProdQA17", "1", "1", "201", "50", "35");
                publicarProductos("ProdQA18", "1", "1", "90", "30", "10");
            }
            break;
            case "paquete12":
            {
                publicarProductos("ProdQA19", "1", "50", "20", "20", "20");
                publicarProductos("ProdQA2", "1", "1", "10", "10", "10");
            }
            default:
                System.out.println("No Esta definido ese producto: " + nombreProducto + "no existe");
            break;
        }

    }



    public void publicarProductos(String nombre, String precio, String peso, String longitud,String ancho, String alto){

        //click(menuProductos);

        click(btnAnadirProducto2);

        writeText(txtNombreProducto, nombre);
        System.out.println("\n--------------------------------");
        System.out.println("Datos del nuevo producto: ");
        System.out.println("--------------------------------");
        System.out.println("Nombre: "+ nombre);

        click(txtPrecioProducto);
        writeText(txtPrecioProducto, precio);
        System.out.println("Precio: "+ precio);

        click(menuEnvio);

        click(txtPeso);
        writeText(txtPeso, peso);
        System.out.println("peso: "+ peso +" kg");

        click(txtLongitud);
        writeText(txtLongitud, longitud);
        System.out.println("longitud: "+ longitud +" cm");

        click(txtAncho);
        writeText(txtAncho, ancho);
        System.out.println("ancho: "+ ancho +" cm");

        click(txtAlto);
        writeText(txtAlto, alto);
        System.out.println("alto: "+ alto +" cm");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");

        waitForSeconds(1);
        click(btnPublicarProducto);
    }

    //borrar todos los productos
    //1 - hacer clic en el boton productos
    //2 - hacer clic en el check para seleccionar todos los productos disponibles - //td[@id='cb']/input[1]
    //3 - seleccionar la opcion mover a la papelera - //select[@id='bulk-action-selector-top']/option[.='Mover a la papelera']
    //4 - hacer un clic en el boton Aplicar - //input[@id='doaction']
    //5 - ingresar a la papelera - //li[@class='trash']
    //6 - seleccionar todos los productos disponibles
    //7 - seleccionar la opcion borrar permanentemente - //select[@id='bulk-action-selector-top']/option[.='Borrar permanentemente']
    //8 - hacer un clic en el boton Aplicar


    public void borrarProductos(){

        click(checkProductos);
        click(opcionPapelera);
        click(btnAplicarProductos);

        click(btnPapelera);
        click(checkProductos);
        click(opcionBorrarPermanente);
        click(btnAplicarProductos);


    }



}
