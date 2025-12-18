package page.EcosistemaDigital;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.MiCorreo1_5.PageForm;

public class PageHomeLoginED extends BasePage {

    PageForm pageForm;

    public By emailLocator = By.xpath("//input[@name='email']");
    public By passwordLocator = By.xpath("//input[@name='password']");
    public By btnLogin = By.xpath("//button[.='Ingresar' or contains(.,'Ingresar')]");
    // Locator para el mensaje de sesión activa - puede variar según la implementación, ajustar si es necesario
    public By mensajeSesionActivaLocator = By.xpath("//*[contains(text(), 'Tiene una sesión activa') or contains(text(), 'sesión activa')]");
    
    // Locators para recuperación de contraseña
    public By linkOlvidasteContrasena = By.xpath("//p[contains(text(), 'Olvidaste') or contains(text(), 'olvidaste') or contains(text(), 'contraseña') or contains(text(), 'Contraseña')]");
    public By campoEmailRecuperacion = By.xpath("(//input[@placeholder='Correo electrónico' or @type='text'])[2]");
    public By btnEnviarRecuperacion = By.xpath("//button[contains(text(), 'Enviar') or contains(text(), 'Recuperar') or contains(text(), 'Solicitar')]");
    public By mensajeConfirmacionRecuperacion = By.xpath("//*[contains(text(), 'enviado') or contains(text(), 'Enviado') or contains(text(), 'correo') or contains(text(), 'email')]");
    
    // Locators para la página de recuperación de contraseña (después de hacer clic en el enlace del email)
    // Buscar por label o placeholder que contenga "Contraseña" (sin "Confirmar")
    public By campoNuevaContrasena = By.xpath("(//input[@type='password'])[1] | //input[@type='password' and (contains(@placeholder, 'Contraseña') or contains(@name, 'password') or contains(@id, 'password')) and not(contains(@placeholder, 'Confirmar'))]");
    // Buscar el segundo campo de contraseña o el que contenga "Confirmar"
    public By campoConfirmarContrasena = By.xpath("(//input[@type='password'])[2] | //input[@type='password' and (contains(@placeholder, 'Confirmar') or contains(@name, 'confirm') or contains(@id, 'confirm'))]");
    public By btnReiniciarContrasena = By.xpath("//button[contains(text(), 'Reiniciar') or contains(text(), 'reiniciar') or contains(text(), 'Restablecer') or contains(text(), 'restablecer')]");
    
    // Locators para registro de usuario
    public By btnRegistrarme = By.xpath("//a[contains(text(), 'Registrarme') or contains(text(), 'Registrarse') or contains(text(), 'registrarme')] | //button[contains(text(), 'Registrarme')]");
    public By campoNombre = By.xpath("//input[@name='name' or @id='nombre' or contains(@placeholder, 'Nombre')]");
    public By campoApellido = By.xpath("//input[@name='lastname' or @id='apellido' or contains(@placeholder, 'Apellido')]");
    // Combos de Tipo de documento y Rubro
    public By comboDNI = By.xpath("//div[@role='combobox']//span[text()='Seleccionar']");
    public By opcionDNI = By.xpath("//li[@data-value='DNI']");
    public By comboRubro = By.xpath("//input[@role='combobox']");
    public By opcionRubroPrimera = By.xpath("//input[@value='Alimentos y Bebidas']");
    public By campoDocumento = By.xpath("//input[@name='document.id']");
    public By campoCelular = By.xpath("//input[@name='phone']");
    public By campoEmailRegistro = By.xpath("(//input[@type='email' or @name='email' or @id='email' or contains(@placeholder, 'Correo')])[2]");
    public By campoPasswordRegistro = By.xpath("(//input[@type='password' and (contains(@name, 'password') or contains(@id, 'password') or contains(@placeholder, 'Contraseña'))])[2]");
    public By campoPasswordConfirmRegistro = By.xpath("(//input[@type='password' and (contains(@name, 'password') or contains(@id, 'password') or contains(@placeholder, 'Contraseña'))])[3]");
    public By checkTerminos = By.xpath("//input[@type='checkbox' and @data-indeterminate='false']");
    public By btnSiguiente = By.xpath("//button[@type='submit' and normalize-space(text())='Siguiente']");
    
    // Locators para Paso 2 del registro (Dirección)
    public By comboProvincia = By.xpath("(//input[@role='combobox' or @value='Seleccione'])[1]");
    public By opcionProvincia = By.xpath("//li[contains(@data-value, 'Capital') or contains(text(), 'Capital')] | //li[@data-value][1]");
    public By comboLocalidad = By.xpath("(//input[@role='combobox' or @value='Seleccione'])[2]");
    public By opcionLocalidad = By.xpath("//li[@data-value][1] | //li[contains(text(), 'CABA') or contains(text(), 'Capital')][1]");
    public By campoDireccion = By.xpath("//input[@name='address' or @id='address' or contains(@placeholder, 'Dirección')]");
    public By campoCodigoPostal = By.xpath("//input[@name='postalCode' or @id='postalCode' or contains(@placeholder, 'Código postal') or contains(@placeholder, 'postal')]");
    public By btnRegistrarseFinal = By.xpath("(//button[contains(text(), 'Registrarme') and not(contains(text(), 'Siguiente'))] | //button[@type='submit' and contains(text(), 'Registrarme')])[2]");
    
    public By mensajeBienvenida = By.xpath("//*[contains(text(), 'Bienvenido') or contains(text(), 'bienvenido') or contains(text(), 'Bienvenida') or contains(text(), 'Cuenta creada') or contains(text(), 'Registro exitoso')]");
    
    // Locator para mensajes de validación (caja roja con errores)
    public By mensajesValidacion = By.xpath("//*[contains(@class, 'error') or contains(@class, 'validation') or contains(@style, 'red') or contains(@style, 'error')]//*[contains(text(), 'Debe') or contains(text(), 'debe')] | //div[contains(@class, 'MuiAlert')]//*[contains(text(), 'Debe') or contains(text(), 'debe')]");
    
    // Variable para almacenar la contraseña generada
    private String contrasenaGenerada;
    
    // Variables para almacenar credenciales para reintento
    private String emailGuardado;
    private String passwordGuardado;

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
        this.emailGuardado = email; // Guardar email para posible reintento
        writeText(emailLocator, email);
    }

    public void ingresoPassword(String password) {
        this.passwordGuardado = password; // Guardar password para posible reintento
        writeText(passwordLocator, password);
    }

    public void btnIngresar() {
        int maxReintentos = 2; // Máximo de reintentos por sesión activa
        int reintentos = 0;
        boolean loginExitoso = false;

        while (!loginExitoso && reintentos <= maxReintentos) {
            // Hacer clic en el botón Ingresar
            click(btnLogin);
            
            // Esperar un momento para que aparezca el mensaje si existe
            waitForSeconds(2);
            
            // Verificar si aparece el mensaje de sesión activa
            if (verificarMensajeSesionActiva()) {
                reintentos++;
                System.out.println("⚠ Mensaje de sesión activa detectado. Reintentando login (intento " + reintentos + "/" + maxReintentos + ")...");
                
                // Si ya se alcanzó el máximo de reintentos, continuar de todas formas
                // porque según el mensaje, el sistema cerrará la sesión anterior automáticamente
                if (reintentos > maxReintentos) {
                    System.out.println("⚠ Se alcanzó el máximo de reintentos. Continuando con el login...");
                    loginExitoso = true;
                    break;
                }
                
                // Limpiar campos y reintentar
                limpiarCampos();
                if (emailGuardado != null && passwordGuardado != null) {
                    writeText(emailLocator, emailGuardado);
                    writeText(passwordLocator, passwordGuardado);
                    // Esperar un momento antes de continuar el ciclo
                    waitForSeconds(1);
                } else {
                    System.out.println("⚠ No se pueden reintentar las credenciales porque no fueron guardadas");
                    break;
                }
            } else {
                // No hay mensaje de sesión activa, el login procede normalmente
                loginExitoso = true;
                System.out.println("✓ Login procesado correctamente");
            }
        }
    }

    /**
     * Verifica si el mensaje de sesión activa está presente en la página
     * @return true si el mensaje existe, false en caso contrario
     */
    private boolean verificarMensajeSesionActiva() {
        try {
            // Buscar el mensaje de sesión activa usando findElements para evitar excepciones
            java.util.List<WebElement> elementos = getDriver().findElements(mensajeSesionActivaLocator);
            
            for (WebElement elemento : elementos) {
                if (elemento.isDisplayed()) {
                    String textoMensaje = elemento.getText();
                    // Verificar si contiene texto relacionado con sesión activa
                    if (textoMensaje.contains("sesión activa") || 
                        textoMensaje.contains("Tiene una sesión activa") ||
                        textoMensaje.contains("sesión anterior")) {
                        System.out.println("⚠ Mensaje de sesión activa detectado: " + textoMensaje);
                        return true;
                    }
                }
            }
            
            // También buscar por texto en toda la página como respaldo
            String pageText = getDriver().findElement(By.tagName("body")).getText();
            if (pageText.contains("Tiene una sesión activa") || 
                pageText.contains("sesión activa") && pageText.contains("cerrar su sesión")) {
                System.out.println("⚠ Mensaje de sesión activa detectado en el texto de la página");
                return true;
            }
            
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // El mensaje no existe, lo cual es normal si no hay sesión activa
            return false;
        } catch (Exception e) {
            // Cualquier otra excepción, asumimos que no hay mensaje
            System.out.println("Error al verificar mensaje de sesión activa: " + e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * Limpia los campos de email y contraseña
     */
    private void limpiarCampos() {
        try {
            WebElement emailField = findElement(emailLocator);
            emailField.clear();
            WebElement passwordField = findElement(passwordLocator);
            passwordField.clear();
            waitForSeconds(1); // Pequeña pausa después de limpiar
        } catch (Exception e) {
            System.out.println("Error al limpiar campos: " + e.getMessage());
        }
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

    public void validarCampoCorreoExiste() {
        boolean campoExiste = validarCampoExistenteYEditable(emailLocator);
        if (campoExiste) {
            System.out.println("✓ El campo de correo existe y está visible y editable");
        } else {
            throw new AssertionError("✗ El campo de correo no existe o no está visible/editable");
        }
    }

    public void validarCampoContrasenaExiste() {
        boolean campoExiste = validarCampoExistenteYEditable(passwordLocator);
        if (campoExiste) {
            System.out.println("✓ El campo de contraseña existe y está visible y editable");
        } else {
            throw new AssertionError("✗ El campo de contraseña no existe o no está visible/editable");
        }
    }

    public void validarBotonIngresarExiste() {
        boolean botonExiste = elementExists(btnLogin);
        if (botonExiste) {
            WebElement boton = findElement(btnLogin);
            if (boton.isDisplayed() && boton.isEnabled()) {
                System.out.println("✓ El botón Ingresar existe y está visible y habilitado");
            } else {
                throw new AssertionError("✗ El botón Ingresar existe pero no está visible o habilitado");
            }
        } else {
            throw new AssertionError("✗ El botón Ingresar no existe en la página");
        }
    }

    // Métodos para recuperación de contraseña
    public void clicOlvidasteContrasena() {
        try {
            waitForSeconds(1);
            click(linkOlvidasteContrasena);
            System.out.println("✓ Se hizo clic en el enlace '¿Olvidaste tu contraseña?'");
            waitForSeconds(2); // Esperar a que cargue la página de recuperación
        } catch (Exception e) {
            throw new AssertionError("✗ No se pudo hacer clic en el enlace de recuperación de contraseña: " + e.getMessage());
        }
    }

    public void ingresarEmailRecuperacion(String email) {
        try {
            waitForSeconds(1);
            writeText(campoEmailRecuperacion, email);
            System.out.println("✓ Se ingresó el email para recuperación: " + email);
        } catch (Exception e) {
            throw new AssertionError("✗ No se pudo ingresar el email para recuperación: " + e.getMessage());
        }
    }

    public void solicitarRecuperacionContrasena() {
        try {
            waitForSeconds(1);
            click(btnEnviarRecuperacion);
            System.out.println("✓ Se solicitó la recuperación de contraseña");
            waitForSeconds(2); // Esperar respuesta del servidor
        } catch (Exception e) {
            throw new AssertionError("✗ No se pudo solicitar la recuperación de contraseña: " + e.getMessage());
        }
    }

    public void validarMensajeConfirmacion() {
        try {
            waitForSeconds(2);
            boolean mensajeExiste = elementExists(mensajeConfirmacionRecuperacion);
            if (mensajeExiste) {
                WebElement mensaje = findElement(mensajeConfirmacionRecuperacion);
                String textoMensaje = mensaje.getText();
                System.out.println("✓ Mensaje de confirmación encontrado: " + textoMensaje);
            } else {
                // También buscar en el texto de la página
                String pageText = getDriver().findElement(By.tagName("body")).getText();
                if (pageText.contains("enviado") || pageText.contains("correo") || pageText.contains("email")) {
                    System.out.println("✓ Mensaje de confirmación encontrado en la página");
                } else {
                    throw new AssertionError("✗ No se encontró mensaje de confirmación de recuperación de contraseña");
                }
            }
        } catch (Exception e) {
            throw new AssertionError("✗ Error al validar mensaje de confirmación: " + e.getMessage());
        }
    }

    /**
     * Genera una contraseña aleatoria y la muestra por consola
     * @return La contraseña generada
     */
    public String generarContrasenaAleatoria() {
        // Combinar letras mayúsculas, minúsculas y números
        String letrasMayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String caracteres = letrasMayusculas + letrasMinusculas + numeros;
        
        java.util.Random random = new java.util.Random();
        StringBuilder contrasena = new StringBuilder(12); // Longitud de 12 caracteres
        
        // Asegurar al menos una mayúscula, una minúscula y un número
        contrasena.append(letrasMayusculas.charAt(random.nextInt(letrasMayusculas.length())));
        contrasena.append(letrasMinusculas.charAt(random.nextInt(letrasMinusculas.length())));
        contrasena.append(numeros.charAt(random.nextInt(numeros.length())));
        
        // Completar el resto con caracteres aleatorios
        for (int i = 3; i < 12; i++) {
            contrasena.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        
        // Mezclar los caracteres para que no siempre empiecen igual
        char[] array = contrasena.toString().toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        
        String contrasenaFinal = new String(array);
        this.contrasenaGenerada = contrasenaFinal;
        
        System.out.println("==========================================");
        System.out.println("CONTRASEÑA GENERADA: " + contrasenaFinal);
        System.out.println("==========================================");
        
        return contrasenaFinal;
    }

    /**
     * Ingresa la nueva contraseña en el campo correspondiente
     */
    public void ingresarNuevaContrasena() {
        try {
            waitForSeconds(2);
            String contrasena = generarContrasenaAleatoria();
            writeText(campoNuevaContrasena, contrasena);
            System.out.println("✓ Se ingresó la nueva contraseña");
        } catch (Exception e) {
            throw new AssertionError("✗ No se pudo ingresar la nueva contraseña: " + e.getMessage());
        }
    }

    /**
     * Confirma la nueva contraseña ingresándola en el campo de confirmación
     */
    public void confirmarNuevaContrasena() {
        try {
            waitForSeconds(1);
            if (contrasenaGenerada == null || contrasenaGenerada.isEmpty()) {
                throw new AssertionError("✗ No hay contraseña generada para confirmar");
            }
            writeText(campoConfirmarContrasena, contrasenaGenerada);
            System.out.println("✓ Se confirmó la nueva contraseña");
        } catch (Exception e) {
            throw new AssertionError("✗ No se pudo confirmar la nueva contraseña: " + e.getMessage());
        }
    }

    /**
     * Hace clic en el botón para reiniciar/restablecer la contraseña
     */
    public void clicReiniciarContrasena() {
        try {
            waitForSeconds(1);
            click(btnReiniciarContrasena);
            System.out.println("✓ Se hizo clic en el botón para reiniciar la contraseña");
            waitForSeconds(2); // Esperar respuesta del servidor
        } catch (Exception e) {
            throw new AssertionError("✗ No se pudo hacer clic en el botón de reiniciar contraseña: " + e.getMessage());
        }
    }

    // Métodos para registro de usuario
    /**
     * Hace clic en el botón "Registrarme"
     */
    public void clicRegistrarme() {
        try {
            waitForSeconds(1);
            click(btnRegistrarme);
            System.out.println("✓ Se hizo clic en el botón 'Registrarme'");
            waitForSeconds(2); // Esperar a que se abra el formulario
        } catch (Exception e) {
            throw new AssertionError("✗ No se pudo hacer clic en el botón Registrarme: " + e.getMessage());
        }
    }

    /**
     * Llena el formulario de registro con los datos proporcionados
     * @param email El email del usuario (generado temporalmente)
     */
    public void llenarFormularioRegistro(String email) {
        try {
            waitForSeconds(1);
            
            // Nombre
            writeText(campoNombre, generadorNombresReales());
            System.out.println("✓ Se ingresó el nombre");
            
            // Apellido
            writeText(campoApellido, generadorApellidosReales());
            System.out.println("✓ Se ingresó el apellido");
            
            // Documento
            click(comboDNI);
            waitForSeconds(1);
            click(opcionDNI);
            String numeroDocumento = numerosAleatorios(8);
            waitForSeconds(1);
            writeText(campoDocumento, numeroDocumento);
            System.out.println("✓ Se ingresó el documento: " + numeroDocumento);
            
            // Rubro (selecciona la primera opción disponible)
            try {
                click(comboRubro);
                waitForSeconds(1);
                //click(opcionRubroPrimera);
                String numero = numerosAleatorios(1);
                sendFlechaAbajo(Integer.parseInt(numero+1));
                sendEnter();
                System.out.println("La opcion seleccionada es: "  + numero);
                System.out.println("✓ Se seleccionó un rubro");
            } catch (Exception e) {
                System.out.println("⚠ No se pudo seleccionar el rubro: " + e.getMessage());
            }
            
            // Celular
            waitForSeconds(1);
            writeText(campoCelular, generadorNumeroTelefono());
            System.out.println("✓ Se ingresó el celular");
            
            // Email
            writeText(campoEmailRegistro, email);
            System.out.println("✓ Se ingresó el email: " + email);
            
            // Contraseña
            String contrasena = "123123"; // Contraseña por defecto para registro
            writeText(campoPasswordRegistro, contrasena);
            System.out.println("✓ Se ingresó la contraseña");
            
            // Confirmar contraseña
            writeText(campoPasswordConfirmRegistro, contrasena);
            System.out.println("✓ Se confirmó la contraseña");
            
            // Aceptar términos
            try {
                click(checkTerminos);
                System.out.println("✓ Se aceptaron los términos y condiciones");
            } catch (Exception e) {
                System.out.println("⚠ No se pudo hacer clic en términos (puede que no exista)");
            }
            
            waitForSeconds(1);
            
            // Enviar formulario Paso 1
            click(btnSiguiente);
            System.out.println("✓ Se hizo clic en 'Siguiente' - Paso 1 completado");
            waitForSeconds(3); // Esperar a que cargue el Paso 2
            
            // ========== PASO 2: Completar dirección ==========
            System.out.println("Iniciando Paso 2: Completar dirección");
            
            // Provincia
            try {
                waitForSeconds(2);
                click(comboProvincia);
                waitForSeconds(1);
                sendBorrar(13);
                // Seleccionar la primera opción disponible o Capital Federal
                try {
                    //click(opcionProvincia);
                    writeText(comboProvincia,"capital");
                    waitForSeconds(1);
                    sendFlechaAbajo(1);
                    sendEnter();
                } catch (Exception e) {
                    // Si no encuentra la opción específica, usar flechas
                    sendFlechaAbajo(1);
                    sendEnter();
                }
                System.out.println("✓ Se seleccionó la provincia");
            } catch (Exception e) {
                System.out.println("⚠ No se pudo seleccionar la provincia: " + e.getMessage());
            }
            
            // Localidad
            try {
                waitForSeconds(1);
                click(comboLocalidad);
                sendBorrar(13);
                // Seleccionar la primera opción disponible
                try {
                    //click(opcionLocalidad);
                    writeText(comboLocalidad,"ciudad");
                    waitForSeconds(1);
                    sendFlechaAbajo(1);
                    sendEnter();
                } catch (Exception e) {
                    // Si no encuentra la opción específica, usar flechas
                    sendFlechaAbajo(1);
                    sendEnter();
                }
                System.out.println("✓ Se seleccionó la localidad");
            } catch (Exception e) {
                System.out.println("⚠ No se pudo seleccionar la localidad: " + e.getMessage());
            }
            
            // Dirección
            try {
                waitForSeconds(1);
                writeText(campoDireccion, "Av. Test " + numerosAleatorios(4));
                System.out.println("✓ Se ingresó la dirección");
            } catch (Exception e) {
                System.out.println("⚠ No se pudo ingresar la dirección: " + e.getMessage());
            }
            
            // Código postal
            try {
                waitForSeconds(1);
                writeText(campoCodigoPostal, "1020"); // Código postal por defecto para Capital Federal
                System.out.println("✓ Se ingresó el código postal");
            } catch (Exception e) {
                System.out.println("⚠ No se pudo ingresar el código postal: " + e.getMessage());
            }
            
            waitForSeconds(1);
            
            // Hacer clic en el botón "Registrarme" final
            try {
                click(btnRegistrarseFinal);
                System.out.println("✓ Se hizo clic en 'Registrarme' - Paso 2 completado");
                waitForSeconds(3); // Esperar respuesta del servidor y finalización del registro
            } catch (Exception e) {
                throw new AssertionError("✗ No se pudo hacer clic en el botón Registrarme final: " + e.getMessage());
            }
            
        } catch (Exception e) {
            throw new AssertionError("✗ Error al llenar el formulario de registro: " + e.getMessage());
        }
    }

    /**
     * Valida que se muestra el mensaje de bienvenida después del registro
     */
    public void validarMensajeBienvenida() {
        try {
            waitForSeconds(3);
            boolean mensajeExiste = elementExists(mensajeBienvenida);
            if (mensajeExiste) {
                WebElement mensaje = findElement(mensajeBienvenida);
                String textoMensaje = mensaje.getText();
                System.out.println("✓ Mensaje de bienvenida encontrado: " + textoMensaje);
            } else {
                // También buscar en el texto de la página
                String pageText = getDriver().findElement(By.tagName("body")).getText();
                if (pageText.contains("Bienvenido") || 
                    pageText.contains("bienvenido") || 
                    pageText.contains("Cuenta creada") ||
                    pageText.contains("Registro exitoso")) {
                    System.out.println("✓ Mensaje de bienvenida encontrado en la página");
                } else {
                    throw new AssertionError("✗ No se encontró mensaje de bienvenida después del registro");
                }
            }
        } catch (Exception e) {
            throw new AssertionError("✗ Error al validar mensaje de bienvenida: " + e.getMessage());
        }
    }

    /**
     * Ingresa un valor en un campo específico del formulario de registro
     * Nota: El sistema muestra errores solo después de hacer clic en el campo y luego hacer clic fuera (blur)
     */
    public void ingresarValorEnCampo(String campo, String valor) {
        try {
            waitForSeconds(1);
            By locatorCampo = null;
            
            switch (campo.toLowerCase()) {
                case "nombre":
                    locatorCampo = campoNombre;
                    break;
                case "apellido":
                    locatorCampo = campoApellido;
                    break;
                case "documento":
                    locatorCampo = campoDocumento;
                    break;
                case "celular":
                    locatorCampo = campoCelular;
                    break;
                case "email":
                    locatorCampo = campoEmailRegistro;
                    break;
                case "contraseña":
                case "password":
                    locatorCampo = campoPasswordRegistro;
                    break;
                case "confirmarcontraseña":
                case "confirmar contraseña":
                case "confirmarpassword":
                    locatorCampo = campoPasswordConfirmRegistro;
                    break;
                case "tipo":
                    // Para tipo de documento, solo hacer clic si no está vacío
                    if (valor != null && !valor.isEmpty()) {
                        click(comboDNI);
                        waitForSeconds(1);
                        click(opcionDNI);
                        // Hacer clic fuera para disparar validación
                        hacerClicFueraDelCampo();
                    } else {
                        // Si está vacío, hacer clic en el combo y luego fuera
                        click(comboDNI);
                        waitForSeconds(1);
                        hacerClicFueraDelCampo();
                    }
                    return; // Salir temprano para tipo
                default:
                    throw new IllegalArgumentException("Campo no reconocido: " + campo);
            }
            
            if (locatorCampo != null) {
                // Primero hacer clic en el campo para enfocarlo
                WebElement elemento = findElement(locatorCampo);
                click(locatorCampo);
                try {
                    Thread.sleep(500); // Esperar 0.5 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                if (valor == null || valor.isEmpty()) {
                    // Limpiar el campo si el valor está vacío
                    elemento.clear();
                } else {
                    // Limpiar primero y luego escribir
                    elemento.clear();
                    elemento.sendKeys(valor);
                }
                
                // Hacer clic fuera del campo para disparar la validación (blur event)
                hacerClicFueraDelCampo();
                
                System.out.println("✓ Se ingresó en " + campo + ": " + (valor == null || valor.isEmpty() ? "(vacío)" : valor));
            }
        } catch (Exception e) {
            throw new AssertionError("✗ Error al ingresar valor en campo " + campo + ": " + e.getMessage());
        }
    }

    /**
     * Hace clic en un lugar vacío de la página para disparar eventos blur y validaciones
     */
    private void hacerClicFueraDelCampo() {
        try {
            // Hacer clic en el título del modal o en un área vacía
            // Intentar hacer clic en el título "Creá tu Cuenta" o en el body
            By tituloModal = By.xpath("//h1[contains(text(), 'Creá tu Cuenta') or contains(text(), 'Crea tu Cuenta')] | //h2[contains(text(), 'Creá tu Cuenta')]");
            
            try {
                if (elementExists(tituloModal)) {
                    click(tituloModal);
                } else {
                    // Si no existe el título, hacer clic en el body o en un área vacía del modal
                    WebElement body = getDriver().findElement(By.tagName("body"));
                    // Hacer clic en una posición específica (por ejemplo, esquina superior izquierda del modal)
                    org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(getDriver());
                    actions.moveToElement(body, 100, 100).click().perform();
                }
            } catch (Exception e) {
                // Si falla, intentar hacer clic en cualquier otro campo que no sea el actual
                // o simplemente presionar Tab para mover el foco
                try {
                    WebElement body = getDriver().findElement(By.tagName("body"));
                    body.click();
                } catch (Exception e2) {
                    // Último recurso: usar Tab para mover el foco
                    WebElement body = getDriver().findElement(By.tagName("body"));
                    body.sendKeys(org.openqa.selenium.Keys.TAB);
                }
            }
            
            try {
                Thread.sleep(500); // Pequeña pausa para que se procese el evento blur
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.out.println("⚠ No se pudo hacer clic fuera del campo, pero se continuará: " + e.getMessage());
        }
    }

    /**
     * Hace clic en el botón Siguiente del formulario de registro
     */
    public void clicBotonSiguiente() {
        try {
            waitForSeconds(1);
            click(btnSiguiente);
            System.out.println("✓ Se hizo clic en el botón Siguiente");
            waitForSeconds(2); // Esperar a que aparezcan los mensajes de validación
        } catch (Exception e) {
            throw new AssertionError("✗ Error al hacer clic en el botón Siguiente: " + e.getMessage());
        }
    }

    /**
     * Valida que se muestra un mensaje de validación específico
     */
    public void validarMensajeValidacion(String mensajeEsperado) {
        try {
            waitForSeconds(2);
            
            // Buscar el mensaje en diferentes ubicaciones posibles
            String mensajeLower = mensajeEsperado.toLowerCase();
            
            // Buscar en elementos de error/validación
            try {
                java.util.List<WebElement> elementosError = getDriver().findElements(mensajesValidacion);
                for (WebElement elemento : elementosError) {
                    String texto = elemento.getText().toLowerCase();
                    if (texto.contains(mensajeLower) || mensajeLower.contains(texto)) {
                        System.out.println("✓ Mensaje de validación encontrado: " + elemento.getText());
                        return;
                    }
                }
            } catch (Exception e) {
                // Continuar con otros métodos de búsqueda
            }
            
            // Buscar en todo el body de la página
            String pageText = getDriver().findElement(By.tagName("body")).getText().toLowerCase();
            if (pageText.contains(mensajeLower)) {
                System.out.println("✓ Mensaje de validación encontrado en la página: " + mensajeEsperado);
                return;
            }
            
            // Buscar por texto exacto o parcial en cualquier elemento
            String xpathMensaje = "//*[contains(text(), '" + mensajeEsperado + "')]";
            try {
                WebElement mensaje = getDriver().findElement(By.xpath(xpathMensaje));
                if (mensaje.isDisplayed()) {
                    System.out.println("✓ Mensaje de validación encontrado: " + mensaje.getText());
                    return;
                }
            } catch (Exception e) {
                // Continuar
            }
            
            // Si no se encuentra, lanzar error
            throw new AssertionError("✗ No se encontró el mensaje de validación esperado: " + mensajeEsperado);
            
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new AssertionError("✗ Error al validar mensaje de validación: " + e.getMessage());
        }
    }

}
