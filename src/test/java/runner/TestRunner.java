package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/Cucumber.html"},
        monochrome = true,
        stepNotifications = true,
        publish = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/main/resources/features/MiCorreo2"
        },
        glue = {"stepsDefinitions", "framework","MiCorreo2","Google","MiCorreo1_5"}, // Agrega el paquete de los hooks y framework
        tags =  "@PDG1-252"
        //"@PPDIPI-3167"
)
public class TestRunner {
}

