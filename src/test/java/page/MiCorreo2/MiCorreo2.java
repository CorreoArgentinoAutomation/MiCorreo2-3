package page.MiCorreo2;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiCorreo2 extends BasePage {
    public MiCorreo2(WebDriver driver) {
        super(driver);
    }

    private By campoUsr = By.xpath("//input[@formcontrolname='username' and @type='email']");
    private By campoPassword = By.xpath("//input[@formcontrolname='password' and @type='password']");
    private By btnIngresar = By.xpath("//span[contains(@class, 'button-margin-internal') and contains(@class, 'ng-star-inserted') and text()='Ingresar']");
    //=========================================================================================================================================================================
    //Envios
    //=========================================================================================================================================================================
    private By btnNuevoEnvio = By.xpath("//img[@src='../../../assets/img/dashboard/add.svg']");

    private By opcionIndividual = By.xpath("//span[@class='mdc-tab__text-label' and text()='Individual']");
    private By campoName = By.xpath("//input[@formcontrolname='name' and @aria-required='true' and contains(@class, 'mat-mdc-input-element')]");

    private By campoOrigen = By.xpath("(//div[contains(@class, 'mdc-notched-outline')])[1]");
    private By listaProvincias = By.xpath("//mat-form-field[@appearance='outline' and contains(@class, 'mat-mdc-form-field') and contains(@class, 'mat-input-element') and contains(@class, 'ng-tns-c2608167813-7')]");
    private By btnSiguiente = By.xpath("//button[@class='yes']");
    private By medidaLargo = By.xpath("//input[@formcontrolname='length']");
    private By medidaAncho = By.xpath("//input[@formcontrolname='width']");
    private By medidaAlto = By.xpath("//input[@formcontrolname='height']");
    private By medidaPeso = By.xpath("//input[@formcontrolname='weight']");
    private By valor = By.xpath("//input[@formcontrolname='declaredValue']");
    private By nombreYApellido = By.xpath("//input[@formcontrolname='name']");
    private By listaProvincia = By.xpath("//span[contains(@class, 'mat-mdc-select-placeholder') and contains(@class, 'mat-mdc-select-min-line')]");
    private By campoLocalidad = By.xpath("//input[@formcontrolname='location']");
    private By campoDireccion = By.xpath("//input[@formcontrolname='address']");
    private By campoCP = By.xpath("//input[@formcontrolname='zip']");
    private By campoEmail = By.xpath("//input[@formcontrolname='email']");
    private By campoTelefono = By.xpath("//input[@formcontrolname='phone']");
    private By btnPagar = By.xpath("//button[@class='pay']");
    private By pagoSaldo = By.xpath("//label[.='Saldo']");
    private By menuPanelEnvios = By.xpath("//mat-list-item[3]/span[@class='mdc-list-item__content']");
    private By menuPanelEnviosPagados = By.xpath("//h3[.='Pagados']");
    private By btnPagados = By.xpath("//span[contains(@class, 'mdc-tab__text-label') and text()='Pagados']");
    private By tablaPagados = By.xpath("//table[contains(@class, 'mat-mdc-table')]");
    private By btnMicuenta = By.xpath("//p[@class='menu-list-profile-user' and contains(text(), 'Mi cuenta')]");
    private By opcionesConfiguracion = By.xpath("//span[contains(.,'Configuración')]");

    // Configuraciones de la cuenta
    private By campoNombre = By.xpath("//mat-form-field[contains(@class, 'mat-mdc-form-field') and .//mat-label[contains(text(), 'Nombre')]]//input");
    private By campoApellido = By.xpath("(//mat-form-field[contains(@class, 'mat-mdc-form-field') and .//mat-label[contains(text(), 'Apellido')]])[1]//input");
    //private By  = By.xpath("");
    private By campoTelefonoConfiguracion = By.xpath("(//mat-form-field[contains(@class, 'mat-mdc-form-field') and .//mat-label[contains(text(), 'Celular')]])[1]//input");
    private By btnConfirmar = By.xpath("//span[contains(.,'Confirmar')]");

    // Configuraciones de Facturacion
    private By datosDeUsuario = By.xpath("//button[contains(@class, 'item') and contains(text(), 'Datos de usuario')]");
    private By informacionDeLaCuenta = By.xpath("//button[contains(.,'Información de la cuenta')]");
    private By domicilios = By.xpath("//button[contains(@class, 'item') and contains(text(), 'Domicilios')]");
    private By medidasFrecuentes = By.xpath("//button[contains(@class, 'item') and contains(text(), 'Medidas frecuentes')]");
    private By usuariosInvitados = By.xpath("//button[contains(@class, 'item') and contains(text(), 'Usuarios invitados')]");

    // Configuraciones de Domicilios Pick UP
    private By btnPickUp = By.xpath("//span[contains(@class, 'mdc-tab__text-label') and contains(text(), 'Pick up')]");
    private By campoAlias = By.xpath("//input[@formcontrolname='alias' and contains(@class, 'mat-mdc-input-element')]");
    private By campoTelefonoPickUp = By.xpath("//input[@formcontrolname='cellphone' and contains(@class, 'mat-mdc-input-element')]");
    private By campoPropiedad = By.xpath("//input[@formcontrolname='name' and contains(@class, 'mat-mdc-input-element')]");
    private By campoCorreoElectronico = By.xpath("//input[@formcontrolname='email']");
    private By listaProvinciaConfiguracion = By.xpath("//mat-select[@formcontrolname='province' and contains(@class, 'mat-mdc-select')]");
    private By listaFranjaHoraria = By.xpath("//mat-select[@formcontrolname='shift']");
    private By campoLocalidadPU = By.xpath("//input[@formcontrolname='location']");
    private By listaDias = By.xpath("//span[contains(text(), 'LUNES')]");
    private By campoCallePU = By.xpath("//input[@formcontrolname='address']");
    private By campoCodigoPostal = By.xpath("//input[@formcontrolname='cp']");

    // Configuraciones de medidas frecuentes
    private By campoNameMF = By.xpath("//input[@formcontrolname='name']");
    private By campoLargoMF = By.xpath("//input[@formcontrolname='length']");
    private By campoAnchoMF = By.xpath("//input[@formcontrolname='width']");
    private By campoAltoMF = By.xpath("//input[@formcontrolname='height']");
    private By btnGuardarMF = By.xpath("//button[contains(text(),'Guardar')]");

    //Configuracion informacion de la cuenta

    private By informacionCuenta = By.xpath("//button[contains(.,'Informaci')]");
    //private By campoNombreYApellido = By.xpath("//input[@formcontrolname='name']");
    private By campoEmailIC = By.xpath("//input[@formcontrolname='email_address']");
    private By ComboProvincia = By.xpath("//mat-select[@formcontrolname='province']");
    private By opcionBuenosAires = By.xpath("//span[contains(.,'Ciudad Aut')]");
    private By campoCiudad = By.xpath("//input[@formcontrolname='city']");


    //Servicios y Oficios Judiciales


    private By menuServicios = By.xpath("//img[contains(@src, 'dashboard/services.svg') and @aria-label='Servicios']");

    private By subMenuOficios = By.xpath("//h3[contains(.,'Oficios judiciales')]");

    private By listaCamara = By.xpath("//span[.='Cámara Nacional Electoral']");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");


    private By campoCalleIC = By.xpath("//input[@formcontrolname='address_line1']");
    private By campoCodigoPostalPU = By.xpath("//input[@formcontrolname='postal_code']");
    private By msjConfirmacion = By.xpath("//strong[contains(.,'¡Actualizaste tus datos exitosamente!')]");

    //Configuracion de Remitente

    private By listaProvinciaDR = By.xpath("//mat-select[@formcontrolname='province']");
    private By campoLocalidadDR = By.xpath("//input[@formcontrolname='location']");
    private By camnpoDireccionDR = By.xpath("//input[@formcontrolname='address']");
    private By campoCodigoPostalDR = By.xpath("//input[@formcontrolname='cp']");

    //Envios Pendientes

    private By btnEnvios = By.xpath("//img[@src='../../../assets/img/dashboard/local_post_office.svg' and @aria-label='Mis envíos']");

    private By btnEnviosPendientes = By.xpath("//h3[.='Pendientes']");



    public void loginMiCorreo2() {
        String userName = "Automationpi@yopmail.com";
        String password = "123123Aa@";
        writeText(campoUsr, userName);
        writeText(campoPassword, password);
        click(btnIngresar);
    }

    public void tipoDeEnvio() {
        waitForSeconds(1);
        click(btnNuevoEnvio);
        click(opcionIndividual);


    }

    public void tipoDeEntrega(){
        waitForSeconds(1);
        writeText(campoName, "Automation Test");
        click(listaProvincias);
        sendEnter();
        click(btnSiguiente);

        //Configuracion de la medida del paquete
        writeText(medidaLargo, "10");
        writeText(medidaAncho, "10");
        writeText(medidaAlto, "10");
        writeText(medidaPeso, "5");
        writeText(valor, "5");
        click(btnSiguiente);

        //Destino

        writeText(nombreYApellido, "Nombre Apellido");
        click(listaProvincia);
        sendEnter();
        writeText(campoLocalidad, "Aguero");
        writeText(campoDireccion, "2502");
        writeText(campoCP, "1020");
        writeText(campoEmail, "prueba@correo.com");
        writeText(campoTelefono, "3804564576");

        waitForSeconds(4);

        //Opcion de envio
        //click();
        System.out.println("1");
        click(btnPagar);
        System.out.println("2");

/*

        waitForSeconds(3);
        click(pagoSaldo);
        System.out.println("3");

 */
        click(btnSiguiente);



        //Probar recargar
        waitForSeconds(3);
        recargar(3);
        waitForSeconds(10);

    }

    public void validarPago(){
        click(menuPanelEnvios);
        click(menuPanelEnviosPagados);
        waitForSeconds(3);
        click(btnPagados);
        //recargar(3);
        buscarEnTabla(tablaPagados);
    }

    public void configuracionCuenta(){
        click(btnMicuenta);
        waitForSeconds(2);
        /*
        scrollToElement(opcionesConfiguracion);
        click(opcionesConfiguracion);
         */
        sendFlechaAbajo(1);
        sendEnter();
    }

    public void cambioDeDatos(){
        String Nombre = generadorNombresReales();
        String Apellido = generadorApellidosReales();
        //el usuario es el Correo
        //String Usuario = generadornombres();
        String Telefono = generadorNumeroTelefono();

        writeText(campoNombre, Nombre);
        writeText(campoApellido, Apellido);
        //writeText(campoLocalidad, "Aguero");
        writeText(campoTelefonoConfiguracion, Telefono);

        System.out.println("Nombre: "+Nombre);
        System.out.println("Apellido: "+Apellido);
        //System.out.println("Telefono: "+Telefono);
        System.out.println("Telefono: "+Telefono);


        click(btnSiguiente);
        waitForSeconds(1);
        click(btnConfirmar);
    }

    public void cambioInfoCuenta(){

        click(informacionCuenta);

        //Datos de facturacion

        String email = generadorCorreos();
        writeText(campoEmailIC, email);
        System.out.println("Se cambio el Caorreo por: "+email);

        click(ComboProvincia);
        click(opcionBuenosAires);
        writeText(campoCiudad, "Quilmes");

        String calle = "Calle "+numerosAleatorios(4);
        writeText(campoCalleIC, calle);
        System.out.println("Se cambio la Calle por: "+calle);

        String codigoPostal = "1020";
        writeText(campoCodigoPostalPU, codigoPostal);
        System.out.println("Se cambio el codigo postal por: "+codigoPostal);

        click(btnSiguiente);
        waitForSeconds(2);
        click(btnConfirmar);

    }
    public void cambioDomiciliosPickUp(){
        click(domicilios);
        click(btnPickUp);

        writeText(campoAlias, "Automation");
        writeText(campoTelefonoPickUp, generadorNumeroTelefono());
        writeText(campoPropiedad, "Razon Social");

        String email = generadorCorreos();
        writeText(campoCorreoElectronico, email);
        System.out.println("Se cambio el Correo por: "+email);

        click(listaProvinciaConfiguracion);
        sendEnter();

        click(listaFranjaHoraria);
        sendEnter();

        writeText(campoLocalidadPU, "ciudad");

        click(listaDias);
        sendEnter();

        String calle = "Calle "+numerosAleatorios(4);
        writeText(campoCallePU, calle);
        System.out.println("Se cambio la Calle por: "+calle);

        writeText(campoCodigoPostal, "1020");

        click(btnSiguiente);

    }

    public void configuracionMedidasFrecuentes(){
        waitForSeconds(2);

        click(medidasFrecuentes);

        writeText(campoNameMF, "Prueba");
        writeText(campoLargoMF, "21");
        writeText(campoAnchoMF, "22");
        writeText(campoAltoMF, "23");

        click(btnGuardarMF);

    }


    public void validarMsjConfirmacion(){
        compararTextoConMensajeEsperado(msjConfirmacion, "¡Actualizaste tus datos exitosamente!");
    }

    public void serviciosOficiosJudiciales(){
        waitForSeconds(2);

        click(menuServicios);
        click(subMenuOficios);



    }

    public void llenarFormularioOficios(){
        waitForSeconds(2);

        click(listaCamara);



    }


    public void configuracionDomiciliosRemitente(){
        click(domicilios);
        click(listaProvinciaDR);
        sendEnter();

        writeText(campoLocalidadDR, "ciudad");

        writeText(camnpoDireccionDR, "Calle 1234");

        writeText(campoCodigoPostalDR, "1020");

        click(btnSiguiente);


    }

    public void enviosPendientes(){
        click(btnEnvios);
        click(btnEnviosPendientes);
    }
}

