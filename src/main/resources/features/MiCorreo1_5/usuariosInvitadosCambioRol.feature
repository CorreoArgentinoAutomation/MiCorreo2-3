@UsuariosInvitados
Feature: Gestión de roles y activación de usuarios


	Scenario Outline: Cambio de rol de un usuario
		Given el usuario '<tipoUsuario>' está logueado y en la page home
		And selecciona Mi cuenta
		And selecciona un usuario
		When cambia el rol del usuario a "Con Rol de Pago"
		Then muestra un mensaje de rol cambiado correctamente

		Examples:
			| tipoUsuario      |
			| Usuario Invitado |





