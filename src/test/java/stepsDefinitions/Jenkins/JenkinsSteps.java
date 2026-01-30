package stepsDefinitions.Jenkins;

import framework.BasePage;
import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import page.Jenkins.PageJenkinsLogin;

public class JenkinsSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final PageJenkinsLogin pageJenkins = new PageJenkinsLogin(driver);

    @Given("abro el navegador en la URL de Jenkins {string}")
    public void abroElNavegadorEnLaURLDeJenkins(String url) {
        pageJenkins.navegarA(url);
    }

    @And("inicio sesión en Jenkins con usuario {string} y contraseña {string}")
    public void inicioSesionEnJenkinsConUsuarioYContrasena(String usuario, String contrasena) {
        pageJenkins.login(usuario, contrasena);
        // Breve espera para que cargue el dashboard tras el login
        BasePage.waitForSeconds(3);
    }

    @And("recargo la página 1 vez cada minuto durante 2 horas")
    public void recargoLaPaginaUnaVezCadaMinutoDurante2Horas() {
        // 2 horas = 120 minutos → 120 recargas (1 por minuto)
        pageJenkins.recargarUnaVezCadaMinutoDuranteMinutos(120);
    }

    @And("recargo la página 1 vez cada minuto durante {double} minutos")
    public void recargoLaPaginaUnaVezCadaMinutoDuranteMinutos(double minutos) {
        pageJenkins.recargarUnaVezCadaMinutoDuranteMinutos(minutos);
    }
}
