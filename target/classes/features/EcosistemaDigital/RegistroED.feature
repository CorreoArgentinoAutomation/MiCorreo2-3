@RegistroEcosistemaDigital
Feature: Registro de usuario

  Scenario: Registro exitoso de un nuevo usuario
    Given el usuario crea un email temporal
    When que el usuario ingresa al sitio de ecosistema digital
    And el usuario hace clic en el boton Registrarme
    And el usuario llena el formulario de registro
    And el usuario abre el servicio de correo electronico
    And el usuario hace clic en el boton Activa tu usuario
    Then el usuario deberia ver el mensaje de bienvenida
		

