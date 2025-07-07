@PTA-23
@loguinUnificado
Feature: Configuracion de servicios de Correo Argentino en WooCommerce

  Scenario Outline: Configuracion de servicio <servicio> en WooCommerce
    Given que estoy en el Dashboard de WordPress
    And hago clic en el boton de plugin
    And desactivo el plugin
    And Activo el plugin
    When hago clic en el boton Conexion Api
    And hago clic en el menu desplegable de "<servicio>"
    And hago clic en el boton Guardar Cambios
    And ingreso el usuario y la contrasena con el "<tipoUsuario>"
    And completo los datos del negocio en el formulario
    Then valido que se guarden los a justes

    Examples:
      | tipoUsuario      | servicio  |
      | Consumidor final | Mi Correo |
      | Monotributista   | Mi Correo |
      | Empresa          | Mi Correo |
      | PaqAr            | PaqAr     |
