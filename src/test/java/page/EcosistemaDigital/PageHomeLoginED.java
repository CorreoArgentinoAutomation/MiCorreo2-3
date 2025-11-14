package page.EcosistemaDigital;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import page.MiCorreo1_5.PageForm;

public class PageHomeLoginED extends BasePage {

    PageForm pageForm;

    public By emailLocator = By.xpath("//input[@name='email']");
    public By passwordLocator = By.xpath("//input[@name='password']");
    public By btnLogin = By.xpath("//button[.='Ingresar' or contains(.,'Ingresar')]");

    public PageHomeLoginED(WebDriver driver) {
        super(driver);
        this.pageForm = new PageForm(driver);
    }

    public void login() {
        boolean loginExitoso = false;
        String expectedUrl = "https://wcpzt.correo.local/MiCorreo/public/message-home";
        int intentos = 0;
        int maxIntentos = 2; // Establece el número máximo de intentos

        while (!loginExitoso && intentos < maxIntentos) {
            try {
                // Paso 2
                writeText(emailLocator, "empctacte_test@yopmail.com");
                writeText(passwordLocator, "123123");
                click(btnLogin);

                // Paso 3
                waitForUrlToBe(expectedUrl, 2);

                // Verificar si la URL es la esperada
                String currentUrl = getCurrentURL(); // Utilizando la función encapsulada
                if (currentUrl.equals(expectedUrl)) {
                    // Si estamos en la página principal, el inicio de sesión es exitoso
                    System.out.println("¡Inicio de sesión exitoso!");
                    System.out.println("Estamos en la página principal (message-home).");
                    return; // Salir del método después de un inicio de sesión exitoso
                } else {
                    // Si no estamos en la página principal, continuar con el siguiente intento
                    System.out.println("Inicio de sesión fallido. No estamos en la página principal.");
                    intentos++;
                }
            } catch (Exception e) {
                // Si se produce una excepción, incrementar el contador de intentos
                intentos++;
                System.out.println("Intento de inicio de sesión #" + intentos + " fallido.");
            }
        }
        System.out.println("Inicio de sesión fallido después de " + maxIntentos + " intentos.");
    }

    public void ingresoEmail(String email) {
        writeText(emailLocator, email);
    }

    public void ingresoPassword(String password) {
        writeText(passwordLocator, password);
        click(btnLogin);
    }

    public void btnIngresar() {
        click(btnLogin);
    }

    public void msjEsperado(String mensaje) {
        System.out.println("El sistema muestra el mensaje:");

        switch (mensaje) {
            case "Correo o contraseña incorrectos":
                System.out.println("Correo o contraseña incorrectos");
                break;
            case "Usuario no encontrado":
                System.out.println("Usuario no encontrado");
                break;
            case "Usuario bloqueado":
                System.out.println("Usuario bloqueado");
                break;
            case "Usuario desactivado":
                System.out.println("Usuario desactivado");
                break;
            default:
                System.out.println("Mensaje no reconocido");
                break;
        }


    }


}
