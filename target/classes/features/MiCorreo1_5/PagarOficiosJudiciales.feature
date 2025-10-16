@PagoOficioJudicial
@RegresionRequerimientoEvolutivo
@PTA-43
Feature: Pagar oficio judicial

  Scenario Outline: Pagar oficio judicial
    Given el usuario se situa en los campos email y password
    When el usuario '<tipoUsuario>' está logueado y en la page home
    And el usuario ingresa la pantalla de Oficios Judiciales
    And el usuario rellena el formulario de oficios con el tipo de camara '<tipoCamara>'
    And el usuario visualiza el message de oficio creado correctamente
    And el usuario realiza el pago con el '<medioPago>' seleccionado
    Then el usuario visualiza el mensaje de pago correctamente

    Examples:
      | tipoUsuario      | tipoCamara       | medioPago    |
      | Consumidor final | Camara           | Saldo        |
      | Monotributista   | Camara           | Saldo        |
      | Empresa          | Camara           | Saldo        |
      | Consumidor final | Justicia Federal | Saldo        |
      | Monotributista   | Justicia Federal | Saldo        |
      | Empresa          | Justicia Federal | Saldo        |
      | Consumidor final | Corte Suprema    | Saldo        |
      | Monotributista   | Corte Suprema    | Saldo        |
      | Empresa          | Corte Suprema    | Saldo        |

      | Consumidor final | Camara           | Tarjeta      |
      | Monotributista   | Camara           | Tarjeta      |
      | Empresa          | Camara           | Tarjeta      |
      | Consumidor final | Justicia Federal | Tarjeta      |
      | Monotributista   | Justicia Federal | Tarjeta      |
      | Empresa          | Justicia Federal | Tarjeta      |
      | Consumidor final | Corte Suprema    | Tarjeta      |
      | Monotributista   | Corte Suprema    | Tarjeta      |
      | Empresa          | Corte Suprema    | Tarjeta      |

      | Consumidor final | Camara           | Mercado Pago |
      | Monotributista   | Camara           | Mercado Pago |
      | Empresa          | Camara           | Mercado Pago |
      | Consumidor final | Justicia Federal | Mercado Pago |
      | Monotributista   | Justicia Federal | Mercado Pago |
      | Empresa          | Justicia Federal | Mercado Pago |
      | Consumidor final | Corte Suprema    | Mercado Pago |
      | Monotributista   | Corte Suprema    | Mercado Pago |
      | Empresa          | Corte Suprema    | Mercado Pago |
