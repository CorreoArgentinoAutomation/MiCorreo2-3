@EliminarTodosLosOficiosJudiciales
@Regresion1.5
@PTA-44

Feature: Eliminar oficios judicialess

  Scenario Outline: Eliminar todos los oficios judiciales
    Given el usuario se situa en los campos email y password
    When el usuario '<tipoUsuario>' está logueado y en la page home
    And el usuario ingresa la pantalla de Oficios Judiciales
    And el usuario rellena el formulario de oficios
    Then el usuario visualiza el message de oficio creado correctamente
    And el usuario selecciona y elimina todos los oficios
    Then el usuario visualiza el mensaje de eliminacion correctamente

    Examples:
      | tipoUsuario      |
      #| usuario ED  |
      | Consumidor final |
      | Monotributista   |
      | Empresa          |
      #| Usuario Invitado |
		

