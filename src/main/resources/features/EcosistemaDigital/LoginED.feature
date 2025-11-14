#Feature: Autenticación de usuario en la Ecosistema Digital
#  Como usuario registrado
#  Quiero iniciar sesión en la plataforma
#  Para acceder a mis funcionalidades

#  Scenario: Login exitoso con credenciales válidas
#    Given que el usuario está en la página de login
#    And el usuario ingresa un correo válido "usuario@correo.com"
#    And el usuario ingresa una contraseña válida "Password123"
#    When el usuario hace clic en el botón "Iniciar sesión"
#    Then el sistema valida las credenciales contra el servicio de autenticación
#    And el usuario es redirigido al dashboard
#    And se muestra el mensaje "Bienvenido"

#  Scenario: Login fallido con credenciales inválidas
#    Given que el usuario está en la página de login
#    And el usuario ingresa un correo inválido "usuario@correo.com"
#    And el usuario ingresa una contraseña incorrecta "ClaveErronea"
#    When el usuario hace clic en el botón "Iniciar sesión"
#    Then el sistema muestra el mensaje de error "Credenciales inválidas"
#    And el usuario permanece en la página de login

#  Scenario: Validación de campos obligatorios
#    Given que el usuario está en la página de login
#    When el usuario hace clic en el botón "Iniciar sesión" sin completar los campos
#    Then el sistema muestra mensajes de validación "El correo es obligatorio" y "La contraseña es obligatoria"

Feature: Autenticación de usuario en la plataforma ComunidadCorreo
  Como usuario registrado
  Quiero iniciar sesión en la plataforma
  Para acceder a mis funcionalidades privadas

  Scenario Outline: Intento de login con diferentes combinaciones
    Given que el usuario está en la página de login
    And el usuario ingresa el correo "<email>"
    And el usuario ingresa la contraseña "<pass>"
    When el usuario hace clic en el botón Ingresar
    Then el sistema muestra el mensaje "<mensajeEsperado>"

    Examples:
      | email              | pass         | mensajeEsperado                                        |
      | usuario@correo.com | Password123  | Hola                                                   |
      | usuario@correo.com | ClaveErronea | Credenciales inválidas                                 |
      |                    |              | El correo es obligatorio; La contrasena es obligatoria |
