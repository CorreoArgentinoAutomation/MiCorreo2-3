@EnvioIndivual
@RegresionRequerimientoEvolutivo
@PTA-42
#@MCIB-133 Numero de referencia de envío en el proyecto de jira
Feature: TE - Nuevo envio individual

  Background:
		#@PRECOND_MCIB-130
    Given el usuario se situa en los campos email y password

	#*Objetivo:* Verificar que un perfil pueda realizar un nuevo envío individual a través del sistema de la aplicación, siguiendo el flujo crítico establecido y concretando con el pago exitoso del mismo.
  #@TEST_MCIB-87 @TESTSET_MCIB-84
  Scenario Outline: Nuevo envio individual
    Given el usuario '<tipoUsuario>' está logueado y en la page home
    When ingresa en nuevo envío individual
    And editar el '<tipoOrigen>' del envío individual
    And llena los campos de paquete segun el '<peso>' y las dimensiones
    And selecciona el '<tipoEntrega>' completa el formulario de destino
    And selecciona el '<tipoProducto>' y procede a pagar
    And se muestra la grilla de checkout
    Then realiza el pago con '<medioPago>' del envío
    And se confirma que el pago se ha realizado con éxito

    Examples:
      | tipoUsuario      | tipoOrigen    | peso  | tipoEntrega    | tipoProducto | medioPago    |
      | Consumidor final | SucursalZona1 | 0,5kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 1kg   | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 2kg   | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 3kg   | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 5kg   | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 10kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 15kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 20kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 25kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 30kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 35kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 40kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 50kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 60kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 70kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 80kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 90kg  | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 100kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 110kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 120kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 130kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 140kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 150kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 170kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 190kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 210kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 230kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 250kg | SucursalZona1  | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 0,5kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 1kg   | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 2kg   | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 3kg   | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 5kg   | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 10kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 15kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 20kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 25kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 30kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 35kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 40kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 50kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 60kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 70kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 80kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 90kg  | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 100kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 110kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 120kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 130kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 140kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 150kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 170kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 190kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 210kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 230kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 250kg | SucursalZona1  | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 0,5kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 1kg   | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 2kg   | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 3kg   | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 5kg   | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 10kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 15kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 20kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 25kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 30kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 35kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 40kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 50kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 60kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 70kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 80kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 90kg  | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 100kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 110kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 120kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 130kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 140kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 150kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 170kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 190kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 210kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 230kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 250kg | SucursalZona1  | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 0,5kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 1kg   | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 2kg   | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 3kg   | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 5kg   | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 10kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 15kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 20kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 25kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 30kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 35kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 40kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 50kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 60kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 70kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 80kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 90kg  | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 100kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 110kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 120kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 130kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 140kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 150kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 170kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 190kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 210kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 230kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 250kg | SucursalZona1  | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 0,5kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 1kg   | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 2kg   | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 3kg   | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 5kg   | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 10kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 15kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 20kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 25kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 30kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 35kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 40kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 50kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 60kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 70kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 80kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 90kg  | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 100kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 110kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 120kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 130kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 140kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 150kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 170kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 190kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 210kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 230kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 250kg | SucursalZona1  | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 0,5kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 1kg   | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 2kg   | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 3kg   | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 5kg   | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 10kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 15kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 20kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 25kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 30kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 35kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 40kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 50kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 60kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 70kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 80kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 90kg  | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 100kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 110kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 120kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 130kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 140kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 150kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 170kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 190kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 210kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 230kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 250kg | SucursalZona1  | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 0,5kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 1kg   | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 2kg   | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 3kg   | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 5kg   | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 10kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 15kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 20kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 25kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 30kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 35kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 40kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 50kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 60kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 70kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 80kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 90kg  | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 100kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 110kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 120kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 130kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 140kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 150kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 170kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 190kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 210kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 230kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 250kg | DomicilioZona1 | Clasico      | Saldo        |
      | Consumidor final | SucursalZona1 | 0,5kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 1kg   | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 2kg   | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 3kg   | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 5kg   | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 10kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 15kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 20kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 25kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 30kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 35kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 40kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 50kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 60kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 70kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 80kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 90kg  | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 100kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 110kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 120kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 130kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 140kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 150kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 170kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 190kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 210kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 230kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 250kg | DomicilioZona1 | Expreso      | Saldo        |
      | Consumidor final | SucursalZona1 | 0,5kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 1kg   | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 2kg   | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 3kg   | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 5kg   | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 10kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 15kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 20kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 25kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 30kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 35kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 40kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 50kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 60kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 70kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 80kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 90kg  | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 100kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 110kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 120kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 130kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 140kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 150kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 170kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 190kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 210kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 230kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 250kg | DomicilioZona1 | Clasico      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 0,5kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 1kg   | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 2kg   | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 3kg   | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 5kg   | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 10kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 15kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 20kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 25kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 30kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 35kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 40kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 50kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 60kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 70kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 80kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 90kg  | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 100kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 110kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 120kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 130kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 140kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 150kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 170kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 190kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 210kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 230kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 250kg | DomicilioZona1 | Expreso      | Tarjeta      |
      | Consumidor final | SucursalZona1 | 0,5kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 1kg   | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 2kg   | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 3kg   | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 5kg   | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 10kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 15kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 20kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 25kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 30kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 35kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 40kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 50kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 60kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 70kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 80kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 90kg  | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 100kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 110kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 120kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 130kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 140kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 150kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 170kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 190kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 210kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 230kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 250kg | DomicilioZona1 | Clasico      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 0,5kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 1kg   | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 2kg   | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 3kg   | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 5kg   | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 10kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 15kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 20kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 25kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 30kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 35kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 40kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 50kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 60kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 70kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 80kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 90kg  | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 100kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 110kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 120kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 130kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 140kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 150kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 170kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 190kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 210kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 230kg | DomicilioZona1 | Expreso      | Mercado Pago |
      | Consumidor final | SucursalZona1 | 250kg | DomicilioZona1 | Expreso      | Mercado Pago |


		
