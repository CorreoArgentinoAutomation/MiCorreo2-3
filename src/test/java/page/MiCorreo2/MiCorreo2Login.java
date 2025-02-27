package page.MiCorreo2;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiCorreo2Login extends BasePage {
    public MiCorreo2Login(WebDriver driver) {
        super(driver);
    }

    private By campoUsr = By.xpath("//input[@formcontrolname='username' and @type='email']");
    private By campoPassword = By.xpath("//input[@formcontrolname='password' and @type='password']");
    private By btnIngresar = By.xpath("//span[contains(@class, 'button-margin-internal') and contains(@class, 'ng-star-inserted') and text()='Ingresar']");
    //=========================================================================================================================================================================


    public void loginMiCorreo2() {
        String userName = "Automationpi@yopmail.com";
        String password = "123123Aa@";
        writeText(campoUsr, userName);
        writeText(campoPassword, password);
        click(btnIngresar);
    }

}

