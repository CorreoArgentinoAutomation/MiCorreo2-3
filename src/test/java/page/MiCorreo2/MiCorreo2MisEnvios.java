package page.MiCorreo2;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiCorreo2MisEnvios extends BasePage {
    public MiCorreo2MisEnvios(WebDriver driver) {
        super(driver);
    }

    //Envios Pendientes
    private By btnEnvios = By.xpath("//img[@src='../../../assets/img/dashboard/local_post_office.svg' and @aria-label='Mis envíos']");
    private By btnEnviosPendientes = By.xpath("//h3[.='Pendientes']");

    public void enviosPendientes(){
        click(btnEnvios);
        click(btnEnviosPendientes);
    }
}

