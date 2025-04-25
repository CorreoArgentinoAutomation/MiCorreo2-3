package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PageFranquicia extends BasePage {


    By btnMenuPanel = By.xpath("//span[@class='navbar-toggler-icon']");
    By btnPuntoCorreo = By.xpath("//span[contains(@class, 'm-2') and contains(@class, 'gilroy-medium') and contains(@class, 'd-flex') and contains(@class, 'align-items-center') and contains(@class, 'color-correo-black')]/img[@alt='Punto Correo']");
    private By btnNuevoPedido = By.xpath("//a[.='Nuevo pedido']");
    private By btnSumarCantidadCartaSimple = By.xpath("//div[@id='Cartasimple']/div[1]//div[@class='input-group-append']/button[@class='btn ']");
    private By btnSumarCantidadCartaSimple150gm = By.xpath("//div[@id='Cartasimple']/div[2]//div[@class='input-group-append']/button[@class='btn ']");
    private By btnSumarCartaCertificada = By.xpath("//div[@id='Cartacertificada']//div[@class='input-group-append']/button[@class='btn ']");
    private By btnSumarCartaExpreso = By.xpath("//div[@id='Cartaexpreso']//div[@class='input-group-append']/button[@class='btn ']");
    private By btnSumarCartaDocumento = By.xpath("//div[@id='Cartadocumento']//div[@class='input-group-append']/button[@class='btn ']");
    private By btnGuardar = By.xpath("//button[contains(.,'Guardar')]");
    private By btnIrPendientes = By.xpath("//a[contains(.,'Ir a pendientes')]");
    private By checkSeleccionTodos = By.xpath("//input[@id='check_padre']");
    private By btnCotizar = By.xpath("//button[@id='btnpedido']");
    private By btnGenerar = By.xpath("//div[contains(@class, 'col-md-4') and contains(@class, 'mt-auto') and contains(@class, 'pb-1') and contains(@class, 'bd-highlight')]//button[contains(@class, 'btn') and contains(@class, 'btn-correo-primary') and contains(@class, 'botonDimensiones') and contains(@class, 'd-none') and contains(@class, 'd-lg-block')]");
    private By numeroTAndT = By.xpath(" //tbody/tr[1]/td[4]/div");
    private By btnImprimirSellosDigitales = By.xpath("//a[contains(text(), 'aqu')]");
    private By campoNumeroSeguimiento = By.xpath("//input[@id='seguimiento']");
    private By btnConsultar = By.xpath("//button[@id='buscar']");
    private By msjPagoExitoso = By.xpath("//div[@class='col-12']/div[@class='row']");//h6[contains(text(), '¡Genial! Tu pago fue procesado correctamente')]");
    private By numeroTAndTConsulta = By.xpath(" //table[contains(@id, 'contenedo_tabla')]//td/div[contains(text(), 'M')]");


    //imprirmir Sellos Digitales
    private By btnGenerarSellos = By.xpath("//button[@id='btn_armar_rotulos_franquicia']");
    private By btnCancelar = By.xpath("//button[@type='button']/span[text()='Cancelar']");
    private By iframe = By.xpath("//iframe[@id='pdf-viewer']");


    public PageFranquicia(WebDriver driver) {
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


    public void Producto(String Producto, String cantidad) {

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

        scrollPageUpDown(0, 3);
        click(btnGuardar);
        waitForSeconds(2);
        scrollPageUpDown(0, 3);
    }

    public void cartaSimple20g(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            click(btnSumarCantidadCartaSimple);
        }
        waitForSeconds(2);
    }

    public void cartaSimple150g(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            click(btnSumarCantidadCartaSimple150gm);
        }
        waitForSeconds(2);
    }

    public void cartaCertificada(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            click(btnSumarCartaCertificada);
        }
        waitForSeconds(2);
    }


    public void cartaExpreso(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            click(btnSumarCartaExpreso);
        }
        waitForSeconds(2);
    }

    public void cartaDocumento(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            click(btnSumarCartaDocumento);
        }
        waitForSeconds(2);
    }


    public void pantallaPendientes() {
        click(btnIrPendientes);
        waitForSeconds(2);
    }


    public void cotizarPendientes() {
        click(checkSeleccionTodos);
        click(btnCotizar);
        waitForSeconds(2);
    }

    public void generar() {
        click(btnGenerar);
        waitForSeconds(2);
    }

    public String obtenerNumeroSeguimiento() {
        String numero = extrarTextoDeUnLocalizador("El numero de seguimiento es", numeroTAndT);
        return numero;
    }

    public void imprimirSellosDigitales() {

        extrarTextoDeUnLocalizador("El mensaje de pago exitoso es", msjPagoExitoso);

        waitForSeconds(1);
        click(btnImprimirSellosDigitales);
        waitForSeconds(1);
    }

    public void escribirNumeroSeguimiento(String numeroSeguimiento) {
        click(campoNumeroSeguimiento);
        writeText(campoNumeroSeguimiento, numeroSeguimiento);
        waitForSeconds(1);

        click(btnConsultar);
        /*
        click(checkSeleccionTodos);
        waitForSeconds(1);

         */

        extrarTextoDeUnLocalizador("El numero de track and trace es", numeroTAndTConsulta);


    }


    public void generarSelloDigital(String producto, String numeroSeguimiento) throws IOException {

        click(checkSeleccionTodos);
        waitForSeconds(1);

        click(btnGenerarSellos);
        waitForSeconds(3);

        String productoBuscado = "";

        if (producto.equals("Carta Simple 20g")) {
            productoBuscado = "Carta Simple Hasta 20g";
        } else if (producto.equals("Carta Simple 150g")) {
            productoBuscado = "Carta Simple Hasta 150g";
        } else if (producto.equals("Carta Certificada")) {
            productoBuscado = "Carta Certificada Plus\t\tHasta\n" + "150g";
        } else if (producto.equals("Carta Expreso")) {
            productoBuscado = "Carta Expreso Plus\t\tHasta 150g";
        } else if (producto.equals("Carta Documento")) {
            productoBuscado = "Carta Documento";
        } else {
            throw new IllegalArgumentException("Tipo de producto no válido: " + producto);
        }

        waitForSeconds(5);
        //leerPDF(textoEsperado, numeroSeguimiento);
        leerPDF(productoBuscado, numeroSeguimiento);
        waitForSeconds(2);
        capturarPantalla();
        recargar(1);


    }

}
