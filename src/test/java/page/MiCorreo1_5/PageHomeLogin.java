package page.MiCorreo1_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.BasePage;

public class PageHomeLogin extends BasePage {

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


    public PageHomeLogin(WebDriver driver) {
        super(driver);
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
}
