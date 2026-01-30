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

    @And("recargo la página cada aproximadamente 2 horas durante {int} ciclo")
    public void recargoLaPaginaCadaAproximadamente2HorasDuranteCiclo(int ciclos) {
        // 2 horas = 120 minutos, con variación ~±10%
        pageJenkins.recargarCadaAproximadamenteMinutosDuranteCiclos(120, ciclos);
    }

    @And("recargo la página cada aproximadamente {double} minutos durante {int} ciclos")
    public void recargoLaPaginaCadaAproximadamenteMinutosDuranteCiclos(double minutos, int ciclos) {
        pageJenkins.recargarCadaAproximadamenteMinutosDuranteCiclos(minutos, ciclos);
    }
}
