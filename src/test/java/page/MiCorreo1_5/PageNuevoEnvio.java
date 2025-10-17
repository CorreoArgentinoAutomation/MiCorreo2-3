package page.MiCorreo1_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.BasePage;

public class PageNuevoEnvio extends BasePage {
    private By nuevoEnvioLocator = By.xpath("//li[@class='nav-item px-2 centrarDiv']");
    private By campoLargoLocator = By.xpath("//input[@id='largo']");
    private By campoAnchoLocator = By.xpath("//input[@id='ancho']");
    private By campoAltoLocator = By.xpath("//input[@id='alto']");
    private By campoPesoLocator = By.xpath("//input[@id='peso']");
    private By campoValorDelContenido = By.xpath("//input[@id='valorContenido']");
    public By btnNextLocator = By.xpath("//button[@id='next']");
    private By tipoDeEntrega = By.xpath("//select[@id='tipoEntrega']");
    private By entregaDomicilio = By.xpath("//option[@value='domicilio']");
    private By entregaSucursal = By.xpath("//option[@value='sucursal']");
    private By nomApellidoLocator = By.xpath("//input[@id='nars']");
    private By nomApellidoLocatoSuc = By.xpath("//input[@id='nars2']");
    private By seleccionarProvOrigen = By.xpath("//select[@name='sucursalProvinciaOrigen' and @id='sucursalProvinciaOrigen']");
    private By seleccionarProvOrigenSuc = By.xpath("//select[@id='provincia2']");
    private By provinciaCatamarca = By.xpath("//select[@name='sucursalProvinciaOrigen']//option[@value='K']");
    private By seleccionarProvincia = By.xpath("//select[@name='provincia' and @id='provincia']");
    private By provinciaCordoba = By.xpath("(//*[text() = 'CORDOBA'])[2]");
    private By provinciaCordobaDest = By.xpath("//select[@name='provincia2']/option[7]");
    private By correoElectronicoSuc = By.xpath("//input[@id='correoElectronico2']");
    private By celularSuc = By.xpath("//input[@id='celular2']");
    private By getSeleccionarLocalidadSuc = By.xpath("//select[@id='sucursalDestino2']");
    private By seleccionarLocalidadOrigen = By.xpath("//select[@name='sucursalOrigen'and@id='sucursalOrigen']");
    private By localidadBelen = By.xpath("//option[@value='K0006' and @class='4750']");
    private By localidadLocator = By.xpath("//input[@id='localidad']");
    private By direccionLocator = By.xpath("//input[@id='direCompleta']");
    private By codPostalLocator = By.xpath("//input[@id='cpCpa']");
    private By correoElectronico = By.xpath("//input[@id='correoElectronico']");
    private By celularLocator = By.xpath("//input[@id='celular']");
    private By observacionesLocator = By.xpath("//textarea[@id='observaciones']");
    private By envioExpresoLocator = By.xpath("//input[@class='form-check-input EXPRESO']");
    private By envioClasicoLocator = By.xpath("//input[@class='form-check-input CLASICO']");
    private By envioPaqArHoyLocator = By.xpath("//input[@id='pqHoy']");
    private By pagoSaldo = By.xpath("//input[@id='radioSaldo']");
    private By pagoCtaCte = By.xpath("//input[@id='radioCuentaCorriente']");
    private By pagoTarjeta = By.xpath("//input[@class='form-check-input' and @id='radioTarjeta']");
    private By pagoMercadoPago = By.xpath("//input[@class='form-check-input' and @id='radioMercadoPago']");
    private By nuevaTarjetaMP = By.xpath("//label[@class=\"options-list__label\" and @for=\"new_card_row\"]");
    private By dosTarjetasMP = By.xpath("//label[@class=\"options-list__label\" and @for=\"split_cards_row\"]");
    private By numeroTarjetaMP = By.id("cardNumber");
    private By numeroTarjetaUnoMP = By.id("card_number");
    private By nombreTitularMP = By.xpath("//input[@name='[ui_components][group_content][group_scroller][group_form][controls][top_card_group][fullname]']");
    private By nombreTitularUnoMP = By.id("full_name_id");
    private By nombreTitularDosMP = By.xpath("");
    private By mesVencimientoUnoMP = By.xpath("//span[normalize-space()='MM']");
    private By noviembreMP = By.xpath("//span[normalize-space()=11]");
    private By anoVencimientoUnoMP = By.xpath("//span[normalize-space()='AA']");
    private By veinticincoAnoMP = By.xpath("//span[normalize-space()='2025']");
    private By codSeguridadUnoMP = By.xpath("//input[@id='cvv']");
    private By documentoUnoMP = By.xpath("//input[@type='text'and@id='number']");
    private By VencimientoMP = By.id("expirationDate");
    private By codSeguridadMP = By.id("securityCode");
    private By btnContinuarMP = By.xpath("//button[@id='submit']");
    private By numeroDNIMP = By.xpath("//input[@id='number']");
    private By MailMP = By.xpath("//input[@id='email']");
    private By btnPagarLocator = By.xpath("//button[@type='button' and @id='pagar' and text()='Pagar']");
    private By btnPagarMP = By.xpath("//button[@id=\"pay\"]");
    private By btnPagar2 = By.xpath("//button[@id='btnPagar']");
    protected By visaLocator = By.xpath("//label[@title='Visa']");
    protected By numeroTarjeta = By.xpath("//input[@id='card_number']");
    protected By codSeguridad = By.xpath("//input[@id='security_code']");
    protected By mesVencimiento = By.xpath("//input[@id='card_expiration_month']");
    protected By anoVencimiento = By.xpath("//input[@id='card_expiration_year']");
    protected By nombreTitular = By.xpath("//input[@id='card_holder_name']");
    protected By numeroDocumento = By.xpath("//input[@id='card_holder_doc_number']");
    private By codigoTN = By.xpath("(//td[@class='table-text text-center']//div)[9]");

    //Origen del envío individual
    private By origenDelEnvioIndividual = By.xpath("//small[@id='ediOri']");
    private By pickUp = By.xpath("//input[@id='checkPickUp']");
    private By listaPickUP = By.xpath("//select[@id='dirOrigen']");
    private By sucursal = By.xpath("//input[@id='checkSucursal']");
    private By seleccionarProvOrigenSuc2 = By.xpath("//select[@id='sucursalProvinciaOrigen']");
    private By sucursalOrigen = By.xpath("//select[@id='sucursalOrigen']");

    public PageNuevoEnvio(WebDriver driver) {
        super(driver);
    }

    public void caracteristicasDelPaqueteCM(String codigoMaterial) {

        String largo = "0";
        String ancho = "0";
        String alto = "0";
        String peso = "0";
        String valorContenido = "1";

        switch (codigoMaterial) {
            case "0,5kg": {
                largo = "10";
                ancho = "15";
                alto = "2";
                peso = "0,5";
            }
            break;
            case "1kg": {
                largo = "5";
                ancho = "5";
                alto = "5";
                peso = "1";
            }
            break;
            case "2kg": {
                largo = "10";
                ancho = "10";
                alto = "10";
                peso = "2";
            }
            break;
            case "3kg": {
                largo = "10";
                ancho = "10";
                alto = "10";
                peso = "3";
            }
            break;
            case "5kg": {
                largo = "10";
                ancho = "10";
                alto = "10";
                peso = "4";
            }
            break;
            case "10kg": {
                largo = "10";
                ancho = "10";
                alto = "10";
                peso = "6";
            }
            break;
            case "15kg": {
                largo = "10";
                ancho = "12";
                alto = "14";
                peso = "12";
            }
            break;
            case "20kg": {
                largo = "2";
                ancho = "11";
                alto = "12";
                peso = "15,6";
            }
            break;
            case "25kg": {
                largo = "100";
                ancho = "30";
                alto = "50";
                peso = "15";
            }
            break;
            case "30kg": {
                largo = "50";
                ancho = "80";
                alto = "43";
                peso = "25";
            }
            break;
            case "35kg": {
                largo = "100";
                ancho = "45";
                alto = "43";
                peso = "30";
            }
            break;
            case "40kg": {
                largo = "45";
                ancho = "50";
                alto = "100";
                peso = "7";
            }
            break;
            case "50kg": {
                largo = "50";
                ancho = "50";
                alto = "100";
                peso = "12";
            }
            break;
            case "60kg": {
                largo = "100";
                ancho = "70";
                alto = "32";
                peso = "17";
            }
            break;
            case "70kg": {
                largo = "27";
                ancho = "100";
                alto = "100";
                peso = "23";
            }
            break;
            case "80kg": {
                largo = "100";
                ancho = "60";
                alto = "50";
                peso = "30";
            }
            break;
            case "90kg": {
                largo = "50";
                ancho = "100";
                alto = "67";
                peso = "34";
            }
            break;
            case "100kg": {
                largo = "60";
                ancho = "75";
                alto = "85";
                peso = "40";
            }
            break;
            case "110kg": {
                largo = "30";
                ancho = "105";
                alto = "130";
                peso = "50";
            }
            break;
            case "120kg": {
                largo = "40";
                ancho = "118";
                alto = "100";
                peso = "50";
            }
            break;
            case "130kg": {
                largo = "40";
                ancho = "115";
                alto = "111";
                peso = "50";
            }
            break;
            case "140kg": {
                largo = "40";
                ancho = "115";
                alto = "120";
                peso = "50";
            }
            break;
            case "150kg": {
                largo = "50";
                ancho = "118";
                alto = "100";
                peso = "50";
            }
            break;
            case "170kg": {
                largo = "80";
                ancho = "90";
                alto = "90";
                peso = "50";
            }
            break;
            case "190kg": {
                largo = "85";
                ancho = "70";
                alto = "122";
                peso = "50";
            }
            break;
            case "210kg": {
                largo = "100";
                ancho = "80";
                alto = "100";
                peso = "50";
            }
            break;
            case "230kg": {
                largo = "86";
                ancho = "100";
                alto = "100";
                peso = "50";
            }
            break;
            case "250kg": {
                largo = "100";
                ancho = "100";
                alto = "100";
                peso = "50";
            }
            break;
            default:
                System.out.println("No Esta definido ese producto: " + codigoMaterial + " no existe");
                break;
        }

        waitForSeconds(1);
        System.out.println("Valor de peso: " + peso + " kg");
        System.out.println("Alto: " + alto + " cm");
        System.out.println("Largo: " + largo + " cm");
        System.out.println("Ancho: " + ancho + " cm");

        writeText(campoLargoLocator, largo);
        writeText(campoAnchoLocator, ancho);
        writeText(campoAltoLocator, alto);
        writeText(campoPesoLocator, peso);
        writeText(campoValorDelContenido, valorContenido);
        waitForSeconds(1);
        try {
            click(btnNextLocator);
        } catch (Exception e) {
            click(btnNextLocator);
        }
    }

    //funcion vieja
    public void caracteristicasDelPaquete() {

        String largo = "25";
        writeText(campoLargoLocator, largo);

        String ancho = "25";
        writeText(campoAnchoLocator, ancho);

        String alto = "25";
        writeText(campoAltoLocator, alto);

        String peso = "10";
        writeText(campoPesoLocator, peso);

        String valorContenido = "6500";
        writeText(campoValorDelContenido, valorContenido);
        waitForSeconds(1);
        try {
            click(btnNextLocator);
        } catch (Exception e) {
            click(btnNextLocator);
        }
    }

    public void tipoEntrega(String tipoEntrega) {
        click(tipoDeEntrega);
        waitForSeconds(2);
        if (tipoEntrega.equals("Domicilio")) {
            entregaDomicilio();
            scrollPageUpDown(0, 2);
        } else if (tipoEntrega.equals("Sucursal")) {
            entregaSucursal();
            scrollPageUpDown(0, 2);
        } else {
            throw new IllegalArgumentException("Tipo de entrega no válido: " + tipoEntrega);
        }
    }


    // funcion vieja
    public void tipoEntregaZonas(String tipoEntrega) {
        click(tipoDeEntrega);
        waitForSeconds(2);

        String valorProvincia = "C";;
        String codigoPostal = "1424";;
        String nombreProvincia= "CAPITAL FEDERAL";;

        /*
        "B" // BUENOS AIRES
        "C" // CAPITAL FEDERAL
        "K" // CATAMARCA
        "H" // CHACO
        "U" // CHUBUT
        "X" // CORDOBA
        "W" // CORRIENTES
        "E" // ENTRE RIOS
        "P" // FORMOSA
        "Y" // JUJUY
        "L" // LA PAMPA
        "F" // LA RIOJA
        "M" // MENDOZA
        "N" // MISIONES
        "Q" // NEUQUEN
        "R" // RIO NEGRO
        "A" // SALTA
        "J" // SAN JUAN
        "D" // SAN LUIS
        "Z" // SANTA CRUZ
        "S" // SANTA FE
        "G" // SANTIAGO DEL ESTERO
        "V" // TIERRA DEL FUEGO
        "T" // TUCUMAN
         */

        switch (tipoEntrega) {
            case "DomicilioZona1": {
                valorProvincia = "C";
                codigoPostal = "1424";
                nombreProvincia = "CAPITAL FEDERAL";
                entregaDomicilioZonas(valorProvincia, codigoPostal, nombreProvincia);
            }
            break;
            case "DomicilioZona2": {
                valorProvincia = "X";
                codigoPostal = "5000";
                nombreProvincia = "CORDOBA";
                entregaDomicilioZonas(valorProvincia, codigoPostal, nombreProvincia);
            }
            case "DomicilioZona3": {
                valorProvincia = "K";
                codigoPostal = "4700";
                nombreProvincia = "CATAMARCA";
                entregaDomicilioZonas(valorProvincia, codigoPostal, nombreProvincia);
            }
            break;
            case "DomicilioZona4": {
                valorProvincia = "U";
                codigoPostal = "9103";
                nombreProvincia = "CHUBUT";
                entregaDomicilioZonas(valorProvincia, codigoPostal, nombreProvincia);
            }
            break;
            case "DomicilioZona5": {
                valorProvincia = "C";
                codigoPostal = "1020";
                nombreProvincia = "CAPITAL FEDERAL";
                entregaDomicilioZonas(valorProvincia, codigoPostal, nombreProvincia);
            }
            break;
            case "DomicilioZona6": {
                valorProvincia = "C";
                codigoPostal = "1650";//San Martin
                nombreProvincia = "BUENOS AIRES";
                entregaDomicilioZonas(valorProvincia, codigoPostal, nombreProvincia);
            }
            break;
            case "DomicilioZona7": {
                valorProvincia = "B";
                codigoPostal = "1648";//Tigre
                nombreProvincia = "BUENOS AIRES";
                entregaDomicilioZonas(valorProvincia, codigoPostal, nombreProvincia);
            }
            break;
            case "SucursalZona1": {
                valorProvincia = "C";
                codigoPostal = "1424";//CABA
                entregaSucursalZonas(valorProvincia, codigoPostal);
            }
            break;
            case "SucursalZona2": {
                valorProvincia = "X";
                codigoPostal = "5000";//CORDOBA
                entregaSucursalZonas(valorProvincia, codigoPostal);
            }
            break;
            case "SucursalZona3": {
                valorProvincia = "K";
                codigoPostal = "4700";//CATAMARCA
                entregaSucursalZonas(valorProvincia, codigoPostal);
            }
            break;
            case "SucursalZona4": {
                valorProvincia = "U";
                codigoPostal = "9103";//CHUBUT
                entregaSucursalZonas(valorProvincia, codigoPostal);
            }
            break;
            default: System.out.println("No Esta definido ese producto: " + tipoEntrega + "no existe");
        }

        System.out.println("Valor de provincia: " + valorProvincia);
        System.out.println("Codigo de postal: " + codigoPostal);
        System.out.println("Nombre de provincia: " + nombreProvincia);


    }

    private void entregaDomicilioZonas(String valorProvincia, String codigoPostal, String nombreProvincia) {
        click(entregaDomicilio);
        writeText(nomApellidoLocator, "Juan Perez");
        waitForSeconds(1);
        click(seleccionarProvincia);
        waitForSeconds(2);
        selectOptionFromDropdownByValue("provincia", valorProvincia);
        writeText(localidadLocator, nombreProvincia);
        writeText(direccionLocator, "Rivadavia 1200");
        writeText(codPostalLocator, codigoPostal);
        writeText(correoElectronico, "hola1@yopmail.com");
        writeText(celularLocator, "351456789");
        writeText(observacionesLocator, "Casa con rejas negras");
        waitForSeconds(3);
    }

    private void entregaSucursalZonas(String valorProvincia, String codigoPostal) {
        click(entregaSucursal);
        writeText(nomApellidoLocatoSuc, "Carlos Sanchez");
        waitForSeconds(1);
        click(seleccionarProvOrigenSuc);
        waitForSeconds(1);
        selectOptionFromDropdownByValue("provincia2", valorProvincia);
        waitForSeconds(2);
        selectOptionFromDropdownByValue("sucursalDestino2", codigoPostal);
        waitForSeconds(1);
        writeText(correoElectronicoSuc, "hola2@yopmail.com");
        writeText(celularSuc, "3825564354");
        waitForSeconds(3);
    }


    //funcion vieja

    private void entregaDomicilio() {

        String nombreProvincia = "CAPITAL FEDERAL";
        String codigoPostal = "1424";
        String valorProvincia = "C";

        click(entregaDomicilio);
        writeText(nomApellidoLocator, "Juan Perez");
        waitForSeconds(1);
        click(seleccionarProvincia);
        waitForSeconds(2);
        selectOptionFromDropdownByValue("provincia", valorProvincia);
        writeText(localidadLocator, nombreProvincia);
        writeText(direccionLocator, "Rivadavia 1200");
        writeText(codPostalLocator, codigoPostal);
        writeText(correoElectronico, "hola1@yopmail.com");
        writeText(celularLocator, "351456789");
        writeText(observacionesLocator, "Casa con rejas negras");
        waitForSeconds(3);
    }

    //funcion vieja

    private void entregaSucursal() {

        String nombreProvincia = "CAPITAL FEDERAL";
        String codigoPostal = "1424";
        String valorProvincia = "C";

        click(entregaSucursal);
        writeText(nomApellidoLocatoSuc, "Carlos Sanchez");
        waitForSeconds(1);
        click(seleccionarProvOrigenSuc);
        waitForSeconds(1);
        selectOptionFromDropdownByValue("provincia2", valorProvincia);
        waitForSeconds(2);
        selectOptionFromDropdownByValue("sucursalDestino2", codigoPostal);
        waitForSeconds(1);
        writeText(correoElectronicoSuc, "hola2@yopmail.com");
        writeText(celularSuc, "3825564354");
        waitForSeconds(3);
    }

    public void tipoProducto(String tipoProducto) {
        if (tipoProducto.equals("Clasico")) {
            clasico();
        } else if (tipoProducto.equals("Expreso")) {
            expreso();
        } else if (tipoProducto.equals("PaqArHoy")) {
            paqArHoy();
        } else {
            throw new IllegalArgumentException("Tipo de producto no válido: " + tipoProducto);
        }
        preionarPagar1();
    }

    public void expreso() {
        clickWithRetry(envioExpresoLocator);
        waitForSeconds(2);
    }

    public void clasico() {
        clickWithRetry(envioClasicoLocator);
        waitForSeconds(2);
    }

    public void paqArHoy() {
        clickWithRetry(envioPaqArHoyLocator);
        waitForSeconds(2);
    }

    public void preionarPagar1() {
        clickDoble(btnPagarLocator);
        waitForSeconds(1);
    }


    public void MPUnaTarjeta() {
        waitForSeconds(2);
        click(btnPagarLocator);
        waitForSeconds(2);
        click(pagoMercadoPago);
        waitForSeconds(2);
        try {
            click(btnPagar2);
        } catch (Exception e) {
            click(btnPagar2);
        }
        completarFormularioMercadoPago();
        waitForSeconds(5);
    }

    public void EmpDomicilioClasicoMPDosTarjetas() {
        waitForSeconds(2);
        try {
            click(btnNextLocator);
        } catch (Exception e) {
            click(btnNextLocator);
        }
        waitForSeconds(2);
        click(btnPagarLocator);
        waitForSeconds(2);
        click(pagoMercadoPago);
        waitForSeconds(2);
        completarFormularioMercadoPagoDosTarjetas();
    }

    public void completarFormularioMercadoPago() {
        WebElement iframe = findElement(By.tagName("iframe"));
        switchToFrame(iframe);
        // Aceptar cookies si es necesario
        if (elementExists(By.xpath("//button[text()='Aceptar cookies']"))) {
            click(By.xpath("//button[text()='Aceptar cookies']"));
        }
        click(nuevaTarjetaMP);
// Aceptar cookies nuevamente si es necesario
        if (elementExists(By.xpath("//*[contains(text(),'Aceptar cookies')]"))) {
            click(By.xpath("//*[contains(text(),'Aceptar cookies')]"));
        }
        waitForSeconds(2);
        switchToFrame(findElement(By.xpath("//*[@id=\"card_number\"]/iframe")));
        writeText(numeroTarjetaMP, "4509953566233704");
        waitForSeconds(2);
        switchToParentFrame();
        writeText(nombreTitularMP, "APRO");
        switchToFrame(findElement(By.xpath("//*[@id=\"expiration_date\"]/iframe")));
        writeText(VencimientoMP, "1125");
        switchToParentFrame();
        switchToFrame(findElement(By.xpath("//*[@id=\"cvv\"]/iframe")));
        writeText(codSeguridadMP, "123");
        switchToParentFrame();
        click(btnContinuarMP);
        writeText(numeroDNIMP, "11111111");
        click(btnContinuarMP);
        waitForSeconds(2);
        writeText(MailMP, "pepe@correoargentino.com.ar");
        waitForSeconds(1);
        click(btnPagarMP);
        waitForSeconds(3);
        // Verificar si aparecen mensajes de error
        if (elementExists(By.xpath("(//*[normalize-space()='Algo salió mal...'])[1]")) ||
                elementExists(By.xpath("(//*[normalize-space()='No pudimos procesar tu pago'])[1]"))) {
            // Realizar acciones en caso de error
            System.out.println("Algo salio mal...\nNo pudimos procesar tu pago\n");
            click(By.id("mp-close-btn"));
            waitForSeconds(3);
            click(By.xpath("//span[normalize-space()='Cerrar y cancelar pago']"));
        }
        //crear el else para cerrar el camino correcto
    }

    public void completarFormularioMercadoPagoDosTarjetas() {
        WebElement iframe = findElement(By.tagName("iframe"));
        switchToFrame(iframe);
        // Aceptar cookies si es necesario
        if (elementExists(By.xpath("//button[text()='Aceptar cookies']"))) {
            click(By.xpath("//button[text()='Aceptar cookies']"));
        }
        click(dosTarjetasMP);
// Aceptar cookies nuevamente si es necesario
        if (elementExists(By.xpath("//*[contains(text(),'Aceptar cookies')]"))) {
            click(By.xpath("//*[contains(text(),'Aceptar cookies')]"));
        }
        waitForSeconds(2);
        writeText(numeroTarjetaUnoMP, "4509953566233704");
        waitForSeconds(2);
        writeText(nombreTitularUnoMP, "APRO");
        click(mesVencimientoUnoMP);
        waitForSeconds(2);
        click(noviembreMP);
        waitForSeconds(2);
        click(anoVencimientoUnoMP);
        waitForSeconds(2);
        click(veinticincoAnoMP);
        waitForSeconds(2);
        writeText(codSeguridadUnoMP, "123");
        writeText(documentoUnoMP, "11111111");

        click(By.xpath("//button[@aria-label='Cuotas: Elegí']"));
        click(By.xpath("//li[@name='[1]']"));
        click(btnContinuarMP);

        // Verificar si aparecen mensajes de error
        if (elementExists(By.xpath("(//*[normalize-space()='Algo salió mal...'])[1]")) ||
                elementExists(By.xpath("(//*[normalize-space()='No pudimos procesar tu pago'])[1]"))) {
            // Realizar acciones en caso de error
            System.out.println("Algo salio mal...\nNo pudimos procesar tu pago\n");
            click(By.id("mp-close-btn"));
            waitForSeconds(3);
            click(By.xpath("//span[normalize-space()='Cerrar y cancelar pago']"));
        }
        //crear el else para cerrar el camino correcto
    }

    //Origen del envío individual

    //Funcion vieja
    public void origenDelEnvioIndividual(String origen) {

        if (origen.equals("PickUP")) {
            click(origenDelEnvioIndividual);
            waitForSeconds(2);
            origenPickUp();
            scrollPageUpDown(0, 2);
        } else if (origen.equals("Sucursal")) {
            click(origenDelEnvioIndividual);
            waitForSeconds(2);
            //origenSucursal();
            scrollPageUpDown(0, 2);
        } else {
            throw new IllegalArgumentException("Tipo de origen no válido: " + origen);
        }

    }

    public void origenPickUp() {
        click(pickUp);
        waitForSeconds(1);
        click(listaPickUP);
        sendFlechaAbajo(1);
        sendEnter();
        click(btnNextLocator);
    }

    public void origenDelEnvioIndividualConZonas(String origen) {

        click(origenDelEnvioIndividual);
        waitForSeconds(2);

        String valorProvincia;

        switch (origen) {
            case "PickUP": {
                origenPickUp();
            }
            break;
            case "SucursalZona1": {
                valorProvincia = "C";//CABA
                origenSucursal(valorProvincia);
            }
            break;
            case "SucursalZona2": {
                valorProvincia = "B";//Buenos Aires
                origenSucursal(valorProvincia);
            }
            break;
            case "SucursalZona3": {
                valorProvincia = "P";//Formosa
                origenSucursal(valorProvincia);
            }
            break;
            case "SucursalZona4": {
                valorProvincia = "Y";//Jujuy
                origenSucursal(valorProvincia);
            }
            break;
            default:
                System.out.println("No Esta definido ese producto: " + origen + "no existe");
                break;
        }
        scrollPageUpDown(0, 2);

    }


    public void origenSucursal(String valorProvincia) {
        click(sucursal);
        waitForSeconds(1);

        click(seleccionarProvOrigenSuc2);
        selectOptionFromDropdownByValue("sucursalProvinciaOrigen", valorProvincia);
        waitForSeconds(1);

        /*
        sendFlechaAbajo(2);
        sendEnter();

         */

        click(sucursalOrigen);
        sendFlechaAbajo(2);
        sendEnter();
        /*
        String valorProvincia = "C";
        selectOptionFromDropdownByValue("provincia", valorProvincia);
         */

        click(btnNextLocator);
    }

}