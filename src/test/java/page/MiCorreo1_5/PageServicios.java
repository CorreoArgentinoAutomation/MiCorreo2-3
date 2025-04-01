package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageServicios extends BasePage {

    PageCheckOut pageCheckOut;
    public PageServicios(WebDriver driver){
        super (driver);
        this.pageCheckOut = new PageCheckOut(driver);
    }

    private By btnOficiosJudiciales = By.xpath("//img[@alt='Servicios - Oficios judiciales']");
    private By campoCamara = By.xpath("//select[@id='camara']");
    private By opcionCamara = By.xpath("//option[.='Justicia Federal de Bahía Blanca']");
    private By campoNroExpediente = By.xpath("//input[@id='nroexpediente']");
    private By campoAnio = By.xpath("//input[@id='anio']");
    private By campoCantidad = By.xpath("//input[@id='cantidad']");
    private By btnGuardar = By.xpath("//button[@id='btn-save-oficio']");
    private By confirmacionOficio = By.xpath("//div[@class='alert alert-success alert-dismissable']");

    //Pagar oficio judicial
    private By primerCheckboxFila = By.xpath("(//tbody//input[@type='checkbox' and contains(@class, 'chkSelecTodosId')])[1]");
    private By btnCotizar = By.xpath("//button[@id='btnpedido']");
    private By msjPagoRealizado = By.xpath("//h6[@class='mb-1 text-muted']");

    //Eliminar oficio judicial
    private By checkPadre = By.xpath("//input[@id='check_padre']");
    private By btnEliminar = By.xpath("//i[@class='bi bi-trash']");
    private By msjSinOficiosCargados = By.xpath("//div[@class='dvEnvios']");
    //private By  = By.xpath("");



    //Crear oficio judicial
    public void menuDeServiciosOficiosJudiciales(){
        click(btnOficiosJudiciales);
    }

    public void formularioOficioJudicial(){
        click(campoCamara);
        waitForSeconds(1);
        int numerosAleatorios = Integer.parseInt(numerosAleatorios(1));
        sendFlechaAbajo(numerosAleatorios);
        //click(opcionCamara);
        //writeText(campoCamara, "justicia");
        waitForSeconds(1);
        sendEnter();
        String numeroExpediente = String.valueOf(numerosAleatorios(4));
        writeText(campoNroExpediente, numeroExpediente);

        writeText(campoAnio, "2024");

        String numeroCantidad = String.valueOf(numerosAleatorios(2));
        writeText(campoCantidad, numeroCantidad);

        click(btnGuardar);
        waitForSeconds(1);

        scrollPageUpDown(0,1);
    }

    public void msjConfirmacionOficio(){
        compararTextoConMensajeEsperado(confirmacionOficio, "Oficio agregado correctamente.Oficio agregado correctamente.");
        System.out.println("El mensaje de confirmacion es: " + getText(confirmacionOficio));
    }

    //Pagar oficio judicial
    public void pagarOficioJudicial(String medioPago) {
        click(primerCheckboxFila);
        waitForSeconds(1);

        //scrollPageUpDown(0, 1);
        click(btnCotizar);

        pageCheckOut.medioPago(medioPago);
    }

    public void msjConfirmacionPagoRealizado(){
        compararTextoConMensajeEsperado(msjPagoRealizado, "¡Genial! Tu pago fue procesado correctamente.");
        System.out.println("El mensaje de confirmacion es: " + getText(msjPagoRealizado));
    }

    public void eliminarTodosLosOficios(){
        scrollPageUpDown(0, 3);

        hacerClickElementoExiste(checkPadre);
        waitForSeconds(1);
        hacerClickElementoExiste(btnEliminar);
        waitForSeconds(1);

        System.out.println("El mensaje de confirmacion es: " + getText(msjSinOficiosCargados));

        scrollPageUpDown(2, 0);
    }

}
