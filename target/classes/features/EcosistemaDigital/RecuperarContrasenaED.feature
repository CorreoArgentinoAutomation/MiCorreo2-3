@RecuperarContrasenaED
Feature: Recuperación de contraseña en la plataforma ComunidadCorreo
  Como usuario que olvidó su contraseña
  Quiero recuperar mi contraseña mediante email
  Para poder acceder nuevamente a mi cuenta

  Scenario: Solicitar recuperación de contraseña y validar email recibido
    Given que el usuario está en la página de login
    When el usuario hace clic en el enlace "¿Olvidaste tu contraseña?"
    And el usuario ingresa su correo electrónico "cf_tester02@yopmail.com" para recuperar contraseña
    And el usuario solicita la recuperación de contraseña
    Then el sistema muestra un mensaje de confirmación
    When el usuario accede a su correo electrónico "cf_tester02@yopmail.com"
    And el usuario busca el email de recuperación de contraseña
    Then el sistema valida que el email de recuperación fue recibido
    And el sistema valida que el email contiene un enlace para recuperar contraseña
    When el usuario hace clic en el enlace de recuperación de contraseña del email
    And el usuario ingresa una nueva contraseña aleatoria
    And el usuario confirma la nueva contraseña
    And el usuario hace clic en el botón para reiniciar la contraseña

    #contraseña nueva a6UfeNnG491y

