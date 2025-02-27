package page.MiCorreo2;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiCorreo2Configuraciones extends BasePage {
    public MiCorreo2Configuraciones(WebDriver driver) {
        super(driver);
    }
    private By medidasFrecuentes = By.xpath("//button[contains(@class, 'item') and contains(text(), 'Medidas frecuentes')]");

    // Configuraciones de medidas frecuentes
    private By campoNameMF = By.xpath("//input[@formcontrolname='name']");
    private By campoLargoMF = By.xpath("//input[@formcontrolname='length']");
    private By campoAnchoMF = By.xpath("//input[@formcontrolname='width']");
    private By campoAltoMF = By.xpath("//input[@formcontrolname='height']");
    private By btnGuardarMF = By.xpath("//button[contains(text(),'Guardar')]");

    // Configuraciones de la cuenta
    private By campoNombre = By.xpath("//mat-form-field[contains(@class, 'mat-mdc-form-field') and .//mat-label[contains(text(), 'Nombre')]]//input");
    private By campoApellido = By.xpath("(//mat-form-field[contains(@class, 'mat-mdc-form-field') and .//mat-label[contains(text(), 'Apellido')]])[1]//input");
    //private By  = By.xpath("");
    private By campoTelefonoConfiguracion = By.xpath("(//mat-form-field[contains(@class, 'mat-mdc-form-field') and .//mat-label[contains(text(), 'Celular')]])[1]//input");
    private By btnConfirmar = By.xpath("//span[contains(.,'Confirmar')]");
    private By btnSiguiente = By.xpath("//button[@class='yes']");

    // Configuraciones de Domicilios Pick UP
    private By domicilios = By.xpath("//button[contains(@class, 'item') and contains(text(), 'Domicilios')]");
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

    //Configuracion de Remitente
    private By listaProvinciaDR = By.xpath("//mat-select[@formcontrolname='province']");
    private By campoLocalidadDR = By.xpath("//input[@formcontrolname='location']");
    private By camnpoDireccionDR = By.xpath("//input[@formcontrolname='address']");
    private By campoCodigoPostalDR = By.xpath("//input[@formcontrolname='cp']");

    //Configuracion informacion de la cuenta
    private By informacionCuenta = By.xpath("//button[contains(.,'Informaci')]");
    //private By campoNombreYApellido = By.xpath("//input[@formcontrolname='name']");
    private By campoEmailIC = By.xpath("//input[@formcontrolname='email_address']");
    private By ComboProvincia = By.xpath("//mat-select[@formcontrolname='province']");
    private By opcionBuenosAires = By.xpath("//span[contains(.,'Ciudad Aut')]");
    private By campoCiudad = By.xpath("//input[@formcontrolname='city']");
    private By campoCalleIC = By.xpath("//input[@formcontrolname='address_line1']");
    private By campoCodigoPostalPU = By.xpath("//input[@formcontrolname='postal_code']");
    private By msjConfirmacion = By.xpath("//strong[contains(.,'¡Actualizaste tus datos exitosamente!')]");



    public void configuracionMedidasFrecuentes(){
        waitForSeconds(2);

        click(medidasFrecuentes);

        writeText(campoNameMF, "Prueba");
        writeText(campoLargoMF, "21");
        writeText(campoAnchoMF, "22");
        writeText(campoAltoMF, "23");

        click(btnGuardarMF);

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

    public void configuracionDomiciliosRemitente(){
        click(domicilios);
        click(listaProvinciaDR);
        sendEnter();
        writeText(campoLocalidadDR, "ciudad");
        writeText(camnpoDireccionDR, "Calle 1234");
        writeText(campoCodigoPostalDR, "1020");
        click(btnSiguiente);

    }

    public void cambioInfoCuenta(){
        click(informacionCuenta);

        //Datos de facturacion

        String email = generadorCorreos();
        writeText(campoEmailIC, email);
        System.out.println("Se cambio el Correo por: "+email);

        //Despues Revisar este campo al parecer intercepta el click
        //click(ComboProvincia);
        //click(opcionBuenosAires);
        //sendEnter();

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
    public void validarMsjConfirmacion(){
        compararTextoConMensajeEsperado(msjConfirmacion, "¡Actualizaste tus datos exitosamente!");
    }

}

