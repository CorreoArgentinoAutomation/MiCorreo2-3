package framework;


import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.*;


public class BasePage {

    private final WebDriver driver;
    public final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void click(By locator) {
        findElement(locator).click();
    }

    public void clickDoble(By locator) {
        try {
            click(locator);
        } catch (Exception e) {
            click(locator);
        }
    }

    public void clickWithRetry(By locator) {
        int maxAttempts = 3; // Número máximo de intentos
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                click(locator);
                return; // Salir del método si la operación de clic tiene éxito
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                // Manejar la excepción (puede agregar un registro o tomar una captura de pantalla aquí)
                System.err.println("Error al hacer clic en el elemento: " + e.getMessage());
                // Esperar antes de intentar nuevamente
                waitForSeconds(1);
            }
        }
        // Si todos los intentos fallan, lanzar la excepción
        throw new ElementNotInteractableException("No se pudo interactuar con el elemento después de " + maxAttempts + " intentos");
    }

    public void clickMultiple(By locator, int cantidadDeClicks) {
        int i;
        for (i = 0; i < cantidadDeClicks; i++) {
            findElement(locator).click();
        }
    }

    public void moveToElementAndClick(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = findElement(locator);
        actions.moveToElement(element).click().perform();
    }

    public boolean elementExists(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Método para seleccionar una opción de un dropdown por valor
    public void selectOptionFromDropdownByValue(String dropdownId, String value) {
        WebElement dropdownElement = driver.findElement(By.id(dropdownId));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void assertURL(String expectedUrl) {
        String currentUrl = getCurrentURL();
        Assert.assertEquals("La URL actual no coincide con la URL esperada", expectedUrl, currentUrl);
    }

    public boolean waitForUrlToBe(String url, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException e) {
            return false; // El tiempo de espera se agotó antes de que la URL coincidiera
        }
    }

    public void writeText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText().trim();
    }

    public boolean compararTextoConMensajeEsperado(By locator, String textoEsperado) {
        // Obtener el texto del sitio utilizando el localizador proporcionado
        String textoDelSitio = getText(locator);
        // Comparar el texto del sitio con el texto esperado
        return textoDelSitio.equals(textoEsperado);
    }

    public String extraerNumeros(By locator) {
        WebElement element = driver.findElement(locator);
        String texto = element.getText();
        String numeros = texto.replaceAll("[^0-9]", "");
        return numeros;
    }

    public String extraerTextoPedido(By locator) {
        WebElement orderReferenceElement = driver.findElement(locator);
        // Obtiene el texto completo del elemento
        String fullText = orderReferenceElement.getText();
        // Extrae la parte después de "Referencia de pedido: "
        String orderReference = fullText.replace("Referencia de pedido: ", "").trim();
        //La Linea de abajo sirve para que es lo que trae.
        //System.out.println("Referencia de pedido: " + orderReference);
        return orderReference;
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void esperaImplicita(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> sendWebElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Elemento no encontrado con el localizador proporcionado: " + locator);
            return null;
        }
    }

    public void clickLastElementInDropdown(By locator) {
        List<WebElement> dropdownElements = sendWebElements(locator);

        if (dropdownElements != null && !dropdownElements.isEmpty()) {
            WebElement lastElement = dropdownElements.get(dropdownElements.size() - 1);
            try {
                lastElement.click();
            } catch (Exception e) {
                System.out.println("Error al hacer clic en el último elemento del menú desplegable: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontraron elementos en el dropdown con el locator proporcionado: " + locator);
        }
    }

    // Método para seleccionar una opción de un elemento <select> por texto visible
    protected void seleccionarOpcionPorTextoVisible(By locator, String textoVisible) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        select.selectByVisibleText(textoVisible);
    }

    // Método para seleccionar una opción de un elemento <select> por valor
    protected void seleccionarOpcionPorValor(By locator, String valor) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        select.selectByValue(valor);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement findElement(By locator) {
        //System.out.println("El elemento existe");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void validarCampo(String nombreCampo, By locator, String textoEsperado) {
        String textoCampo = getText(locator); // Utiliza el método getText() de BasePage para obtener el texto del elemento
        // Verifica si el texto del campo coincide con el texto esperado
        if (textoCampo.equals(textoEsperado)) {
            System.out.println("El campo '" + nombreCampo + "' está correctamente llenado: " + textoCampo);
        } else {
            System.out.println("El campo '" + nombreCampo + "' no coincide con el texto esperado.");
            System.out.println("Texto esperado: " + textoEsperado);
            System.out.println("Texto actual: " + textoCampo);
        }
    }

    public boolean validarCampoExistenteYEditable(By locator) {
        try {
            // Buscar el elemento por el locator proporcionado
            WebElement element = findElement(locator);

            // Validar que el elemento existe
            Assert.assertTrue(element.isDisplayed());

            // Validar que el elemento es editable (en este caso, solo para campos de texto)
            Assert.assertTrue(element.isEnabled());

            // Si se llega a este punto, la validación fue exitosa
            return true;
        } catch (Exception e) {
            // En caso de cualquier excepción, capturar y mostrar el mensaje de error
            e.printStackTrace();
            System.out.println("Error al validar el campo: " + e.getMessage());
            // La validación no fue exitosa
            return false;
        }
    }

    // Método personalizado para cambiar al contexto de un iframe específico
    public void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    // Método personalizado para regresar al contexto del iframe padre
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    // Método para cambiar al contexto predeterminado de la página
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Método para obtener los identificadores de todas las ventanas abiertas
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    // Método para cambiar al contexto de una ventana específica
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    // Cambia al contexto de un iframe utilizando su índice.
    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    // Cambia al contexto de un iframe utilizando su nombre o ID.
    public void switchToFrameByNameOrId(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    //Iframe
    // Cambiar al iframe usando su ruta
    public void cambioDeIframe(String iframeName) {

        WebElement iframe = driver.findElement(By.tagName("iframe"));
        String src = iframe.getAttribute("src");
        System.out.println("================================================================");
        System.out.println("src: " + src);
        System.out.println("================================================================");
        driver.switchTo().frame(driver.findElement(By.tagName(iframeName)));
        //driver.switchTo().frame(driver.findElement(By.cssSelector(iframeName)));

        String url = src.replace("blob:", "");

        String xpath = "//iframe[contains(@src, '" + url + "')]//button[contains(text(), 'Cancelar')]";
        By boton = By.xpath(xpath);
        click(boton);

    }

    public void clickConCambioIFrame() {//By Iframe,By boton) {

        /*
        // Esperar a que el iframe esté presente
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pdf-viewer")));
        // Cambiar al iframe
        driver.switchTo().frame(driver.findElement(Iframe));
        //driver.switchTo().frame(driver.findElement(By.id("pdf-viewer")));
        // Encontrar el botón
        wait.until(ExpectedConditions.presenceOfElementLocated(boton));
        WebElement botonCancelar = driver.findElement(boton);

        // Hacer clic en el botón
        botonCancelar.click();

         */

        // Esperar a que el iframe esté presente
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe[src*='edge_pdf/index.html']")));

            // Cambiar al iframe
            driver.switchTo().frame(iframe);

            // Esperar a que el botón "Cancelar" esté presente
            WebElement botonCancelar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']/span[text()='Cancelar']")));

            // Hacer clic en el botón "Cancelar"
            botonCancelar.click();
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("El iframe no se encontró dentro del tiempo especificado.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("El botón 'Cancelar' no se encontró dentro del iframe.");
        }
    }


    // Método para abrir una nueva pestaña y navegar a una URL
    public void abrirNuevaPestanaYNavegarA(String url) {
        try {
            // Ejecutar JavaScript para abrir una nueva pestaña
            ((JavascriptExecutor) driver).executeScript("window.open()");

            // Cambiar el foco al nuevo tab
            switchToNewTab();

            // Navegar a la URL proporcionada
            driver.get(url);
        } catch (WebDriverException e) {
            System.out.println("No se pudo abrir una nueva pestaña: " + e.getMessage());
        }
    }

    // Método para cambiar el foco al nuevo tab
    public void switchToNewTab() {
        // Obtener todas las ventanas o tabs abiertas
        for (String ventana : driver.getWindowHandles()) {
            // Cambiar el foco a la nueva ventana
            driver.switchTo().window(ventana);
            break; // Salir después de cambiar al nuevo tab
        }
    }

    // Método para cambiar el foco al nuevo tab
    public void switchToNewTab2() {
        // Obtener todas las ventanas o tabs abiertas
        Set<String> ventanas = driver.getWindowHandles();
        Iterator<String> iterador = ventanas.iterator();

        // Cambiar el foco a la nueva ventana
        while (iterador.hasNext()) {
            String nuevaVentana = iterador.next();
            driver.switchTo().window(nuevaVentana);
        }
    }

    // Método para validar la existencia de un campo por su XPath
    public boolean validarCampoExistente(By locator) {
        try {
            driver.findElement(locator);
            return true;// El campo existe
        } catch (NoSuchElementException e) {
            return false; // El campo no existe
        }
    }


// Método para posicionar el cursor sobre un elemento identificado por su XPath

    public void posicionarCursorEnElemento(By locator) {

        WebElement element = driver.findElement(locator);

        Actions actions = new Actions(driver);

        actions.moveToElement(element).perform();

    }

    public void comparadorTexto(By locator1, By locator2) {
        try {
            String texto1 = getText(locator1);
            System.out.println(texto1);
            String texto2 = getText(locator2);
            System.out.println(texto2);
            if (texto1.equals(texto2)) {
                System.out.println("Los textos coinciden: " + texto1);
            } else {
                System.out.println("Los textos NO coinciden.");
                System.out.println("Texto 1: " + texto1);
                System.out.println("Texto 2: " + texto2);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Uno de los elementos no fue encontrado.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al comparar los textos.");
        }
    }


    public void txtIguales(String texto1, String texto2) {


        if (texto1.equals(texto2)) {
            System.out.println("Las medidas del paquete estan bien.");
        } else {
            System.out.println("Las medidas del paquete estan mal.");
        }


        //return texto1.equals(texto2);
    }


    public void imprimirContenidoTabla(By tablaLocator) {
        WebElement tabla = driver.findElement(tablaLocator);

        // Localiza todas las filas de la tabla
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));

        // Itera sobre las filas para obtener los valores
        for (WebElement fila : filas) {
            // Localiza todas las celdas de la fila
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            // Imprime el texto de cada celda
            for (WebElement celda : celdas) {
                System.out.print(celda.getText() + " ");
            }
            System.out.println(); // Salto de línea entre filas
        }
    }

    public List<List<String>> obtenerContenidoTabla(By tablaLocator, String valorBuscado) {
        List<List<String>> contenidoTabla = new ArrayList<>();
        WebElement tabla = driver.findElement(tablaLocator);
        // Localiza todas las filas de la tabla
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        // Itera sobre las filas para obtener los valores
        for (WebElement fila : filas) {
            // Lista para almacenar las celdas de la fila actual
            List<String> celdasFila = new ArrayList<>();
            // Localiza todas las celdas de la fila
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            // Agrega el texto de cada celda a la lista de celdas de la fila actual
            for (WebElement celda : celdas) {
                String textoCelda = celda.getText();
                celdasFila.add(textoCelda);
                // Si la celda contiene el valor buscado, puedes hacer algo aquí
                if (textoCelda.equals(valorBuscado)) {
                    System.out.println("Valor encontrado en la tabla: " + textoCelda);
                    // Puedes realizar alguna acción específica, como romper el bucle o almacenar la fila que contiene el valor buscado
                }
            }
            // Agrega la lista de celdas de la fila actual a la lista de filas
            contenidoTabla.add(celdasFila);
        }
        return contenidoTabla;
    }

    public void sendKeys(String texto) {

        Actions actions = new Actions(driver);
        actions.sendKeys(texto).perform();
        //sendKeys(actions, texto);
    }

    public void sendEnter() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void sendBorrar(int repetir) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < repetir; i++) {
            actions.sendKeys(Keys.DELETE).perform();
        }

    }

    public void sendFlechaArriba(int repetir) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < repetir; i++) {
            actions.sendKeys(Keys.ARROW_UP).perform();
        }

    }

    public void sendFlechaAbajo(int repetir) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < repetir; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }

    }

    public void sendTab(int repetir) {
        Actions actions = new Actions(driver);
        int i;
        for (i = 0; i < repetir; i++) {
            actions.sendKeys(Keys.TAB).perform();
        }
    }

    public void scrollPageUpDown(int Up, int Down) {
        Actions actions = new Actions(driver);
        int i;
        for (i = 0; i < Up; i++) {
            actions.sendKeys(Keys.PAGE_UP).perform();
        }

        for (i = 0; i < Down; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).perform();
        }

    }

    public String generadorCorreos() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
        Random random = new Random();

        // Generar un nombre de usuario aleatorio
        String username = "user" + random.nextInt(100000);

        // Seleccionar un dominio aleatorio
        String domain = domains[random.nextInt(domains.length)];

        // Crear la dirección de correo electrónico aleatoria
        String email = username + "@" + domain;

        return email;
    }

    public String generadornombres() {
        Random random = new Random();
        // Generar un nombre de usuario aleatorio
        String username = "user" + random.nextInt(100000);
        return username;
    }

    public String generadorNombresReales() {
        String[] nombres = {
                "Juan", "María", "José", "Ana", "Luis", "Carmen", "Carlos", "Lucía", "Pedro", "Sofía",
                "Miguel", "Laura", "Jorge", "Elena", "Francisco", "Marta", "Antonio", "Isabel", "Manuel", "Paula"
        };

        Random random = new Random();
        // Generar un nombre latino aleatorio
        String nombre = nombres[random.nextInt(nombres.length)];
        return nombre;
    }

    public String generadorApellidosReales() {
        String[] apellidos = {
                "García", "Martínez", "Rodríguez", "López", "González", "Pérez", "Sánchez", "Ramírez", "Torres", "Flores",
                "Rivera", "Gómez", "Díaz", "Cruz", "Morales", "Ortiz", "Gutiérrez", "Chávez", "Ramos", "Vargas"
        };

        Random random = new Random();
        // Generar un apellido latino aleatorio
        String apellido = apellidos[random.nextInt(apellidos.length)];
        return apellido;
    }

    public String generadorNumeroTelefono() {
        Random random = new Random();
        // Generar los 8 dígitos restantes aleatorios
        int numeroAleatorio = 100000 + random.nextInt(900000);
        // Concatenar "3804" con los 8 dígitos aleatorios
        String numeroCompleto = "3804" + numeroAleatorio;
        return numeroCompleto;
    }

    public String caracteresAleatorios(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public String numerosAleatorios(int rangoCifra) {
        String characters = "012356789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(rangoCifra);

        for (int i = 0; i < rangoCifra; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public void selectIdAleatorio(String idElement, int valorMaximo, int valorMinimo) {
        // Encuentra el elemento select por su id
        WebElement selectElement = driver.findElement(By.id(idElement));

        // Crea una instancia de Select
        Select select = new Select(selectElement);

        // Genera un número aleatorio para seleccionar una opción
        Random random = new Random();

        // Selecciona la opción por su valor
        if (valorMaximo == valorMinimo) {
            System.out.println("Los 2 números son iguales: " + valorMaximo);
            select.selectByValue(String.valueOf(valorMaximo));
        } else {
            System.out.println("Los 2 números no son iguales.");
            int randomNumber = random.nextInt(valorMinimo, valorMaximo);
            select.selectByValue(String.valueOf(randomNumber));
        }


    }


    public String buscarElementoEnTabla(String numeroOrden) {

        WebElement registro = buscarRegistroPorNumeroOrden(driver, numeroOrden);

        if (registro != null) {
            System.out.println("Registro encontrado: " + registro.getText());
        } else {
            System.out.println("Registro no encontrado.");
        }


        String pesoYMedidas = null;
        if (registro != null) {
            // Encuentra la celda de la columna "Detalles" en la fila encontrada
            WebElement columnaDetalles = registro.findElement(By.xpath(".//td[8]"));
            pesoYMedidas = columnaDetalles.getText();
            System.out.println("==========================================");
            System.out.println("Peso y medidas del paquete " + numeroOrden + ": " + pesoYMedidas);
            System.out.println("==========================================");


        } else {
            System.out.println("Registro no encontrado.");
        }

        return (pesoYMedidas);

    }

    public WebElement buscarRegistroPorNumeroOrden(WebDriver driver, String numeroOrden) {
        // Encuentra la tabla
        WebElement tabla = driver.findElement(By.cssSelector("table.table-hover.mcr-table.table-responsive"));

        // Encuentra todas las filas de la tabla
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));

        // Itera sobre las filas para encontrar el número de orden
        for (WebElement fila : filas) {
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            for (WebElement celda : celdas) {
                if (celda.getText().equals(numeroOrden)) {
                    return fila;
                }
            }
        }
        return null; // Si no se encuentra el registro
    }


    public void buscarEnTabla(By locator) {
        // El número de seguimiento que quieres buscar
        String numeroSeguimientoBuscado = "00050021862GI6L5P0MC001";

        // Encontrar la tabla
        WebElement tabla = driver.findElement(locator);

        // Obtener todas las filas de la tabla
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));

        // Recorrer las filas y buscar el número de seguimiento
        boolean encontrado = false;
        for (WebElement fila : filas) {
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            for (WebElement celda : celdas) {
                if (celda.getText().equals(numeroSeguimientoBuscado)) {
                    encontrado = true;
                    System.out.println("Número de seguimiento encontrado: " + numeroSeguimientoBuscado);
                    break;
                }
            }
            if (encontrado) {
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Número de seguimiento no encontrado.");

        }
    }


    public void condicionalDeFunciones(By locator, Runnable funcion1, Runnable funcion2) {
        try {
            WebElement elemento = driver.findElement(locator);
            // Si el elemento se encuentra, ejecuta la función1
            funcion2.run();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Si el elemento no se encuentra, ejecuta la función2
            funcion1.run();
            funcion2.run();
        }
    }

    public void ejecutarCondicional(By locator, Runnable funcionSiEncuentra, Runnable funcionSiNoEncuentra) {
        try {
            // Intentar encontrar el elemento
            WebElement elemento = driver.findElement(locator);
            // Si el elemento se encuentra, ejecutar la función correspondiente
            funcionSiEncuentra.run();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Si el elemento no se encuentra, ejecutar la otra función
            funcionSiNoEncuentra.run();
        }
    }


    public void detallesTabla(String numeroOrden) {
        // Número de orden a buscar
        //String numeroOrden = "575";

        // Encuentra la fila que contiene el número de orden
        //WebElement filaOrden = driver.findElement(By.xpath("//td[text()='" + numeroOrden + "']/parent::tr"));

        WebElement filaOrden = driver.findElement(By.xpath("//tbody[2]//div[contains(.,'" + numeroOrden + "')]"));


        // Captura los detalles de la orden
        String detalles = filaOrden.findElement(By.xpath(".//td[9]")).getText(); // Ajusta el índice según la columna de detalles

        // Separa los detalles en variables
        String[] partes = detalles.split(" - ");
        String peso = partes[0].trim();
        String[] dimensiones = partes[1].split("x");
        String alto = dimensiones[0].trim();
        String largo = dimensiones[1].trim();
        String ancho = dimensiones[2].trim();

        // Imprime los detalles
        System.out.println("Peso: " + peso);
        System.out.println("Alto: " + alto);
        System.out.println("Largo: " + largo);
        System.out.println("Ancho: " + ancho);

        // Cierra el navegador
    }

    public void clickConEspera(By locator) {

        //WebDriverWait wait = new WebDriverWait(driver, tiempo);

        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public String extrarTextoDeUnLocalizador(String textoAMostrar, By locator) {
        WebElement orderNumberElement = driver.findElement(locator);
        String orderNumber = orderNumberElement.getText();
        System.out.println("==============================================================================================");
        System.out.println("" + textoAMostrar + ": " + orderNumber);
        System.out.println("==============================================================================================");
        return orderNumber;
    }

    public void recargar(int cantidadDeRecargas) {
        for (int i = 0; i < cantidadDeRecargas; i++) {
            System.out.println("La pagina se recargo: " + (i + 1));
            driver.navigate().refresh();
        }

    }

    public void hacerClickElementoExiste(By LocatorABuscar) {
        if (driver.findElements(LocatorABuscar).size() > 0)//(!driver.findElements(LocatorABuscar).isEmpty())
        {
            click(LocatorABuscar);
        } else {
            System.out.println("No se encontro el elemento");
        }
    }

    public void cambiarFocoPestana() {
        // Obtener todas las pestañas abiertas
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Cambiar el foco a la segunda pestaña
        driver.switchTo().window(tabs.get(1));

        // Realizar acciones en la segunda pestaña
        // driver.get(url);

        /* Funcion vieja para cambiar el foco a la segunda pestaña
        // Obtiene todas las ventanas abiertas
        Set<String> ventanas = driver.getWindowHandles();

        // Crea un iterador para recorrer las ventanas
        Iterator<String> iterador = ventanas.iterator();

        // Cambia el foco a la siguiente pestaña
        String nuevaPestana = iterador.next();
        driver.switchTo().window(nuevaPestana);

         */
    }

    public void leerPDF(String producto, String numeroSeguimientoBuscado) throws IOException {
        // Primero definimos numeroSeguimiento antes de usarlo

        String productoBuscado = producto;

        String numeroSeguimiento = numeroSeguimientoBuscado;
        String ultimoNumero = "";

        WebElement iframe = driver.findElement(By.tagName("iframe"));
        String src = iframe.getAttribute("src");
        System.out.println("src: " + src); // Depuración: imprimir el valor de src

        if (src.startsWith("blob:")) {
            // Usar JavaScript para obtener el contenido del blob
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String base64PDF = (String) js.executeScript(
                    "return new Promise((resolve, reject) => {" +
                            "  fetch(arguments[0]).then(response => response.blob()).then(blob => {" +
                            "    const reader = new FileReader();" +
                            "    reader.onloadend = () => resolve(reader.result.split(',')[1]);" +
                            "    reader.readAsDataURL(blob);" +
                            "  }).catch(error => reject(error));" +
                            "});", src);

            byte[] pdfBytes = Base64.getDecoder().decode(base64PDF);

            try (PDDocument document = Loader.loadPDF(new RandomAccessReadBuffer(pdfBytes))) {
                PDFTextStripper stripper = new PDFTextStripper();
                String textoExtraido = stripper.getText(document);
                //System.out.println("Texto del PDF:\n" + textoExtraido);

                // Manipular el texto extraído para omitir el número variable y las dos últimas líneas
                String[] lineas = textoExtraido.split("\n");

                /*
                int numeroLineas = lineas.length;
                System.out.println("Numero de lineas del PDF: " + numeroLineas);
                 */
                StringBuilder textoModificado = new StringBuilder();

                // Aquí extraemos el número de seguimiento y construimos el texto modificado
                for (int i = 0; i < lineas.length - 2; i++) {
                    if (i == lineas.length - 4) {
                        numeroSeguimiento = lineas[i].trim(); // Extraer el número de seguimiento
                    } else if (i == lineas.length - 3) {
                        ultimoNumero = lineas[i].trim(); // Extraer el último número (5)
                    } else if (!lineas[i].trim().matches("\\d+")) {
                        textoModificado.append(lineas[i].trim()).append("\n");


                        // Añadir un salto de línea adicional después de CP: 4449
                        if (lineas[i].trim().startsWith("CP: ")) {
                            textoModificado.append("\n");
                        }
                    }
                }

                // Añadir el número de seguimiento y el último número
                textoModificado.append(numeroSeguimiento).append("\n");
                textoModificado.append(ultimoNumero);

                System.out.println("Texto del PDF modificado:\n" + textoModificado.toString());

                // Ahora definimos textoEsperado de la misma manera que construimos textoModificado
                String textoEsperado = producto + "\n" +
                        "A0007 0005002324\n" +
                        "Gral Juan B\n" +
                        "Peyrotti 100 Apolinario Saravia\n" +
                        "CP: 4449\n\n\n" +
                        numeroSeguimiento + "\n" +
                        ultimoNumero;

                System.out.println("\nTexto esperado:\n" + textoEsperado);

                //validar texto
                Assert.assertEquals(textoModificado.toString(), textoEsperado);

                /*
                // Validar texto
                if (!textoModificado.toString().equals(textoEsperado)) {
                    throw new AssertionError("El texto esperado no se encontró en el PDF");
                }


                 */
                PDFRenderer renderer = new PDFRenderer(document);
                BufferedImage image = renderer.renderImageWithDPI(0, 300); // Primera página, 300 DPI

                if (image == null) {
                    throw new IllegalArgumentException("La imagen no puede ser null");
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = new MultiFormatReader().decode(bitmap);

                String contenidoQR = result.getText();
                System.out.println("\n==============================================================================================");
                System.out.println("Contenido del QR: " + contenidoQR);
                System.out.println("==============================================================================================");

                /*
                if (!contenidoQR.contains("https://")) {
                    throw new AssertionError("El QR no contiene la URL esperada.");
                }

                 */
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("El atributo src del iframe no contiene una cadena base64 válida");
        }

    }

    public void capturarPantalla() {
        Hooks.takeScreenShot(false);
        System.out.println("Capturando pantalla");
    }
    //funciones de los Ecommerce

    public String extrarNumeroDePedido(By locator) {
        WebElement orderNumberElement = driver.findElement(locator);
        String orderNumber = orderNumberElement.getText();
        System.out.println("El número de orden es: " + orderNumber);
        return orderNumber;
    }

    public void cambioDeIframe() {
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name='modal-create-product-iframe']")));
    }

}







