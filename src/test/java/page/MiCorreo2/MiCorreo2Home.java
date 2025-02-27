package page.MiCorreo2;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiCorreo2Home extends BasePage {
    public MiCorreo2Home(WebDriver driver) {
        super(driver);
    }

    private By campoUsr = By.xpath("//input[@formcontrolname='username' and @type='email']");
    private By campoPassword = By.xpath("//input[@formcontrolname='password' and @type='password']");
    private By btnIngresar = By.xpath("//span[contains(@class, 'button-margin-internal') and contains(@class, 'ng-star-inserted') and text()='Ingresar']");
    //=========================================================================================================================================================================
    //Envios
    //=========================================================================================================================================================================
    private By btnNuevoEnvio = By.xpath("//img[@src='../../../assets/img/dashboard/add.svg']");
    private By btnMicuenta = By.xpath("//p[@class='menu-list-profile-user' and contains(text(), 'Mi cuenta')]");

    public void tipoDeEnvio() {
        waitForSeconds(1);
        click(btnNuevoEnvio);
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

}

