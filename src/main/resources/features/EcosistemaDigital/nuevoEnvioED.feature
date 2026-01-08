@nuevoEnvioED
  @PTA-56
Feature: Nuevo envio individual

  Scenario Outline: Intento de login con diferentes combinaciones
    Given que el usuario está en la página de login
    And el usuario ingresa el correo "<email>"
    And el usuario ingresa la contraseña "<pass>"
    When el usuario hace clic en el botón Ingresar
    When ingresa en nuevo envío individual
    And editar el '<tipoOrigen>' del envío individual
    And llena los campos de paquete segun el '<peso>' y las dimensiones
    And selecciona el '<tipoEntrega>' completa el formulario de destino
    And selecciona el '<tipoProducto>' y procede a pagar
    And se muestra la grilla de checkout
    Then realiza el pago con '<medioPago>' del envío
    And se confirma que el pago se ha realizado con exito

    Examples:
      | email                      | pass   | tipoOrigen    | peso | tipoEntrega    | tipoProducto | medioPago        |
      | mono_tester@yopmail.com    | 123123 | SucursalZona1 | 1kg  | DomicilioZona1 | Clasico      | Tarjeta          |
      | cf_tester02@yopmail.com    | 123123 | PickUP        | 1kg  | DomicilioZona1 | Expreso      | Saldo            |
      | empctacte_test@yopmail.com | 123123 | PickUP        | 1kg  | DomicilioZona1 | PaqArHoy     | Cuenta Corriente |
      | cf_tester02@yopmail.com    | 123123 | PickUP        | 1kg  | DomicilioZona1 | PaqArHoy     | Mercado Pago     |

