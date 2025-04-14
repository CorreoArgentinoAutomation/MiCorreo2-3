package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageMiniPaqAr extends BasePage {

    PageFranquicia pageFranquicia;

    private By btnMiniPaqAr = By.xpath("//li[.='Acceso a MiniPaqar']");
    private By menuImposicionEnvios = By.xpath("//h3[contains(.,'Imposici')]");
    private By submenuImposicionPostal = By.xpath("(//li[contains(text(), 'Imposici')])[1]");
    private By campoEmail = By.xpath("//input[@id='mailIP']");
    private By campoEmailConfimacion = By.xpath("//input[@id='mailConfirmarIP']");
    private By campoNumeroSeguimiento = By.xpath("//input[@id='tytIP']");
    private By btnConsultaMiniPaqAr = By.xpath("//button[@id='consulta']");
    private By btnConfirmar = By.xpath("//div[@id='divButtonAceptarContent' and contains(text(), 'Confirmar')]");
    private By msjConfirmacionImpuestas = By.xpath("//span[@id='divModal' and contains(text(), 'Piezas impuestas correctamente')]");
    private By btnCerrarSesion = By.xpath("//h3[@class='item-menu-sesion']");
    //private By  = By.xpath("");
    //private By  = By.xpath("");
    //private By  = By.xpath("");//private By  = By.xpath("");


    public PageMiniPaqAr(WebDriver driver) {
        super(driver);
        this.pageFranquicia = new PageFranquicia(driver);
    }

    public void irAMiniPaqAr() {
        click(pageFranquicia.btnMenuPanel);
        waitForSeconds(1);
        click(pageFranquicia.btnPuntoCorreo);
        waitForSeconds(1);
        click(btnMiniPaqAr);
        waitForSeconds(2);

        cambiarFocoPestana();
        waitForSeconds(1);


    }

    public void menuImposicionEnvios() {
        click(menuImposicionEnvios);
        waitForSeconds(1);
        click(submenuImposicionPostal);
        waitForSeconds(1);

    }

    public void llenarFormularioImposicionPostal(String numeroTT){
        writeText(campoEmail, "automationpi@yopmail.com");
        writeText(campoEmailConfimacion, "automationpi@yopmail.com");
        writeText(campoNumeroSeguimiento, numeroTT);

        click(btnConsultaMiniPaqAr);
        waitForSeconds(1);
        click(btnConfirmar);
        waitForSeconds(1);


    }

    public void validarMsjConfirmacion(){
        compararTextoConMensajeEsperado(msjConfirmacionImpuestas, "Piezas impuestas correctamente");
        extrarTextoDeUnLocalizador("El mensaje de confirmacion es",msjConfirmacionImpuestas);
    }

    public void cerrarSesion(){
        click(btnCerrarSesion);
    }


}
