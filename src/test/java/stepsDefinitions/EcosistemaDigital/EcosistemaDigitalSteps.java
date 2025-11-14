package stepsDefinitions.EcosistemaDigital;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.EcosistemaDigital.PageHomeLoginED;
import page.MiCorreo1_5.*;

import java.io.IOException;

public class EcosistemaDigitalSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("urlED");
    private PageHomeLoginED pageHomeLoginED = new PageHomeLoginED(driver);

    public String email;
    public String numeroSeguimiento;

    /*
    @Given("^el usuario se situa en los campos email y password$")
    public void elUsuarioSeSituaEnLosCamposEmailYPassword() {
        driver.get(baseUrl);
        //pageHomeLogin.assertURL("https://wcpzt.correo.local/MiCorreo/public/");
    }

     */

    @Given("que el usuario está en la página de login")
    public void queElUsuarioEstaEnLaPaginaDeLogin() {
        driver.get(baseUrl);
    }

    @And("el usuario ingresa el correo {string}")
    public void elUsuarioIngresaElCorreo(String email) {
        pageHomeLoginED.ingresoEmail(email);
    }

    @And("el usuario ingresa la contraseña {string}")
    public void elUsuarioIngresaLaContrasena(String password) {
        pageHomeLoginED.ingresoPassword(password);
    }

    @When("el usuario hace clic en el botón Ingresar")
    public void elUsuarioHaceClicEnElBotonIngresar() {
        pageHomeLoginED.btnIngresar();
    }

    @Then("el sistema muestra el mensaje {string}")
    public void elSistemaMuestraElMensaje(String mensajeEsperado) {
        pageHomeLoginED.msjEsperado(mensajeEsperado);
    }

    @When("que el usuario ingresa al sitio de ecosistema digital")
    public void queElUsuarioIngresaAlSitioDeEcosistemaDigital() {
        driver.get(baseUrl);
    }
}
