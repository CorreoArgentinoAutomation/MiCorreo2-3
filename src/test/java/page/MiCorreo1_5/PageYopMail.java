package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageYopMail extends BasePage {

    public By campoEmail = By.xpath("//input[@id='login']");
    public By btnRefrescar = By.xpath("//button[@id='refresh']]");


    public PageYopMail(WebDriver driver) {
        super(driver);
    }


    public String crearMailTemporal() {
        String letras = caracteresAleatorios(6);
        String numero = numerosAleatorios(6);
        String dominio = "yopmail.com";
        String email = letras + numero;
        String emailCreado = letras + numero + "@" + dominio;


        writeText(campoEmail, email);
        waitForSeconds(1);
        sendEnter();

        System.out.println("El email generado es: " + emailCreado);
        return emailCreado;

    }

    public void buscarEmailTemporal(String email){
        writeText(campoEmail, email);
        waitForSeconds(1);
        sendEnter();

        click(btnRefrescar);
        waitForSeconds(1);

    }


}
