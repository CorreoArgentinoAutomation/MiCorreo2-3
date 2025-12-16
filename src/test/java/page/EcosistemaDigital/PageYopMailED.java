package page.EcosistemaDigital;

import framework.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Versión específica de YopMail para Ecosistema Digital.
 * Se copia la lógica de page.MiCorreo1_5.PageYopMail para evitar problemas de dependencias entre paquetes.
 */
public class PageYopMailED extends BasePage {

    public By campoEmail = By.xpath("//input[@id='login']");
    public By iframeMail = By.id("ifmail");
    public By listaEmails = By.xpath("//div[@class='m']//a");
    public By contenidoEmail = By.xpath("//div[@id='mail']");
    public By btnRefresh = By.id("refresh");

    public PageYopMailED(WebDriver driver) {
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

    public void buscarEmailTemporal(String email) {
        waitForSeconds(1);
        writeText(campoEmail, email);
        waitForSeconds(1);
        sendEnter();
        waitForSeconds(1);
    }

    public void buscarEmailRecuperacionContrasena(String email) {
        try {
            getDriver().get("https://yopmail.com/es/");
            waitForSeconds(2);

            String emailSinDominio = email.split("@")[0];
            writeText(campoEmail, emailSinDominio);
            waitForSeconds(1);
            sendEnter();
            waitForSeconds(3);

            try {
                click(btnRefresh);
                waitForSeconds(3);
            } catch (Exception e) {
                System.out.println("No se pudo hacer clic en refresh, continuando...");
            }

            cambiarAIframe(iframeMail);
            waitForSeconds(2);

            List<WebElement> emails = getDriver().findElements(listaEmails);
            boolean emailEncontrado = false;

            for (WebElement emailElement : emails) {
                String textoEmail = emailElement.getText().toLowerCase();
                if (textoEmail.contains("recuperación") ||
                        textoEmail.contains("recuperar") ||
                        textoEmail.contains("contraseña") ||
                        textoEmail.contains("password") ||
                        textoEmail.contains("reset")) {
                    emailElement.click();
                    emailEncontrado = true;
                    System.out.println("✓ Email de recuperación encontrado y abierto");
                    waitForSeconds(2);
                    break;
                }
            }

            if (!emailEncontrado && !emails.isEmpty()) {
                emails.get(0).click();
                System.out.println("⚠ No se encontró email específico de recuperación, abriendo el más reciente");
                waitForSeconds(2);
            }

            volverAContenidoDefault();

        } catch (Exception e) {
            volverAContenidoDefault();
            throw new AssertionError("✗ Error al buscar email de recuperación: " + e.getMessage());
        }
    }

    public void validarEmailRecuperacionRecibido() {
        try {
            cambiarAIframe(iframeMail);
            waitForSeconds(2);

            WebElement contenido = findElement(contenidoEmail);
            String textoEmail = contenido.getText().toLowerCase();

            boolean contieneRecuperacion = textoEmail.contains("recuperación") ||
                    textoEmail.contains("recuperar") ||
                    textoEmail.contains("contraseña") ||
                    textoEmail.contains("password") ||
                    textoEmail.contains("reset");

            if (contieneRecuperacion) {
                System.out.println("✓ Email de recuperación de contraseña validado correctamente");
                System.out.println("Contenido del email contiene información de recuperación");
            } else {
                throw new AssertionError("✗ El email no contiene información de recuperación de contraseña");
            }

            volverAContenidoDefault();

        } catch (Exception e) {
            volverAContenidoDefault();
            throw new AssertionError("✗ Error al validar email de recuperación: " + e.getMessage());
        }
    }

    public String extraerEnlaceRecuperacion() {
        try {
            cambiarAIframe(iframeMail);
            waitForSeconds(2);

            WebElement contenido = findElement(contenidoEmail);
            String textoEmail = contenido.getText();
            String htmlEmail = contenido.getAttribute("innerHTML");

            Pattern pattern = Pattern.compile("href=[\"'](https?://[^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(htmlEmail);

            String enlaceEncontrado = null;
            while (matcher.find()) {
                String enlace = matcher.group(1);
                if (enlace.contains("reset") ||
                        enlace.contains("recuperar") ||
                        enlace.contains("password") ||
                        enlace.contains("recover") ||
                        enlace.contains("token")) {
                    enlaceEncontrado = enlace;
                    break;
                }
            }

            if (enlaceEncontrado == null) {
                pattern = Pattern.compile("(https?://[^\\s]+)", Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(textoEmail);
                while (matcher.find()) {
                    String enlace = matcher.group(1);
                    if (enlace.contains("reset") ||
                            enlace.contains("recuperar") ||
                            enlace.contains("password") ||
                            enlace.contains("recover")) {
                        enlaceEncontrado = enlace;
                        break;
                    }
                }
            }

            volverAContenidoDefault();

            if (enlaceEncontrado != null) {
                System.out.println("✓ Enlace de recuperación encontrado: " + enlaceEncontrado);
                return enlaceEncontrado;
            } else {
                throw new AssertionError("✗ No se encontró enlace de recuperación en el email");
            }

        } catch (Exception e) {
            volverAContenidoDefault();
            throw new AssertionError("✗ Error al extraer enlace de recuperación: " + e.getMessage());
        }
    }

    public void validarEnlaceRecuperacionEnEmail() {
        try {
            String enlace = extraerEnlaceRecuperacion();
            Assert.assertNotNull("El enlace de recuperación no debe ser null", enlace);
            Assert.assertFalse("El enlace de recuperación no debe estar vacío", enlace.isEmpty());
            System.out.println("✓ Enlace de recuperación validado: " + enlace);
        } catch (Exception e) {
            throw new AssertionError("✗ Error al validar enlace de recuperación: " + e.getMessage());
        }
    }

    public void clicEnEnlaceRecuperacion() {
        try {
            cambiarAIframe(iframeMail);
            waitForSeconds(2);

            WebElement contenido = findElement(contenidoEmail);
            String htmlEmail = contenido.getAttribute("innerHTML");

            Pattern pattern = Pattern.compile("href=[\"'](https?://[^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(htmlEmail);

            String enlaceEncontrado = null;
            while (matcher.find()) {
                String enlace = matcher.group(1);
                if (enlace.contains("reset") ||
                        enlace.contains("recuperar") ||
                        enlace.contains("password") ||
                        enlace.contains("recover") ||
                        enlace.contains("recovery") ||
                        enlace.contains("token")) {
                    enlaceEncontrado = enlace;
                    break;
                }
            }

            if (enlaceEncontrado == null) {
                String textoEmail = contenido.getText();
                pattern = Pattern.compile("(https?://[^\\s]+)", Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(textoEmail);
                while (matcher.find()) {
                    String enlace = matcher.group(1);
                    if (enlace.contains("reset") ||
                            enlace.contains("recuperar") ||
                            enlace.contains("password") ||
                            enlace.contains("recover") ||
                            enlace.contains("recovery")) {
                        enlaceEncontrado = enlace;
                        break;
                    }
                }
            }

            volverAContenidoDefault();

            if (enlaceEncontrado != null) {
                System.out.println("✓ Enlace de recuperación encontrado: " + enlaceEncontrado);
                System.out.println("Navegando al enlace de recuperación...");
                getDriver().get(enlaceEncontrado);
                waitForSeconds(3);
                System.out.println("✓ Se navegó al enlace de recuperación de contraseña");
            } else {
                throw new AssertionError("✗ No se encontró enlace de recuperación en el email");
            }

        } catch (Exception e) {
            volverAContenidoDefault();
            throw new AssertionError("✗ Error al hacer clic en el enlace de recuperación: " + e.getMessage());
        }
    }

    public void clicEnEnlaceActivacion(String email) {
        try {
            String currentUrl = getDriver().getCurrentUrl();
            if (!currentUrl.contains("yopmail.com")) {
                getDriver().get("https://yopmail.com/es/");
                waitForSeconds(2);
            }

            String emailSinDominio = email.split("@")[0];
            writeText(campoEmail, emailSinDominio);
            waitForSeconds(1);
            sendEnter();
            waitForSeconds(3);

            try {
                click(btnRefresh);
                waitForSeconds(3);
            } catch (Exception e) {
                System.out.println("No se pudo hacer clic en refresh, continuando...");
            }

            cambiarAIframe(iframeMail);
            waitForSeconds(2);

            List<WebElement> emails = getDriver().findElements(listaEmails);
            boolean emailEncontrado = false;

            for (WebElement emailElement : emails) {
                String textoEmail = emailElement.getText().toLowerCase();
                if (textoEmail.contains("activ") ||
                        textoEmail.contains("activación") ||
                        textoEmail.contains("activacion") ||
                        textoEmail.contains("bienvenido") ||
                        textoEmail.contains("registro")) {
                    emailElement.click();
                    emailEncontrado = true;
                    System.out.println("✓ Email de activación encontrado y abierto");
                    waitForSeconds(2);
                    break;
                }
            }

            if (!emailEncontrado && !emails.isEmpty()) {
                emails.get(0).click();
                System.out.println("⚠ No se encontró email específico de activación, abriendo el más reciente");
                waitForSeconds(2);
            }

            WebElement contenido = findElement(contenidoEmail);
            String htmlEmail = contenido.getAttribute("innerHTML");
            String textoEmail = contenido.getText();

            Pattern pattern = Pattern.compile("href=[\"'](https?://[^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(htmlEmail);

            String enlaceEncontrado = null;
            while (matcher.find()) {
                String enlace = matcher.group(1);
                if (enlace.contains("activ") ||
                        enlace.contains("activate") ||
                        enlace.contains("verific") ||
                        enlace.contains("confirm") ||
                        enlace.contains("token")) {
                    enlaceEncontrado = enlace;
                    break;
                }
            }

            if (enlaceEncontrado == null) {
                pattern = Pattern.compile("(https?://[^\\s]+)", Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(textoEmail);
                while (matcher.find()) {
                    String enlace = matcher.group(1);
                    if (enlace.contains("activ") ||
                            enlace.contains("activate") ||
                            enlace.contains("verific") ||
                            enlace.contains("confirm")) {
                        enlaceEncontrado = enlace;
                        break;
                    }
                }
            }

            volverAContenidoDefault();

            if (enlaceEncontrado != null) {
                System.out.println("✓ Enlace de activación encontrado: " + enlaceEncontrado);
                System.out.println("Navegando al enlace de activación...");
                getDriver().get(enlaceEncontrado);
                waitForSeconds(3);
                System.out.println("✓ Se navegó al enlace de activación de usuario");
            } else {
                throw new AssertionError("✗ No se encontró enlace de activación en el email");
            }

        } catch (Exception e) {
            volverAContenidoDefault();
            throw new AssertionError("✗ Error al hacer clic en el enlace de activación: " + e.getMessage());
        }
    }

    private void cambiarAIframe(By iframeLocator) {
        try {
            WebElement iframe = getDriver().findElement(iframeLocator);
            getDriver().switchTo().frame(iframe);
        } catch (Exception e) {
            System.out.println("Error al cambiar al iframe: " + e.getMessage());
        }
    }

    private void volverAContenidoDefault() {
        try {
            getDriver().switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Error al volver al contenido por defecto: " + e.getMessage());
        }
    }
}


