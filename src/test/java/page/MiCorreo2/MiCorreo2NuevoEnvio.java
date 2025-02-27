package page.MiCorreo2;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiCorreo2NuevoEnvio extends BasePage {
    public MiCorreo2NuevoEnvio(WebDriver driver) {
        super(driver);
    }

    private By campoName = By.xpath("//input[@formcontrolname='name' and @aria-required='true' and contains(@class, 'mat-mdc-input-element')]");
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


    public void tipoDeEntrega(){
        waitForSeconds(1);
        writeText(campoName, "Automation Test");
        waitForSeconds(2);

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

}

