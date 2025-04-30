@EnvioIndivual
@RegresionRequerimientoEvolutivo
Feature: TE - Nuevo envio individual

  Background:
		#@PRECOND_MCIB-130
    Given el usuario se situa en los campos email y password

	#*Objetivo:* Verificar que un perfil pueda realizar un nuevo envío individual a través del sistema de la aplicación, siguiendo el flujo crítico establecido y concretando con el pago exitoso del mismo.
  @TEST_MCIB-87 @TESTSET_MCIB-84
  Scenario Outline: Nuevo envio individual
    Given el usuario '<tipoUsuario>' está logueado y en la page home
    When ingresa en nuevo envío individual
    And editar el '<origen>' del envío individual
    And llena los campos de paquete
    And selecciona el '<tipoEntrega>' completa el formulario de destino
    And selecciona el '<tipoProducto>' y procede a pagar
    And se muestra la grilla de checkout
    Then realiza el pago con '<medioPago>' del envío
    And se confirma que el pago se ha realizado con éxito

    Examples:
      | tipoUsuario       | origen   | tipoEntrega | tipoProducto | medioPago |
      #| Franquisia tipo 2 | Domicilio   | Clasico      | Tarjeta   |
      | Consumidor final  |Sucursal  |  Domicilio  | Clasico     | Saldo     |


		#Posibles combinaciones de tipoUsuario, tipoEntrega y tipoProducto
		#| Consumidor final |  Domicilio    | Clasico       | Tarjeta          |
		#| Consumidor final |  Domicilio    | Clasico       | Saldo            |
		#| Consumidor final |  Domicilio    | Clasico       | Mercado Pago     |
		#| Consumidor final |  Domicilio    | Expreso       | Tarjeta          |
		#| Consumidor final |  Domicilio    | Expreso       | Saldo            |
		#| Consumidor final |  Domicilio    | Expreso       | Mercado Pago     |
		#| Consumidor final |  Sucursal     | Clasico       | Tarjeta          |
		#| Consumidor final |  Sucursal     | Clasico       | Saldo            |
		#| Consumidor final |  Sucursal     | Clasico       | Mercado Pago     |
		#| Consumidor final |  Sucursal     | Expreso       | Tarjeta          |
		#| Consumidor final |  Sucursal     | Expreso       | Saldo            |
		#| Consumidor final |  Sucursal     | Expreso       | Mercado Pago     |

		#| Monotributista   |  Domicilio    | Clasico       | Tarjeta          |
		#| Monotributista   |  Domicilio    | Clasico       | Saldo            |
		#| Monotributista   |  Domicilio    | Clasico       | Mercado Pago     |
		#| Monotributista   |  Domicilio    | Expreso       | Tarjeta          |
		#| Monotributista   |  Domicilio    | Expreso       | Saldo            |
		#| Monotributista   |  Domicilio    | Expreso       | Mercado Pago     |
		#| Monotributista   |  Sucursal     | Clasico       | Tarjeta          |
		#| Monotributista   |  Sucursal     | Clasico       | Saldo            |
		#| Monotributista   |  Sucursal     | Clasico       | Mercado Pago     |
		#| Monotributista   |  Sucursal     | Expreso       | Tarjeta          |
		#| Monotributista   |  Sucursal     | Expreso       | Saldo            |
		#| Monotributista   |  Sucursal     | Expreso       | Mercado Pago     |

		#| Empresa          |  Domicilio    | Clasico       | Tarjeta          |
		#| Empresa          |  Domicilio    | Clasico       | Saldo            |
		#| Empresa          |  Domicilio    | Clasico       | Cuenta Corriente |
		#| Empresa          |  Domicilio    | Clasico       | Mercado Pago     |
		#| Empresa          |  Domicilio    | Expreso       | Tarjeta          |
		#| Empresa          |  Domicilio    | Expreso       | Saldo            |
		#| Empresa          |  Domicilio    | Expreso       | Cuenta Corriente |
		#| Empresa          |  Domicilio    | Expreso       | Mercado Pago     |
		#| Empresa          |  Sucursal     | Clasico       | Tarjeta          |
		#| Empresa          |  Sucursal     | Clasico       | Saldo            |
		#| Empresa          |  Sucursal     | Clasico       | Cuenta Corriente |
		#| Empresa          |  Sucursal     | Clasico       | Mercado Pago     |
		#| Empresa          |  Sucursal     | Expreso       | Tarjeta          |
		#| Empresa          |  Sucursal     | Expreso       | Saldo            |
		#| Empresa          |  Sucursal     | Expreso       | Cuenta Corriente |
		#| Empresa          |  Sucursal     | Expreso       | Mercado Pago     |

		#| Usuario Invitado |  Domicilio    | Clasico       | Tarjeta          |
		#| Usuario Invitado |  Domicilio    | Clasico       | Saldo            |
		#| Usuario Invitado |  Domicilio    | Clasico       | Cuenta Corriente |
		#| Usuario Invitado |  Domicilio    | Clasico       | Mercado Pago     |
		#| Usuario Invitado |  Domicilio    | Expreso       | Tarjeta          |
		#| Usuario Invitado |  Domicilio    | Expreso       | Saldo            |
		#| Usuario Invitado |  Domicilio    | Expreso       | Cuenta Corriente |
 		#| Usuario Invitado |  Domicilio    | Expreso       | Mercado Pago     |
		#| Usuario Invitado |  Sucursal     | Clasico       | Tarjeta          |
		#| Usuario Invitado |  Sucursal     | Clasico       | Saldo            |
		#| Usuario Invitado |  Sucursal     | Clasico       | Cuenta Corriente |
		#| Usuario Invitado |  Sucursal     | Clasico       | Mercado Pago     |
		#| Usuario Invitado |  Sucursal     | Expreso       | Tarjeta          |
		#| Usuario Invitado |  Sucursal     | Expreso       | Saldo            |
		#| Usuario Invitado |  Sucursal     | Expreso       | Cuenta Corriente |
		#| Usuario Invitado |  Sucursal     | Expreso       | Mercado Pago     |

		#Ejemplos
		#| Consumidor final |  Domicilio    | Clasico       | Tarjeta          |
		#| Monotributista   |  Sucursal     | Expreso       | Saldo            |
		#| Empresa          |  Domicilio    | Clasico       | Cuenta Corriente |
		
