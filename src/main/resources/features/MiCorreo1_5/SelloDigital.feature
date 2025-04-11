@SellosDigitales
Feature: Gestion de pedidos y generacion de sellos digitales

	Background:
		Given el usuario se situa en los campos email y password

	Scenario Outline: Crear y gestionar un pedido con usuario franquicia tipo 2
		Given el usuario '<tipoUsuario>' está logueado y en la page home
		When el usuario accede a Punto Correo, Nuevo Pedido
		And el usuario selecciona un '<Producto>', la '<Cantidad>' y presiona Guardar
		And el usuario hace clic en Ir a pendientes
		And el usuario selecciona los productos y presiona Cotizar
		And el usuario presiona Generar
		And el usuario hace clic en Imprimi los sellos digitales que pagaste aqui
		And el usuario busca el primer producto generado y hace clic en consultar
		#And el usuario escanea el codigo QR o barra
		#And el usuario accede a Punto Correo Pagados
		#And el usuario hace clic en el boton Consultar
		#And el usuario selecciona el Numero de TT de algun producto y lo guarda
		#And el usuario accede a Punto Correo Acceso a MiniPaqar
		#And el usuario accede a Imposicion Envios Imposicion Postal
		#And el usuario ingresa el correo electronico del usuario tipo 2
		#And el usuario ingresa la confirmacion del correo electronico
		#And el usuario ingresa el numero de TT previamente guardado
		#And el usuario hace clic en Confirmar
		#And el usuario hace clic en Confirmar en el popup de confirmacion
		#Then el pedido se gestiona correctamente y se genera el sello digital

		Examples:
			| tipoUsuario      | Producto  		  | Cantidad |
			| Franquisia tipo 2| Carta Simple 20g | 3        |


