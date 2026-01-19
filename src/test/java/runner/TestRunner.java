package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/Cucumber.html"},
        monochrome = true,
        stepNotifications = true,
        publish = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/main/resources/features"},
        // Glue paths: incluye todos los paquetes de step definitions, framework y hooks
        glue = {
                "stepsDefinitions",           // Incluye stepsDefinitions.EcosistemaDigital, MiCorreo1_5, MiCorreo2, etc.
                "PlataformaIntegracion",      // Steps de integración (WooCommerce, Presta, Shopify, Magento)
                "framework",                  // Hooks y utilidades del framework
                "MiCorreo2",                  // Steps específicos de MiCorreo2
                "Google",                     // Steps relacionados con Google
                "MiCorreo1_5"                 // Steps específicos de MiCorreo1_5
        },
        // Tag por defecto: @LoginED
        // Para ejecutar otros tags, cambiar este valor o usar -Dcucumber.filter.tags en Maven
        // Ejemplos de tags disponibles:
        // - @LoginED: Login en Ecosistema Digital
        // - @RecuperarContrasenaED: Recuperación de contraseña
        // - @RegistroEcosistemaDigital: Registro de usuario
        // - @EnvioIndivual: Envío individual (MiCorreo1_5)
        // - @Woocommerce: Tests de WooCommerce
        // - @Presta: Tests de PrestaShop
        // - @MiCorreo2: Tests de MiCorreo2
        tags = "@recargarSaldo"
)
public class TestRunner {
}

