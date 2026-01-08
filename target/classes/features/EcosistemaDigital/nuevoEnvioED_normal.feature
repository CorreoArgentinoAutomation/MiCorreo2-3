@nuevoEnvioEDNormal
Feature: Nuevo envio individual (con datos desde CSV)

  Scenario: Intento de login con diferentes combinaciones usando datos desde CSV
    Given que el usuario carga los datos desde CSV y está en la página de login
    And el usuario ingresa el correo desde CSV
    And el usuario ingresa la contraseña desde CSV
    When el usuario hace clic en el botón Ingresar desde CSV
    When ingresa en nuevo envío individual desde CSV
    And editar el origen del envío individual desde CSV
    And llena los campos de paquete desde CSV
    And selecciona el tipo de entrega desde CSV y completa el formulario de destino
    And selecciona el tipo de producto desde CSV y procede a pagar
    And se muestra la grilla de checkout desde CSV
    Then realiza el pago con el medio de pago desde CSV
    And se confirma que el pago se ha realizado con exito desde CSV

