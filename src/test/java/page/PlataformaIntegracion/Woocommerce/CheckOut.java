package page.PlataformaIntegracion.Woocommerce;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOut extends BasePage {

    private By radioBtnPrimeraOpcion = By.xpath("(//*[contains(text(), 'Correo Argentino expreso a domicilio:')])[1]");//label[.='Correo Argentino cl�sico a sucursal']");//("(//input[@class='shipping_method'])[1]");
    private By radioBtnSegundaOpcion = By.xpath("//label[.='Correo Argentino cl�sico a domicilio']");//("(//input[@class='shipping_method'])[2]");
    private By radioBtnTerceraOpcion = By.xpath("//label[.='Correo Argentino expreso a domicilio']");//("(//input[@class='shipping_method'])[3]");
    private By radioBtnCuartaOpcion = By.xpath("//label[.='Correo Argentino expreso a sucursal']");//("(//input[@class='shipping_method'])[4]");
    private By listaDeSucursales = By.xpath("//span[@class='select2-dropdown select2-dropdown--below']");

    private By cuadroTexto = By.xpath("//span[@class='select2 select2-container select2-container--default select2-container--focus']//span[@class='select2-selection select2-selection--single']");//("//input[@class='select2-search__field']");
    private By btnseleccionSucursal = By.xpath("(//span[@class='select2-selection__rendered'])[2]");
    private By seleccionPrimeraSucursal = By.xpath("//li[@class='select2-results__option select2-results__option--selectable select2-results__option--highlighted']");
    private By checkEnvioAlterno = By.xpath("//input[@id='ship-to-different-address-checkbox']");
    private By btnFinalizarCompra = By.xpath("//a[@class='checkout-button button alt wc-forward wp-element-button']");
    private By btnRealizarElPedido = By.xpath("//button[@id='place_order']");    //button[@class='button alt wp-element-button']");
    private By btnRealizaPedido = By.xpath("//button[@value='Realizar el pedido' and @data-value='Realizar el pedido']");
    private By totalDeArriba = By.xpath("(//span[@class='wc-block-order-confirmation-summary-list-item__value'])[3]");
    private By totalDeAbajo = By.xpath("(//span[@class='woocommerce-Price-amount amount'])[14]");
    //private By numeroPedido = By.xpath("//*[@class='woocommerce-order-overview__order order' and strong]");
    private By numeroPedido = By.xpath("(//span[@class='wc-block-order-confirmation-summary-list-item__value'])[1]");
    private By correoUsLocator = By.xpath("(//span[@class='wc-block-order-confirmation-summary-list-item__value'])[4]");
    private By fechaLocator = By.xpath("(//span[@class='wc-block-order-confirmation-summary-list-item__value'])[2]");
    private By primeraSucursal = By.xpath("//li[@class='select2-results__option select2-results__option--selectable select2-results__option--selected select2-results__option--highlighted']");
    private By afuera = By.xpath("//div[@id='order_review']");

    //formulario del envio

    private By campoNombre = By.xpath("//input[@id='shipping_first_name']");

    private By campoApellidos = By.xpath("//input[@id='shipping_last_name']");

    private By campoDireccion = By.xpath("(//input[@class='input-text '])[13]");

    private By campoAlturaPisoDtp = By.xpath("(//input[@class='input-text '])[14]");

    private By campoPoblacion = By.xpath("(//input[@class='input-text '])[15]");

    private By campoProvincias = By.xpath("//select[@id='shipping_state']");
    private By campoProvincia = By.xpath("//select[@id='shipping_state']/option[.='Ciudad Aut�noma de Buenos Aires']");

    private By campoCodigoPostal = By.xpath("(//input[@class='input-text '])[16]");

    //=============================================================================
    //Opciones de envio
    private By expresoSucursal = By.xpath("//*[contains(text(), 'Correo Argentino expreso a sucursal:')]");
    private By clasicoSucursal = By.xpath("//*[contains(text(), 'Correo Argentino cl�sico a sucursal:')]");
    private By expresoDomicilio = By.xpath("//*[contains(text(), 'Correo Argentino expreso a domicilio:')]");
    private By clasicoDomicilio = By.xpath("//*[contains(text(), 'Correo Argentino cl�sico a domicilio:')]");

    public CheckOut(WebDriver driver) {
        super(driver);
    }

    public void checkTienda() {
        waitForSeconds(1);
        System.out.println("se hace un clic en el Finalizar Compra");
        click(checkEnvioAlterno);
        System.out.println("Click en el check de envio alterno");
        waitForSeconds(10);
        //click(radioBtnSegundaOpcion);
        scrollPageUpDown(0, 3);
    }


    public void llenarFormularioEnvio() {
        writeText(campoNombre, "Test");
        writeText(campoApellidos, "llenado");
        writeText(campoDireccion, "Rivadavia");
        writeText(campoAlturaPisoDtp, "2001,1,1");
        writeText(campoPoblacion, "100");
        click(campoProvincias);
        //click(campoProvincia);
        sendEnter();
        writeText(campoCodigoPostal, "1020");

        scrollPageUpDown(0, 3);
    }


    //1 - 2
    public void quitarEnvioAlterno() {

        waitForSeconds(1);
        click(checkEnvioAlterno);
        scrollPageUpDown(0, 3);

    }

    //2
    public void seleccionOpcionEnvioDomicioOp2() {
        waitForSeconds(32);
        click(radioBtnSegundaOpcion);


    }

    public void seleccionOpcionSucursalOp4() {
        waitForSeconds(32);
        click(radioBtnCuartaOpcion);

    }

    //3
    public void seleccionOpcionSucursalOp1() {
        waitForSeconds(15);
        click(radioBtnPrimeraOpcion);

    }

    public void seleccionOpcionEnvioDomicioOp3() {
        waitForSeconds(32);
        click(radioBtnTerceraOpcion);
    }

    //4
    public void seleccionarEnvioSucursal() {
        waitForSeconds(32);
        click(btnseleccionSucursal);
    }

    //5
    public void escribirEnSucursal() {
        //for(int i=0;i<3;i++){
        click(cuadroTexto);
        writeText(cuadroTexto, "a");
        //click(primeraSucursal);
        //System.out.println("Esta es la ejecuci�n n�mero: " + (i + 1));
        //click(afuera);
        //waitForSeconds(2);
        //}
        sendEnter();
    }

    //6
    public void finalizarPedido() {
        //scrollPageUpDown(3,0);
        llenarFormularioEnvio();

        waitForSeconds(28);
        scrollPageUpDown(0, 1);
        click(btnRealizarElPedido);
        waitForSeconds(10);
        mostrarDatosDelPedido();
    }

    //7
    public void mostrarDatosDelPedido() {
        String NpedidoTienda = extraerNumeros(numeroPedido);
        String FechaPedido = getText(fechaLocator);
        String CorreoUser = getText(correoUsLocator);
        String Total = getText(totalDeArriba);
        System.out.println("--------------------------------" +
                "\nDatos del Checkout: \n" + NpedidoTienda + "\n"
                + FechaPedido + "\n" + CorreoUser + "\n" + Total +
                "\n--------------------------------");

    }

    //Flujo a seguir en el checkout
    //1 - Quitar el check del envio alterno
    //2 - Hacer un scroll hacia abajo y esperar que carguen la opciones de envio
    //3 - Seleccionar la primera opcion de envio y que sea un envio a sucursal
    //4 - Seleccionar el envio a sucursal
    //5 - Escribir en el cuadro de texto de sucursal y seleccionar la primera opcion
    //6 - Hacer un clic en el boton de Finalizar pedido y se redirigira al detalle del pedido
    //7 - Capturar los datos del pedido


    public void generarPedidoCheckOut() {
        quitarEnvioAlterno();
        seleccionOpcionEnvioDomicioOp2();
        finalizarPedido();
        mostrarDatosDelPedido();
    }

    public void tipoEnvio(String tipoEnvio) {
        waitForSeconds(15);

        switch (tipoEnvio) {
            case "expreso a sucursal":
                scrollPageUpDown(0, 2);
                click(expresoSucursal);
                escribirEnSucursal();
                break;
            case "clasico a sucursal":
                scrollPageUpDown(0, 2);
                click(clasicoSucursal);
                break;
            case "expreso a domicilio":
                scrollPageUpDown(0, 2);
                click(expresoDomicilio);
                break;
            case "clasico a domicilio":
                scrollPageUpDown(0, 2);
                click(clasicoDomicilio);
                break;
            default:
                throw new IllegalArgumentException("Tipo de entrega no v�lido: " + tipoEnvio);

        }
    }

    public void terminarPedido() {
        waitForSeconds(8);
        scrollPageUpDown(0, 1);
        click(btnRealizarElPedido);
        waitForSeconds(4);
    }

}



