@recargarSaldoED
@RegresionConUsuarioED
@PTA-46
Feature: Recargar saldo

  Background:
    Given el usuario se situa en los campos email y password

  Scenario Outline: Recargar saldo al usuario
    Given que el usuario está en la página de login
    And el usuario ingresa el correo "<email>"
    And el usuario ingresa la contraseña "<pass>"
    When el usuario hace clic en el botón Ingresar
    When el usuario hace clic en el boton Recargar saldo
    Then el usuario seleccionar el '<medioPago>' y el '<valorRecarga>' para la recarga

    Examples:
      | email                   | pass   | medioPago    | valorRecarga |
      #| mono_tester@yopmail.com | 123123 | Tarjeta      | 10000        |
      #| cf_tester02@yopmail.com | 123123 | Tarjeta      | 10000        |
      #| empctacte_test@yopmail.com | 123123 | Tarjeta      | 10000        |
      | cf_tester02@yopmail.com | 123123 | Mercado Pago | 10000,01     |
