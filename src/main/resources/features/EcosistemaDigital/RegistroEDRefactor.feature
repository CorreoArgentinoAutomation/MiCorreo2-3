@RegistroEcosistemaDigital
Feature: Registro de usuario

  Scenario Outline: Validación de campos del formulario de registro
    Given que el usuario ingresa al sitio de ecosistema digital
    And el usuario hace clic en el boton Registrarme
    And el usuario escribe en el campo nombre con "<nombre>"
    And el usuario escribe en el campo apellido con "<apellido>"

    And el usuario hace clic en el combo de tipo con "<tipo>"

    And el usuario escribe en el campo documento con "<documentoDeIndentidad>"
    And el usuario escribe en el campo cuit con "<cuit>"
    And el usuario escribe en el campo celular con "<celular>"
    And el usuario escribe en el campo correo electronico con "<correoElectronico>"
    And el usuario escribe en el campo contraseña con "<contraseña>"
    And el usuario escribe en el campo confirmar contraseña con "<confirmarContraseña>"

    And el usuario hace clic en el check de terminos y condiciones

    And el usuario hace clic en el boton Siguiente

    Then el sistema debe mostrar el mensaje de validación "<mensajeEsperado>"

    Examples:
      | nombre | apellido  | tipo | documentoDeIndentidad | cuit  | celular   | correoElectronico | contraseña | confirmarContraseña | mensajeEsperado |
      | juan   | aleatorio | DNI  | aleatorio             | lista | aleatorio | aleatorio         | aleatorio  | aleatorio           | cualquiera      |
