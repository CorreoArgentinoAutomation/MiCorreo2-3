@recargarSaldo
@RegresionConUsuarioED
@PTA-46
Feature: Recargar saldo

  Background:
    Given el usuario se situa en los campos email y password

  Scenario Outline: Recargar saldo al usuario
    Given el usuario '<tipoUsuario>' está logueado y en la page home
    When el usuario hace clic en el boton Recargar saldo
    Then el usuario seleccionar el '<medioPago>' y el '<valorRecarga>' para la recarga

    Examples:
      | tipoUsuario | medioPago | valorRecarga |
      | usuario ED  | Tarjeta   | 10000        |
      #| Consumidor final | Tarjeta   | 10000        |
      #| Monotributista | Tarjeta   | 10000        |
      #| Empresa     | Tarjeta   | 10000        |
      #| Consumidor final | Mercado Pago | 10000        |
      #| Monotributista   | Mercado Pago | 10000        |
      #| Empresa          | Mercado Pago | 10000        |