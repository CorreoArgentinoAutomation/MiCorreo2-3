package page.MiCorreo1_5;

import org.apache.xmlbeans.SchemaTypeLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageHomeLogin extends BasePage {

    PageForm pageForm;

    public By emailLocator = By.id("email");
    public By passwordLocator = By.id("password");
    public By btnLogin = By.xpath("//button[@type=\"submit\" and @onclick=\"ValidacionLogin(event)\"]\n");
    private By miCuentaLocator = By.xpath("//span[normalize-space()='Mi cuenta']");
    private By btnlogOut = By.xpath("//a[@class='dropdown-item']");
    //Registro
    private By btnRegistrarme = By.xpath("//a[.='Registrarme']");
    private By campoNombre = By.xpath("//input[@id='name']");
    private By campoApellido = By.xpath("//input[@id='lastname']");
    private By comboTipo = By.xpath("//select[@id='doc_type']/option[@value='1']");
    private By campoDocumento = By.xpath("//input[@id='doc_number']");
    private By comboRubro = By.xpath("//select[@id='rubro']");
    private By opcionArte = By.xpath("//option[contains(.,'Arte y Música')]");
    private By campoCelular = By.xpath("//input[@id='celular']");
    private By campoEmail = By.xpath("(//input[@id='email'])[3]");
    private By campoPassWord = By.xpath("//input[@id='password_register']");
    private By campoPassWordConfirm = By.xpath("//input[@id='password_confirm']");
    private By checkTerms = By.xpath("//input[@id='terms']");
    private By btnSiguiente = By.xpath("//button[@id='btn-next-step']");
    private By comboProvincia = By.xpath("//select[@id='provincia']");
    private By opcionCapitalFederal = By.xpath("//option[.='CAPITAL FEDERAL']");
    private By combolocalidad = By.xpath("//select[@id='localidad']");
    private By opcionlocalidad = By.xpath("//option[.='CIUDAD AUTONOMA BUENOS AIRES (CAPITAL FEDERAL)']");
    private By campoDireccion = By.xpath("//input[@id='direccion']");
    private By campoCodigoPostal = By.xpath("//input[@id='cp']");
    private By btnRegistrarmeModal = By.xpath("//button[@id='btn-register-fisica']");
    private By btnAgregarUsuario = By.xpath("//a[@class='dropdown-item' and normalize-space(text())='Agregar Usuarios']");
    //private By btnMisUsuarios = By.xpath("//a[contains(text(), 'Mis Usuarios') and contains(@class, 'nav-link')]");
    private By btnSeleccionarUsuario = By.xpath("//select[contains(@class, 'form-control') and contains(@id, 'sub_')]");
    //private By msjErrorAgregarUI = By.xpath("//small[contains(@class, 'text-danger') and contains(text(), 'El Rol seleccionado')]");
    private By selectorRol = By.xpath("//select[@class='form-control cmbRoles' and @id='sub_2732']");
    //private By selectorRolDinamico = By.xpath("//select[contains(@id, 'sub_')]");
    private By btnMisUsuarios = By.xpath("//a[@id='tab2' and contains(@class, 'nav-link') and @href='#panel2']");
    private By btnCampoUsuarioInvitado = By.xpath("//td[@class='table-text text-center']/div[contains(text(), '@yopmail.com')]");
    //private By btnSeleccionarUsuario = By.xpath("//select[@id='sub_2732']");
    private By msjErrorAgregarUI = By.xpath("//small[@id='error' and @class='text-danger fw-semibold']");

    //Recargar saldo
    private By btnRecargarSaldo = By.xpath("//a[normalize-space(text())='Recargar saldo']");
    private By checkMercadoPago = By.xpath("(//label[@id='mercadopago'])[1]");
    private By checkTarjeta = By.xpath("(//input[@id='radioTarjeta'])[1]");
    private By btnRecargarSaldoModalValor = By.xpath("//input[@id='valorcargaModal']");
    private By saldo = By.xpath("//div[contains(@class,'modal-dialog') and contains(@class,'modal-dialog-centered')]//div[contains(@class,'saldoActual')]");

    private By btnSiguienteModal = By.xpath("//form[@id='cargaSaldoModal']//button[@class='btn btn-correo-primary']");

    public PageHomeLogin(WebDriver driver) {
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

    public void loginOutline(String tipoUsuario) {
        String email;
        String password = "123123"; // Contraseña común para todos los tipos de usuario
        boolean loginExitoso = false;
        String expectedUrl = "https://wcpzt.correo.local/MiCorreo/public/message-home";
        int intentos = 0;
        int maxIntentos = 2; // Establece el número máximo de intentos


        if (tipoUsuario.equals("Consumidor final")) {
            email = "cf_tester02@yopmail.com";
        } else if (tipoUsuario.equals("Monotributista")) {
            email = "mono_tester@yopmail.com";
        } else if (tipoUsuario.equals("Empresa")) {
            email = "empctacte_test@yopmail.com";
        } else if (tipoUsuario.equals("Usuario Invitado")) {
            email = "empctacte_testcp@yopmail.com";
        } else if (tipoUsuario.equals("Franquicia tipo 2")) {
            email = "A0007@correoargentino.com.ar";
            password = "044495";
        } else if (tipoUsuario.equals("usuario ED")) {
            email = "edcf08qa@yopmail.com";
            password = "Test1234!";
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + tipoUsuario);
        }

        while (!loginExitoso && intentos < maxIntentos) {
            try {
                // Llenar campos de email y contraseña con las credenciales específicas
                writeText(emailLocator, email);
                writeText(passwordLocator, password);
                click(btnLogin);

                // Esperar a que la URL sea la esperada después del inicio de sesión
                waitForUrlToBe(expectedUrl, 2);

                // Verificar si la URL es la esperada
                String currentUrl = getCurrentURL(); // Utilizando la función encapsulada
                if (currentUrl.equals(expectedUrl)) {
                    // Si estamos en la página principal, el inicio de sesión es exitoso
                    System.out.println("¡Inicio de sesión exitoso!");
                    System.out.println("Estamos en la página principal (message-home).");
                    loginExitoso = true;
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
        if (!loginExitoso) {
            System.out.println("Inicio de sesión fallido después de " + maxIntentos + " intentos.");
        }
    }


    public void logout(){
        click(miCuentaLocator);
        waitForSeconds(1);
        clickLastElementInDropdown(btnlogOut);
        waitForSeconds(3);
        System.out.println("Logout Exitoso!");
    }

    public void registro(){
        click(btnRegistrarme);
        waitForSeconds(1);
        System.out.println("Abre la Ventana Modal con el formulario de registro");

    }

    public void llenarFormulario(String email) {
        writeText(campoNombre, "Test");
        writeText(campoApellido, "Test");
        selectOptionFromDropdownByValue("doc_type", "1");

        String numeroDocumento = numerosAleatorios(8);
        writeText(campoDocumento, numeroDocumento);
        selectOptionFromDropdownByValue("rubro", "1");

        writeText(campoCelular, "123456789");

        //aca le tengo que pasar el email temporal
        writeText(campoEmail, email);
        System.out.println("El Correo ingresado para el registro es: "+email);

        writeText(campoPassWord, "123123");
        writeText(campoPassWordConfirm, "123123");

        click(checkTerms);
        click(btnSiguiente);
        waitForSeconds(1);

        click(comboProvincia);
        click(opcionCapitalFederal);

        click(combolocalidad);
        click(opcionlocalidad);

        writeText(campoDireccion, "Av. Test");
        writeText(campoCodigoPostal, "1020");
        click(btnRegistrarmeModal);

        waitForSeconds(15);
    }

    /*public void miCuentaUI() {
        click(miCuentaLocator);
        click(btnAgregarUsuario);
        click(btnMisUsuarios);
        System.out.println("Step completado");
    }*/

    // Método para agregar el rol al usuario
    /*public void agregarUI(String rolDeseado) {
        try {
            System.out.println("El nombre de la variable rolDeseado es: " + rolDeseado);
            miCuentaUI(); // Navegar a la sección de usuarios

            // Verificar si el usuario ya tiene el rol deseado
            By usuarioRolLocator = By.xpath("//td[contains(text(),'"+rolDeseado+"')]");
            if (isElementPresent(usuarioRolLocator)) {
                System.out.println("El usuario ya tiene asignado el rol: " + rolDeseado);
                return;
            }

            // Seleccionar el rol deseado en el combo
            WebElement comboRol = findElement(btnSeleccionarUsuario);
            Select select = new Select(comboRol);
            select.selectByVisibleText(rolDeseado);
            System.out.println("Rol cambiado a: " + rolDeseado);

            // Verificar si aparece el mensaje de error
            if (isElementPresent(msjErrorAgregarUI)) {
                String mensajeError = getText(msjErrorAgregarUI);
                System.out.println("Error al cambiar el rol: " + mensajeError);
            } else {
                System.out.println("Rol asignado correctamente.");
            }

        } catch (Exception e) {
            System.out.println("Error en agregarUI: " + e.getMessage());
        }
    }
*/

    public void recargarSaldo(){
        click(btnRecargarSaldo);
    }

    public void valorDeSaldo(){
        getText(saldo);
        System.out.println("Valor de saldo: " + getText(saldo));
        //return getText(saldo);

    }

    public void seleccionarMedioPago(String medioPago,String valorRecarga){

        System.out.println("Seleccionó el medio de pago: " + medioPago);

        //String valorDeSaldo = valorDeSaldo();
        limpiarFormatoDeSaldo(saldo);

        switch (medioPago) {
            case "Mercado Pago":
                click(checkMercadoPago);
                flujoComun(valorRecarga);
                pageForm.pagoConMercadoPago();
                break;
            case "Tarjeta":
                click(checkTarjeta);
                flujoComun(valorRecarga);
                pageForm.pagoConTarjeta();
                break;
            default:
                System.out.println("Ese medio de pago no existe.");
                break;
        }
        waitForSeconds(2);
        capturarPantalla();
    }

    public void flujoComun(String valorRecarga){
        waitForSeconds(1);
        writeText(btnRecargarSaldoModalValor, valorRecarga);
        waitForSeconds(1);
        click(btnSiguienteModal);
        waitForSeconds(3);
    }


    // Método para verificar si un elemento está presente
    private boolean isElementPresent(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void validarMontosDeCarga(){


    }



    /*public void miCuentaUI() {
        click(miCuentaLocator);
        click(btnAgregarUsuario);
        click(btnMisUsuarios);
        System.out.println("step completado");
    }*/



    /*public MetodoAgregarUI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }*/




    /*public void agregarUI(String rolDeseado) {
        System.out.println("El nombre de la variable rol deseado es: " + rolDeseado);

        try {
            click(miCuentaLocator);
            click(btnAgregarUsuario);
            click(btnMisUsuarios);
            System.out.println("Accedí a la pestaña de usuarios");

            // Esperamos que el selector de roles esté visible
            WebElement comboRol = wait.until(ExpectedConditions.visibilityOfElementLocated(selectorRolDinamico));

            // Verificamos si es un <select>
            if (comboRol.getTagName().equalsIgnoreCase("select")) {
                Select select = new Select(comboRol);
                select.selectByVisibleText(rolDeseado);
                System.out.println("Rol seleccionado: " + rolDeseado);
            } else {
                // Si no es un <select>, intentamos un clic manual
                comboRol.click();
                WebElement opcionRol = findElement(By.xpath("//option[text()='" + rolDeseado + "']"));
                opcionRol.click();
                System.out.println("Rol seleccionado (método alternativo): " + rolDeseado);
            }

            // Verificamos si aparece un mensaje de error
            if (isElementPresent(msjErrorAgregarUI)) {
                String mensajeError = getText(msjErrorAgregarUI);
                System.out.println("Mensaje de error capturado: " + mensajeError);
            } else {
                System.out.println("Rol asignado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar agregar el rol: " + e.getMessage());
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }*/

    public void miCuentaUI(){
        click(miCuentaLocator);
        click(btnAgregarUsuario);
        click(btnMisUsuarios);
        click(btnCampoUsuarioInvitado);
        click(btnSeleccionarUsuario);
        waitForSeconds(1);

        System.out.printf("step completado");
    }

    public void agregarUI(String rolDeseado) {

        System.out.println("Antes del Try");

        System.out.printf("el nombre de la variable rol deseado es: " + rolDeseado);

        try {

            /*
            click(miCuentaLocator);
            click(btnAgregarUsuario);
            click(btnMisUsuarios);
            */

            //esestos xpath no van aca
            By usuarioConRolLocator = By.xpath("//td[contains(text(),'Operador con Pago') or contains(text(),'Operador sin Pago')]");
            By usuarioSinRolLocator = By.xpath("//td[not(contains(text(),'Operador con Pago')) and not(contains(text(),'Operador sin Pago'))]");
            waitForSeconds(1);
            String rolActual = getText(usuarioConRolLocator);

            if (rolActual.equals(rolDeseado)) {
                System.out.println("El usuario ya tiene asignado el rol: " + rolActual);
                return;
            }


            System.out.println("Rol actual: " + rolActual + ". Procediendo a cambiarlo a: " + rolDeseado);


            By selectorRol = By.xpath("//select[@id='sub_2732']");
            WebElement comboRol = findElement(selectorRol);
            Select select = new Select(comboRol);


            select.selectByVisibleText(rolDeseado);
            System.out.println("Rol cambiado a: " + rolDeseado);

        } catch (Exception e) {
            System.out.println("Error al intentar agregar el rol: " + e.getMessage());
        }

        System.out.println("Despues del try");
    }

    /*public void MsjErrorAgregarUI(){

    }*/


}
