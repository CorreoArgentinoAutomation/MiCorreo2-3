package page.MiCorreo2;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiCorreo2Servicios extends BasePage {
    public MiCorreo2Servicios(WebDriver driver) {
        super(driver);
    }

    //Servicios y Oficios Judiciales
    private By menuServicios = By.xpath("//img[contains(@src, 'dashboard/services.svg') and @aria-label='Servicios']");
    private By subMenuOficios = By.xpath("//h3[contains(.,'Oficios judiciales')]");
    private By listaCamara = By.xpath("//div[@class='mat-mdc-form-field-infix ng-tns-c2608167813-4']//mat-select[@formcontrolname='camara']");
    private By listaCamaraDos = By.xpath("//mat-select[@formcontrolname='camara']");
    private By listaCamaraTres = By.xpath("//mat-option/span[contains(text(),'Cámara Penal Económico')]");
    private By numeroExpediente = By.xpath("//input[@formcontrolname='expediente']");
    private By listaAnio = By.xpath("//input[@formcontrolname='ano']");
    private By cantidadGestiones = By.xpath("//input[@formcontrolname='gestiones']");
    private By btnGuardarOficio = By.xpath("//button[text()='Guardar']");
    private By validacionPopUp = By.xpath("//strong[contains(.,'Oficio Judicial registrado exitosamente')]");
    public void serviciosOficiosJudiciales(){
        waitForSeconds(2);
        click(menuServicios);
        click(subMenuOficios);
    }

    public void llenarFormularioOficios(){
        waitForSeconds(2);
        //click(listaCamara);
        //var opcon = driver.findElement(By.xpath("//mat-option/span[contains(text(),'Cámara Penal Económico')]"));
        //opcon.click();

        click(listaCamaraDos);
        click(listaCamaraTres);
        writeText(numeroExpediente, "123123");
        writeText(listaAnio, "123123");
        writeText(cantidadGestiones, "123123");
        waitForSeconds(2);
        click(btnGuardarOficio);
    }

    public void confirmacionOficio(){
        compararTextoConMensajeEsperado(validacionPopUp, "Oficio Judicial registrado exitosamente");
        waitForSeconds(1);
    }

}

