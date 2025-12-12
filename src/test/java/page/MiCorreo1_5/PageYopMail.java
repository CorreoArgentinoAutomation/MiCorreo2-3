package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageYopMail extends BasePage {

    public By campoEmail = By.xpath("//input[@id='login']");
    public By btnRefrescar = By.xpath("//button[@id='refresh']");
    // Locators para emails en Yopmail
    public By iframeMail = By.id("ifmail");
    public By listaEmails = By.xpath("//div[@class='m']//a");
    public By contenidoEmail = By.xpath("//div[@id='mail']");
    public By btnRefresh = By.id("refresh");

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
        waitForSeconds(1);
        writeText(campoEmail, email);
        waitForSeconds(1);
        sendEnter();
        waitForSeconds(1);
    }

    /**
     * Busca y abre el email de recuperación de contraseña
     * @param email El email del usuario
     */
    public void buscarEmailRecuperacionContrasena(String email) {
        try {
            // Navegar a Yopmail
            getDriver().get("https://yopmail.com/es/");
            waitForSeconds(2);
            
            // Ingresar el email
            String emailSinDominio = email.split("@")[0];
            writeText(campoEmail, emailSinDominio);
            waitForSeconds(1);
            sendEnter();
            waitForSeconds(3);
            
            // Refrescar el buzón para obtener nuevos emails
            try {
                click(btnRefresh);
                waitForSeconds(3);
            } catch (Exception e) {
                System.out.println("No se pudo hacer clic en refresh, continuando...");
            }
            
            // Cambiar al iframe del buzón de correo
            cambiarAIframe(iframeMail);
            waitForSeconds(2);
            
            // Buscar el email de recuperación de contraseña
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
                // Si no se encuentra específicamente, abrir el email más reciente
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

    /**
     * Valida que el email de recuperación fue recibido
     */
    public void validarEmailRecuperacionRecibido() {
        try {
            cambiarAIframe(iframeMail);
            waitForSeconds(2);
            
            WebElement contenido = findElement(contenidoEmail);
            String textoEmail = contenido.getText().toLowerCase();
            
            // Verificar que el email contiene texto relacionado con recuperación
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

    /**
     * Extrae y valida el enlace de recuperación de contraseña del email
     * @return El enlace de recuperación encontrado
     */
    public String extraerEnlaceRecuperacion() {
        try {
            cambiarAIframe(iframeMail);
            waitForSeconds(2);
            
            WebElement contenido = findElement(contenidoEmail);
            String textoEmail = contenido.getText();
            String htmlEmail = contenido.getAttribute("innerHTML");
            
            // Buscar enlaces en el HTML
            Pattern pattern = Pattern.compile("href=[\"'](https?://[^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(htmlEmail);
            
            String enlaceEncontrado = null;
            while (matcher.find()) {
                String enlace = matcher.group(1);
                // Verificar que el enlace sea de recuperación/reset
                if (enlace.contains("reset") || 
                    enlace.contains("recuperar") || 
                    enlace.contains("password") ||
                    enlace.contains("recover") ||
                    enlace.contains("token")) {
                    enlaceEncontrado = enlace;
                    break;
                }
            }
            
            // Si no se encuentra en el HTML, buscar en el texto
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

    /**
     * Valida que el email contiene un enlace para recuperar contraseña
     */
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

    /**
     * Hace clic en el enlace de recuperación de contraseña del email
     * Busca el enlace en el email y lo abre en una nueva pestaña o navega directamente
     */
    public void clicEnEnlaceRecuperacion() {
        try {
            cambiarAIframe(iframeMail);
            waitForSeconds(2);
            
            WebElement contenido = findElement(contenidoEmail);
            String htmlEmail = contenido.getAttribute("innerHTML");
            
            // Buscar el enlace en el HTML
            Pattern pattern = Pattern.compile("href=[\"'](https?://[^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(htmlEmail);
            
            String enlaceEncontrado = null;
            while (matcher.find()) {
                String enlace = matcher.group(1);
                // Verificar que el enlace sea de recuperación/reset
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
            
            // Si no se encuentra en el HTML, buscar en el texto y extraer URL
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
                
                // Navegar directamente al enlace
                getDriver().get(enlaceEncontrado);
                waitForSeconds(3);
                
                System.out.println("✓ Se navegó al enlace de recuperación de contraseña");
            } else {
                // Intentar hacer clic en el texto "aquí" o en cualquier enlace del email
                cambiarAIframe(iframeMail);
                waitForSeconds(1);
                
                // Buscar enlaces clickeables en el email
                List<WebElement> enlaces = getDriver().findElements(By.xpath("//a[contains(@href, 'http')]"));
                if (!enlaces.isEmpty()) {
                    // Buscar el enlace que contenga palabras clave de recuperación
                    for (WebElement enlace : enlaces) {
                        String href = enlace.getAttribute("href");
                        if (href != null && (href.contains("recovery") || 
                                            href.contains("reset") || 
                                            href.contains("password") ||
                                            href.contains("recover"))) {
                            volverAContenidoDefault();
                            getDriver().get(href);
                            waitForSeconds(3);
                            System.out.println("✓ Se hizo clic en el enlace de recuperación");
                            return;
                        }
                    }
                    // Si no encuentra uno específico, usar el primero
                    WebElement primerEnlace = enlaces.get(0);
                    String href = primerEnlace.getAttribute("href");
                    volverAContenidoDefault();
                    getDriver().get(href);
                    waitForSeconds(3);
                    System.out.println("✓ Se hizo clic en el primer enlace encontrado: " + href);
                } else {
                    volverAContenidoDefault();
                    throw new AssertionError("✗ No se encontró enlace clickeable en el email");
                }
            }
            
        } catch (Exception e) {
            volverAContenidoDefault();
            throw new AssertionError("✗ Error al hacer clic en el enlace de recuperación: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para cambiar al iframe
     */
    private void cambiarAIframe(By iframeLocator) {
        try {
            WebElement iframe = getDriver().findElement(iframeLocator);
            getDriver().switchTo().frame(iframe);
        } catch (Exception e) {
            System.out.println("Error al cambiar al iframe: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para volver al contenido por defecto
     */
    private void volverAContenidoDefault() {
        try {
            getDriver().switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Error al volver al contenido por defecto: " + e.getMessage());
        }
    }

    /**
     * Busca el email de activación y hace clic en el enlace "Activa tu usuario"
     * @param email El email del usuario
     */
    public void clicEnEnlaceActivacion(String email) {
        try {
            // Navegar a Yopmail si no estamos ahí
            String currentUrl = getDriver().getCurrentUrl();
            if (!currentUrl.contains("yopmail.com")) {
                getDriver().get("https://yopmail.com/es/");
                waitForSeconds(2);
            }
            
            // Ingresar el email
            String emailSinDominio = email.split("@")[0];
            writeText(campoEmail, emailSinDominio);
            waitForSeconds(1);
            sendEnter();
            waitForSeconds(3);
            
            // Refrescar el buzón
            try {
                click(btnRefresh);
                waitForSeconds(3);
            } catch (Exception e) {
                System.out.println("No se pudo hacer clic en refresh, continuando...");
            }
            
            // Cambiar al iframe del buzón de correo
            cambiarAIframe(iframeMail);
            waitForSeconds(2);
            
            // Buscar el email de activación
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
                // Si no se encuentra específicamente, abrir el email más reciente
                emails.get(0).click();
                System.out.println("⚠ No se encontró email específico de activación, abriendo el más reciente");
                waitForSeconds(2);
            }
            
            // Buscar el enlace de activación en el contenido del email
            WebElement contenido = findElement(contenidoEmail);
            String htmlEmail = contenido.getAttribute("innerHTML");
            String textoEmail = contenido.getText();
            
            // Buscar enlaces en el HTML
            Pattern pattern = Pattern.compile("href=[\"'](https?://[^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(htmlEmail);
            
            String enlaceEncontrado = null;
            while (matcher.find()) {
                String enlace = matcher.group(1);
                // Verificar que el enlace sea de activación
                if (enlace.contains("activ") || 
                    enlace.contains("activate") || 
                    enlace.contains("verific") ||
                    enlace.contains("confirm") ||
                    enlace.contains("token")) {
                    enlaceEncontrado = enlace;
                    break;
                }
            }
            
            // Si no se encuentra en el HTML, buscar en el texto
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
            
            // Si no se encuentra enlace, buscar botón o texto "Activa tu usuario"
            if (enlaceEncontrado == null) {
                // Buscar enlaces clickeables en el email
                List<WebElement> enlaces = getDriver().findElements(By.xpath("//a[contains(@href, 'http')]"));
                if (!enlaces.isEmpty()) {
                    // Buscar el enlace que contenga palabras clave de activación
                    for (WebElement enlace : enlaces) {
                        String href = enlace.getAttribute("href");
                        String textoEnlace = enlace.getText().toLowerCase();
                        if ((href != null && (href.contains("activ") || href.contains("activate"))) ||
                            textoEnlace.contains("activa") || textoEnlace.contains("activar")) {
                            enlaceEncontrado = href;
                            break;
                        }
                    }
                    // Si no encuentra uno específico, usar el primero
                    if (enlaceEncontrado == null && !enlaces.isEmpty()) {
                        enlaceEncontrado = enlaces.get(0).getAttribute("href");
                    }
                }
            }
            
            volverAContenidoDefault();
            
            if (enlaceEncontrado != null) {
                System.out.println("✓ Enlace de activación encontrado: " + enlaceEncontrado);
                System.out.println("Navegando al enlace de activación...");
                
                // Navegar directamente al enlace
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

}
