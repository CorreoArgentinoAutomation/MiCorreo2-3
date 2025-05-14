package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageForm extends BasePage {

    protected By visaLocator = By.xpath("//label[@title='Visa']");
    protected By numeroTarjeta = By.xpath("//input[@id='card_number']");
    protected By codSeguridad = By.xpath("//input[@id='security_code']");
    protected By mesVencimiento = By.xpath("//input[@id='card_expiration_month']");
    protected By anoVencimiento = By.xpath("//input[@id='card_expiration_year']");
    protected By nombreTitular = By.xpath("//input[@id='card_holder_name']");
    protected By numeroDocumento = By.xpath("//input[@id='card_holder_doc_number']");
    protected By btnPagarLocator = By.xpath("//button[@id='pagar']");

    //Pago con Mercado Pago
    protected By pagarTarjCredito = By.xpath("//span[text()='Tarjeta de cr']");//button[.='Tarjeta de cr�dito']");
    protected By campoNumTarjeta = By.xpath("//input[@id='cardNumber']");
    protected By campoNombreTitular = By.xpath("//input[@id='cardholderName']");
    protected By campoVencimiento = By.xpath("(//input[@id='expirationDate'])[1]");
    protected By campoCodSeguridad = By.xpath("//input[@id='securityCode']");
    protected By campoDNI = By.xpath("//input[@id='cardholderIdentificationNumber']");
    protected By btnContinuar = By.xpath("//span[.='Continuar']");
    protected By campoEmailMercadoPago = By.xpath("//input[@id='user-email-input']");
    protected By btnPagarMercadoPago = By.xpath("//span[text()='Pagar']");

    public PageForm(WebDriver driver){
        super (driver);
    }
    public void pagoConTarjeta(){
        click(visaLocator);
        writeText(numeroTarjeta,"4507990000004905");
        writeText(codSeguridad,"775");
        writeText(mesVencimiento,"08");
        writeText(anoVencimiento,"25");
        writeText(nombreTitular,"TARJETA VISA");
        writeText(numeroDocumento,"27859328");
        waitForSeconds(2);
        clickWithRetry(btnPagarLocator);
        waitForSeconds(5);
    }

    public void pagoConMercadoPago(){

        waitForSeconds(2);

        sendTab(2);
        sendEnter();

        waitForSeconds(3);
        sendKeys("371180303257522");

        sendTab(1);
        sendKeys("APRO");

        sendTab(1);
        sendKeys("1125");

        waitForSeconds(1);
        sendTab(1);
        sendKeys("1234");

        waitForSeconds(1);
        sendTab(3);
        sendKeys("11111112");

        waitForSeconds(1);
        sendTab(1);
        sendEnter();

        waitForSeconds(1);
        writeText(campoEmailMercadoPago,"test_user_1826015362@testuser.com");

        click(btnPagarMercadoPago);

        /*
        waitForSeconds(1);
        sendTab(2);
        sendKeys("hola@yopmail.com");


         */


        /*
        //click(pagarTarjCredito);
        waitForSeconds(1);
        writeText(campoNumTarjeta,"5031 7557 3453 0604");
        writeText(campoNombreTitular,"APRO");
        writeText(campoVencimiento,"1125");
        writeText(campoCodSeguridad,"123");
        writeText(campoDNI,"12345678");
        click(btnContinuar);
        writeText(campoEmailMercadoPago,"hola@yopmail.com");
        click(btnPagarMercadoPago);
        waitForSeconds(3);

         */
    }
}