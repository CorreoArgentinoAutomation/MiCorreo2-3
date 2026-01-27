@SellosDigitales
@Regresion1.5
@PTA-45
Feature: Gestion de pedidos y generacion de sellos digitales

	Background:
		Given el usuario se situa en los campos email y password

	Scenario Outline: Crear y gestionar un pedido con usuario franquicia tipo 2
		Given el usuario accede a Mi Correo con el usuario Franquicia tipo 2
		When el usuario accede a Punto Correo, Nuevo Pedido
		And el usuario selecciona un '<Producto>', la '<Cantidad>' y presiona Guardar
		And el usuario hace clic en Ir a pendientes
		And el usuario selecciona los productos y presiona Cotizar
		And el usuario presiona Generar
		And el usuario hace clic en Imprimi los sellos digitales que pagaste aqui
		And el usuario busca el primer producto generado y hace clic en consultar
		And el usuario genera el sello digital para el '<Producto>' seleccionado
		And el usuario accede a Punto Correo Acceso a MiniPaqar
		And el usuario accede a Imposicion Envios Imposicion Postal
		And el usuario llena el formulario de Imposicion Postal y confirma
		Then el pedido se impone correctamente.


		Examples:
			| Producto  	     | Cantidad |
			| Carta Simple 20g   | 1        |
			| Carta Simple 150g  | 1        |
			| Carta Certificada  | 1        |
			| Carta Expreso      | 1        |
			| Carta Documento    | 1        |


		#Carta Simple 20g ejemplo T&T MS000013154AR
		#Carta Simple 150g ejemplo T&T MS000013168AR
		#Carta Certificada ejemplo T&T MU000002911AR
		#Carta Expreso ejemplo T&T MX000033012AR
		#Carta Documento ejemplo T&T MD000004387AR

