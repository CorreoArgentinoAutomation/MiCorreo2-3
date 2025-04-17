@UsuariosInvitados
Feature: Gestión activación de usuarios



	Background:

		Given el usuario se situa en la pestaña Mis Usuarios

	Scenario Outline: Activación de un usuario
		Given el usuario '<tipoUsuario>' está logueado y en la page home
		And un usuario existente con estado "Inactivo"
		When un administrador accede a la gestión de usuarios
		And selecciona al usuario
		And activa la cuenta del usuario
		Then muestra un mensaje usuario activado correctamente

		Examples:
			| tipoUsuario      | tipoEntrega   | tipoProducto  | medioPago        |
			| Usuario Invitado |  Domicilio    | Clasico       | Mercado Pago     |


	Scenario: Desactivación de un usuario
		Given un usuario existente con estado "Activo"
		When un administrador accede a la gestión de usuarios
		And selecciona al usuario específico
		And desactiva la cuenta del usuario
		Then muestra el mensaje usuario desactivado correctamente

