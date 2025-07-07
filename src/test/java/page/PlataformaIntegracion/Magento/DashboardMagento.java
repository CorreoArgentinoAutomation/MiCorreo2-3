package page.PlataformaIntegracion.Magento;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardMagento extends BasePage {
    public DashboardMagento(WebDriver driver) {
        super(driver);
    }

    private By campoUsr = By.xpath("//input[@id='username']");
    private By campoPassword = By.xpath("//input[@id='login']");
    private By btnSignIn = By.xpath("//span[.='Sign in']");

    private By btnProfile = By.xpath("//span[@class='admin-user-account-text']");
    private By opcionCustomerView = By.xpath("//a[contains(.,'Customer View')]");
    private By btnGearTienda = By.xpath("//span[.='Gear']");


    //Abrir la tienda
    public void ingresarALaTienda() {
        String urlNuevoAmbiente = "https://prestashop.integracionesco.shop/ag/";//"https://integracionesco.shop/pra/";
        abrirNuevaPestanaYNavegarA(urlNuevoAmbiente);
    }

    public void loginDashboard() {
        String userName = "rvargas";
        String password = "Password1234!";
        writeText(campoUsr, userName);
        writeText(campoPassword, password);
        click(btnSignIn);
    }

    public void irALaTienda() {

        abrirNuevaPestanaYNavegarA("http://45.56.72.143/");
        click(btnGearTienda);
        /*
        waitForSeconds(1);
        click(btnProfile);
        waitForSeconds(1);
        click(opcionCustomerView);
        switchToNewTab2();
        click(btnGearTienda);
         */
    }

}
