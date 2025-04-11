package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageFranquisia extends BasePage {

    private By btnMenuPanel = By.xpath("//span[@class='navbar-toggler-icon']");
    private By btnPuntoCorreo = By.xpath("//span[contains(@class, 'm-2') and contains(@class, 'gilroy-medium') and contains(@class, 'd-flex') and contains(@class, 'align-items-center') and contains(@class, 'color-correo-black')]/img[@alt='Punto Correo']");
    private By btnNuevoPedido = By.xpath("//a[.='Nuevo pedido']");
    private By btnSumarCantidadCartaSimple = By.xpath("//div[@id='Cartasimple']/div[1]//div[@class='input-group-append']/button[@class='btn ']");
    private By btnGuardar = By.xpath("//button[contains(.,'Guardar')]");
    private By btnIrPendientes = By.xpath("//a[contains(.,'Ir a pendientes')]");
    private By checkSeleccionTodos = By.xpath("//input[@id='check_padre']");
    private By btnCotizar = By.xpath("//button[@id='btnpedido']");
    private By btnGenerar = By.xpath("//div[contains(@class, 'col-md-4') and contains(@class, 'mt-auto') and contains(@class, 'pb-1') and contains(@class, 'bd-highlight')]//button[contains(@class, 'btn') and contains(@class, 'btn-correo-primary') and contains(@class, 'botonDimensiones') and contains(@class, 'd-none') and contains(@class, 'd-lg-block')]");
    private By numeroTAndT = By.xpath(" //tbody/tr[1]/td[4]/div");
    private By btnImprimirSellosDigitales = By.xpath("//a[contains(text(), 'aqu')]");
    private By campoNumeroSeguimiento = By.xpath("//input[@id='seguimiento']");
    private By btnConsultar = By.xpath("//button[@id='buscar']");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");


    public PageFranquisia(WebDriver driver) {
        super(driver);
    }

    public void menuPanel() {
        click(btnMenuPanel);
        waitForSeconds(1);
        click(btnPuntoCorreo);
        waitForSeconds(1);
        click(btnNuevoPedido);

        waitForSeconds(2);
    }



    public void Producto(String Producto, String cantidad){

        int cantidadInt = Integer.parseInt(cantidad);

        if (Producto.equals("Carta Simple 20g")) {
            cartaSimple20g(cantidadInt);
        } else if (Producto.equals("Carta Simple 150g")) {
            cartaSimple150g(cantidadInt);
        } else if (Producto.equals("Carta Certificada")) {
            cartaCertificada(cantidadInt);
        } else if (Producto.equals("Carta Expreso")) {
            cartaExpreso(cantidadInt);
        } else if (Producto.equals("Carta Documento")) {
            cartaDocumento(cantidadInt);
        } else {
            throw new IllegalArgumentException("Tipo de producto no válido: " + Producto);
        }
        click(btnGuardar);
        waitForSeconds(2);
    }
    public void cartaSimple20g(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            click(btnSumarCantidadCartaSimple);
        }
        waitForSeconds(2);
    }

    public void cartaSimple150g(int cantidad){
        //click();
        waitForSeconds(2);
    }
    public void cartaCertificada(int cantidad){
        //click();
        waitForSeconds(2);
    }
    public void cartaExpreso(int cantidad){
        //click();
        waitForSeconds(2);
    }
    public void cartaDocumento(int cantidad){
        //click();
        waitForSeconds(2);
    }


    public void pantallaPendientes(){
        click(btnIrPendientes);
        waitForSeconds(2);
    }


    public void cotizarPendientes(){
        click(checkSeleccionTodos);
        click(btnCotizar);
        waitForSeconds(2);
    }

    public void generar(){
        click(btnGenerar);
        waitForSeconds(2);
    }

    public String obtenerNumeroSeguimiento(){
        String numero = extrarNumero(numeroTAndT);
        return numero;
    }

    public void imprimirSellosDigitales(){
        waitForSeconds(1);
        click(btnImprimirSellosDigitales);
        waitForSeconds(1);
    }

    public void escribirNumeroSeguimiento(String numeroSeguimiento){
        click(campoNumeroSeguimiento);
        writeText(campoNumeroSeguimiento, numeroSeguimiento);
        waitForSeconds(1);

        click(btnConsultar);
        click(checkSeleccionTodos);
        waitForSeconds(1);

    }

}
