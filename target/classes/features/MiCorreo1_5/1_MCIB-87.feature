@MCIB-133
Feature: TE - Nuevo envio individual

	Background:
		#@PRECOND_MCIB-130
		Given el usuario se situa en los campos email y password

	#*Objetivo:* Verificar que un perfil pueda realizar un nuevo envío individual a través del sistema de la aplicación, siguiendo el flujo crítico establecido y concretando con el pago exitoso del mismo.
	@TEST_MCIB-87 @TESTSET_MCIB-84
	Scenario Outline: Nuevo envio individual
		Given el usuario '<tipoUsuario>' está logueado y en la page home
		  When ingresa en nuevo envío individual
		  And llena los campos de paquete
		  And selecciona el '<tipoEntrega>' completa el formulario de destino
		  And selecciona el '<tipoProducto>' y procede a pagar
		  And se muestra la grilla de checkout
		  Then realiza el pago con '<medioPago>' del envío
		  And se confirma que el pago se ha realizado con éxito
		
		  Examples:
		| tipoUsuario      | tipoEntrega   | tipoProducto  | medioPago        |
		| Consumidor final |  Domicilio    | Clasico       | Mercado Pago     |


		#Posibles combinaciones de tipoUsuario, tipoEntrega y tipoProducto
		#| Consumidor final |  Domicilio    | Clasico       | Tarjeta          |
		#| Consumidor final |  Domicilio    | Clasico       | Saldo            |
		#| Consumidor final |  Domicilio    | Clasico       | Cuenta Corriente |
		#| Consumidor final |  Domicilio    | Expreso       | Tarjeta          |
		#| Consumidor final |  Domicilio    | Expreso       | Saldo            |
		#| Consumidor final |  Domicilio    | Expreso       | Cuenta Corriente |
		#| Consumidor final |  Sucursal     | Clasico       | Tarjeta          |
		#| Consumidor final |  Sucursal     | Clasico       | Saldo            |
		#| Consumidor final |  Sucursal     | Clasico       | Cuenta Corriente |
		#| Consumidor final |  Sucursal     | Expreso       | Tarjeta          |
		#| Consumidor final |  Sucursal     | Expreso       | Saldo            |
		#| Consumidor final |  Sucursal     | Expreso       | Cuenta Corriente |

		#| Monotributista   |  Domicilio    | Clasico       | Tarjeta          |
		#| Monotributista   |  Domicilio    | Clasico       | Saldo            |
		#| Monotributista   |  Domicilio    | Clasico       | Cuenta Corriente |
		#| Monotributista   |  Domicilio    | Expreso       | Tarjeta          |
		#| Monotributista   |  Domicilio    | Expreso       | Saldo            |
		#| Monotributista   |  Domicilio    | Expreso       | Cuenta Corriente |
		#| Monotributista   |  Sucursal     | Clasico       | Tarjeta          |
		#| Monotributista   |  Sucursal     | Clasico       | Saldo            |
		#| Monotributista   |  Sucursal     | Clasico       | Cuenta Corriente |
		#| Monotributista   |  Sucursal     | Expreso       | Tarjeta          |
		#| Monotributista   |  Sucursal     | Expreso       | Saldo            |
		#| Monotributista   |  Sucursal     | Expreso       | Cuenta Corriente |

		#| Empresa          |  Domicilio    | Clasico       | Tarjeta          |
		#| Empresa          |  Domicilio    | Clasico       | Saldo            |
		#| Empresa          |  Domicilio    | Clasico       | Cuenta Corriente |
		#| Empresa          |  Domicilio    | Expreso       | Tarjeta          |
		#| Empresa          |  Domicilio    | Expreso       | Saldo            |
		#| Empresa          |  Domicilio    | Expreso       | Cuenta Corriente |
		#| Empresa          |  Sucursal     | Clasico       | Tarjeta          |
		#| Empresa          |  Sucursal     | Clasico       | Saldo            |
		#| Empresa          |  Sucursal     | Clasico       | Cuenta Corriente |
		#| Empresa          |  Sucursal     | Expreso       | Tarjeta          |
		#| Empresa          |  Sucursal     | Expreso       | Saldo            |
		#| Empresa          |  Sucursal     | Expreso       | Cuenta Corriente |

		#Ejemplos
		#| Consumidor final |  Domicilio    | Clasico       | Tarjeta          |
		#| Monotributista   |  Sucursal     | Expreso       | Saldo            |
		#| Empresa          |  Domicilio    | Clasico       | Cuenta Corriente |
		
