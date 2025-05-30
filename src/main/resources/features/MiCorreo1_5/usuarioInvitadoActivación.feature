@UsuariosInvitados
Feature: Gestión activación de usuarios

	Scenario Outline: Activación de un usuario
		Given el usuario '<tipoUsuario>' está logueado y en la page home
		And un usuario existente con estado "Inactivo"
		When un administrador accede a la gestión de usuarios
		And selecciona al usuario
		And activa la cuenta del usuario
		Then muestra un mensaje usuario activado correctamente

		Examples:
			| tipoUsuario      |
			| Usuario Invitado |


