@LoginED
Feature: Autenticación de usuario en la plataforma ComunidadCorreo
  Como usuario registrado
  Quiero iniciar sesión en la plataforma
  Para acceder a mis funcionalidades privadas

  Scenario Outline: Intento de login con diferentes combinaciones
    Given que el usuario está en la página de login
    And el sistema valida que el campo de correo existe y está visible
    And el sistema valida que el campo de contraseña existe y está visible
    And el sistema valida que el botón Ingresar existe y está visible
    And el usuario ingresa el correo "<email>"
    And el usuario ingresa la contraseña "<pass>"
    When el usuario hace clic en el botón Ingresar
    Then el sistema muestra el mensaje "<mensajeEsperado>"

    Examples:
      | email                   | pass         | mensajeEsperado                                        |
      | cf_tester02@yopmail.com | 123123       | Hola                                                   |
      | cf_tester02@yopmail.com | ClaveErronea | Credenciales inválidas                                 |
      |                         |              | El correo es obligatorio; La contrasena es obligatoria |
