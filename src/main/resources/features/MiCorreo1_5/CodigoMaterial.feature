@EnvioIndivual
@Regresion1.5
#@RegresionRequerimientoEvolutivo
#@PTA-42
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
    And se confirma que el pago se ha realizado con exito

    Examples:
      | tipoUsuario      | tipoOrigen    | peso  | tipoEntrega    | tipoProducto | medioPago        |
      | Consumidor final | SucursalZona1 | 1kg   | DomicilioZona1 | Clasico      | Tarjeta          |
      | Consumidor final | SucursalZona1 | 5kg   | SucursalZona1  | Expreso      | Mercado Pago     |
      | Consumidor final | PickUP        | 2kg   | DomicilioZona7 | PaqArHoy     | Saldo            |
      | Consumidor final | SucursalZona2 | 10kg  | SucursalZona2  | Clasico      | Tarjeta          |
      | Consumidor final | SucursalZona3 | 3kg   | DomicilioZona3 | Expreso      | Mercado Pago     |
      | Consumidor final | PickUP        | 15kg  | DomicilioZona7 | PaqArHoy     | Saldo            |
      | Consumidor final | SucursalZona4 | 5kg   | DomicilioZona4 | Clasico      | Tarjeta          |
      | Consumidor final | SucursalZona4 | 20kg  | SucursalZona4  | Expreso      | Saldo            |
      | Consumidor final | PickUP        | 1kg   | DomicilioZona5 | PaqArHoy     | Tarjeta          |
      | Consumidor final | PickUP        | 2kg   | DomicilioZona6 | Clasico      | Mercado Pago     |
      | Consumidor final | PickUP        | 3kg   | DomicilioZona7 | Expreso      | Saldo            |
      | Monotributista   | SucursalZona1 | 1kg   | DomicilioZona1 | Expreso      | Tarjeta          |
      | Monotributista   | SucursalZona1 | 10kg  | SucursalZona1  | Clasico      | Mercado Pago     |
      | Monotributista   | SucursalZona2 | 2kg   | DomicilioZona2 | PaqArHoy     | Saldo            |
      | Monotributista   | SucursalZona2 | 15kg  | SucursalZona2  | Expreso      | Tarjeta          |
      | Monotributista   | SucursalZona3 | 5kg   | DomicilioZona3 | Clasico      | Mercado Pago     |
      | Monotributista   | SucursalZona3 | 25kg  | SucursalZona3  | PaqArHoy     | Saldo            |
      | Monotributista   | SucursalZona4 | 3kg   | DomicilioZona4 | Expreso      | Tarjeta          |
      | Monotributista   | SucursalZona4 | 30kg  | SucursalZona4  | Clasico      | Saldo            |
      | Monotributista   | PickUP        | 2kg   | DomicilioZona6 | PaqArHoy     | Tarjeta          |
      | Empresa          | SucursalZona1 | 1kg   | DomicilioZona1 | Clasico      | Cuenta Corriente |
      | Empresa          | SucursalZona1 | 10kg  | SucursalZona1  | Expreso      | Tarjeta          |
      | Empresa          | PickUP        | 20kg  | DomicilioZona6 | PaqArHoy     | Mercado Pago     |
      | Empresa          | SucursalZona2 | 2kg   | DomicilioZona2 | Clasico      | Cuenta Corriente |
      | Empresa          | SucursalZona2 | 15kg  | SucursalZona2  | Expreso      | Saldo            |
      | Empresa          | PickUP        | 5kg   | DomicilioZona5 | PaqArHoy     | Cuenta Corriente |
      | Empresa          | SucursalZona3 | 25kg  | SucursalZona3  | Clasico      | Tarjeta          |
      | Empresa          | SucursalZona4 | 3kg   | DomicilioZona4 | Expreso      | Cuenta Corriente |
      | Empresa          | PickUP        | 30kg  | DomicilioZona5 | PaqArHoy     | Mercado Pago     |
      | Empresa          | PickUP        | 1kg   | DomicilioZona5 | Clasico      | Cuenta Corriente |
      | Consumidor final | PickUP        | 0,5kg | DomicilioZona1 | PaqArHoy     | Saldo            |
      | Consumidor final | SucursalZona2 | 50kg  | SucursalZona2  | Expreso      | Tarjeta          |
      | Monotributista   | SucursalZona1 | 40kg  | DomicilioZona1 | Clasico      | Mercado Pago     |
      | Empresa          | SucursalZona2 | 100kg | DomicilioZona2 | Expreso      | Cuenta Corriente |
      | Consumidor final | PickUP        | 5kg   | DomicilioZona5 | Clasico      | Mercado Pago     |
		
