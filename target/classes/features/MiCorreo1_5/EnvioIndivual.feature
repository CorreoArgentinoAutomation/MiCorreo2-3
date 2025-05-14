@EnvioIndivual
@RegresionRequerimientoEvolutivo
#@MCIB-133 Numero de referencia de envío en el proyecto de jira
Feature: TE - Nuevo envio individual

  Background:
		#@PRECOND_MCIB-130
    Given el usuario se situa en los campos email y password

	#*Objetivo:* Verificar que un perfil pueda realizar un nuevo envío individual a través del sistema de la aplicación, siguiendo el flujo crítico establecido y concretando con el pago exitoso del mismo.
  @TEST_MCIB-87 @TESTSET_MCIB-84
  Scenario Outline: Nuevo envio individual
    Given el usuario '<tipoUsuario>' está logueado y en la page home
    When ingresa en nuevo envío individual
    And editar el '<tipoOrigen>' del envío individual
    And llena los campos de paquete
    And selecciona el '<tipoEntrega>' completa el formulario de destino
    And selecciona el '<tipoProducto>' y procede a pagar
    And se muestra la grilla de checkout
    Then realiza el pago con '<medioPago>' del envío
    And se confirma que el pago se ha realizado con éxito

    Examples:
      | tipoUsuario       | tipoOrigen | tipoEntrega | tipoProducto | medioPago |
      #| Consumidor final  |Sucursal  |  Domicilio  | Clasico      | Saldo     |
      #| Consumidor final  |PickUP    |  Sucursal  | PaqArHoy     | Saldo     |


		#Posibles combinaciones de tipoUsuario, tipoEntrega y tipoProducto
      #| tipoUsuario       | tipoOrigen | tipoEntrega | tipoProducto | medioPago     |
      #| Consumidor final  | PickUP     | Domicilio   | Clasico      | Tarjeta       |
      #| Consumidor final  | PickUP     | Domicilio   | Clasico      | Saldo         |
      #| Consumidor final  | PickUP     | Domicilio   | Clasico      | Mercado Pago  |
      #| Consumidor final  | PickUP     | Domicilio   | Expreso      | Tarjeta       |
      #| Consumidor final  | PickUP     | Domicilio   | Expreso      | Saldo         |
      #| Consumidor final  | PickUP     | Domicilio   | Expreso      | Mercado Pago  |
      #| Consumidor final  | PickUP     | Domicilio   | PaqArHoy     | Tarjeta       |
      #| Consumidor final  | PickUP     | Domicilio   | PaqArHoy     | Saldo         |
      #| Consumidor final  | PickUP     | Domicilio   | PaqArHoy     | Mercado Pago  |
      #| Consumidor final  | PickUp     | Sucursal    | Clasico      | Tarjeta       |
      #| Consumidor final  | PickUp     | Sucursal    | Clasico      | Saldo         |
      #| Consumidor final  | PickUp     | Sucursal    | Clasico      | Mercado Pago  |
      #| Consumidor final  | PickUp     | Sucursal    | Expreso      | Tarjeta       |
      #| Consumidor final  | PickUp     | Sucursal    | Expreso      | Saldo         |
      #| Consumidor final  | PickUp     | Sucursal    | Expreso      | Mercado Pago  |
      #| Consumidor final  | Sucursal   | Domicilio   | Clasico      | Tarjeta       |
      #| Consumidor final  | Sucursal   | Domicilio   | Clasico      | Saldo         |
      #| Consumidor final  | Sucursal   | Domicilio   | Clasico      | Mercado Pago  |
      #| Consumidor final  | Sucursal   | Domicilio   | Expreso      | Tarjeta       |
      #| Consumidor final  | Sucursal   | Domicilio   | Expreso      | Saldo         |
      #| Consumidor final  | Sucursal   | Domicilio   | Expreso      | Mercado Pago  |
      #| Consumidor final  | Sucursal   | Sucursal    | Clasico      | Tarjeta       |
      #| Consumidor final  | Sucursal   | Sucursal    | Clasico      | Saldo         |
      #| Consumidor final  | Sucursal   | Sucursal    | Clasico      | Mercado Pago  |
      #| Consumidor final  | Sucursal   | Sucursal    | Expreso      | Tarjeta       |
      #| Consumidor final  | Sucursal   | Sucursal    | Expreso      | Saldo         |
      #| Consumidor final  | Sucursal   | Sucursal    | Expreso      | Mercado Pago  |
      #| Monotributista    | PickUP     | Domicilio   | Clasico      | Tarjeta       |
      #| Monotributista    | PickUP     | Domicilio   | Clasico      | Saldo         |
      #| Monotributista    | PickUP     | Domicilio   | Clasico      | Mercado Pago  |
      #| Monotributista    | PickUP     | Domicilio   | Expreso      | Tarjeta       |
      #| Monotributista    | PickUP     | Domicilio   | Expreso      | Saldo         |
      #| Monotributista    | PickUP     | Domicilio   | Expreso      | Mercado Pago  |
      #| Monotributista    | PickUP     | Domicilio   | PaqArHoy     | Tarjeta       |
      #| Monotributista    | PickUP     | Domicilio   | PaqArHoy     | Saldo         |
      #| Monotributista    | PickUP     | Domicilio   | PaqArHoy     | Mercado Pago  |
      #| Monotributista    | PickUp     | Sucursal    | Clasico      | Tarjeta       |
      #| Monotributista    | PickUp     | Sucursal    | Clasico      | Saldo         |
      #| Monotributista    | PickUp     | Sucursal    | Clasico      | Mercado Pago  |
      #| Monotributista    | PickUp     | Sucursal    | Expreso      | Tarjeta       |
      #| Monotributista    | PickUp     | Sucursal    | Expreso      | Saldo         |
      #| Monotributista    | PickUp     | Sucursal    | Expreso      | Mercado Pago  |
      #| Monotributista    | Sucursal   | Domicilio   | Clasico      | Tarjeta       |
      #| Monotributista    | Sucursal   | Domicilio   | Clasico      | Saldo         |
      #| Monotributista    | Sucursal   | Domicilio   | Clasico      | Mercado Pago  |
      #| Monotributista    | Sucursal   | Domicilio   | Expreso      | Tarjeta       |
      #| Monotributista    | Sucursal   | Domicilio   | Expreso      | Saldo         |
      #| Monotributista    | Sucursal   | Domicilio   | Expreso      | Mercado Pago  |
      #| Monotributista    | Sucursal   | Sucursal    | Clasico      | Tarjeta       |
      #| Monotributista    | Sucursal   | Sucursal    | Clasico      | Saldo         |
      #| Monotributista    | Sucursal   | Sucursal    | Clasico      | Mercado Pago  |
      #| Monotributista    | Sucursal   | Sucursal    | Expreso      | Tarjeta       |
      #| Monotributista    | Sucursal   | Sucursal    | Expreso      | Saldo         |
      #| Monotributista    | Sucursal   | Sucursal    | Expreso      | Mercado Pago  |
      #| Empresa           | PickUP     | Domicilio   | Clasico      | Tarjeta       |
      #| Empresa           | PickUP     | Domicilio   | Clasico      | Saldo         |
      #| Empresa           | PickUP     | Domicilio   | Clasico      | Mercado Pago  |
      #| Empresa           | PickUP     | Domicilio   | Clasico      | Cta. Corriente|
      #| Empresa           | PickUP     | Domicilio   | Expreso      | Tarjeta       |
      #| Empresa           | PickUP     | Domicilio   | Expreso      | Saldo         |
      #| Empresa           | PickUP     | Domicilio   | Expreso      | Mercado Pago  |
      #| Empresa           | PickUP     | Domicilio   | Expreso      | Cta. Corriente|
      #| Empresa           | PickUP     | Domicilio   | PaqArHoy     | Tarjeta       |
      #| Empresa           | PickUP     | Domicilio   | PaqArHoy     | Saldo         |
      #| Empresa           | PickUP     | Domicilio   | PaqArHoy     | Mercado Pago  |
      #| Empresa           | PickUP     | Domicilio   | PaqArHoy     | Cta. Corriente|
      #| Empresa           | PickUp     | Sucursal    | Clasico      | Tarjeta       |
      #| Empresa           | PickUp     | Sucursal    | Clasico      | Saldo         |
      #| Empresa           | PickUp     | Sucursal    | Clasico      | Mercado Pago  |
      #| Empresa           | PickUp     | Sucursal    | Clasico      | Cta. Corriente|
      #| Empresa           | PickUp     | Sucursal    | Expreso      | Tarjeta       |
      #| Empresa           | PickUp     | Sucursal    | Expreso      | Saldo         |
      #| Empresa           | PickUp     | Sucursal    | Expreso      | Mercado Pago  |
      #| Empresa           | PickUp     | Sucursal    | Expreso      | Cta. Corriente|
      #| Empresa           | Sucursal   | Domicilio   | Clasico      | Tarjeta       |
      #| Empresa           | Sucursal   | Domicilio   | Clasico      | Saldo         |
      #| Empresa           | Sucursal   | Domicilio   | Clasico      | Mercado Pago  |
      #| Empresa           | Sucursal   | Domicilio   | Clasico      | Cta. Corriente|
      #| Empresa           | Sucursal   | Domicilio   | Expreso      | Tarjeta       |
      #| Empresa           | Sucursal   | Domicilio   | Expreso      | Saldo         |
      #| Empresa           | Sucursal   | Domicilio   | Expreso      | Mercado Pago  |
      #| Empresa           | Sucursal   | Domicilio   | Expreso      | Cta. Corriente|
      #| Empresa           | Sucursal   | Sucursal    | Clasico      | Tarjeta       |
      #| Empresa           | Sucursal   | Sucursal    | Clasico      | Saldo         |
      #| Empresa           | Sucursal   | Sucursal    | Clasico      | Mercado Pago  |
      #| Empresa           | Sucursal   | Sucursal    | Clasico      | Cta. Corriente|
      #| Empresa           | Sucursal   | Sucursal    | Expreso      | Tarjeta       |
      #| Empresa           | Sucursal   | Sucursal    | Expreso      | Saldo         |
      #| Empresa           | Sucursal   | Sucursal    | Expreso      | Mercado Pago  |
      #| Empresa           | Sucursal   | Sucursal    | Expreso      | Cta. Corriente|

		#Ejemplos
  #| Consumidor final  | PickUP     | Domicilio   | Clasico      | Tarjeta       |
  #| Monotributista    | PickUP     | Domicilio   | Clasico      | Tarjeta       |
  #| Empresa           | PickUP     | Domicilio   | Clasico      | Tarjeta       |

		
