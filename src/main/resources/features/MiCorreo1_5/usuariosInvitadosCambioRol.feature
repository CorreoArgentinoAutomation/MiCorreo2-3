@UsuariosInvitados
Feature: Gestión de roles y activación de usuarios



	Background:

		Given el usuario se situa en la pestaña agregar usuarios


	Scenario: Cambio de rol de un usuario
		Given un usuario existente con rol "Usuario Invitado"
		When un administrador accede a la gestión de usuarios
		And selecciona un usuario
		And cambia el rol del usuario a "Con Rol de Pago"
		Then muestra un mensaje de rol asignado correctamente


	Scenario: Activación de un usuario
		Given un usuario existente con estado "Inactivo"
		When un administrador accede a la gestión de usuarios
		And selecciona al usuario
		And activa la cuenta del usuario
		Then muestra un mensaje usuario activado correctamente

	Scenario: Desactivación de un usuario
		Given un usuario existente con estado "Activo"
		When un administrador accede a la gestión de usuarios
		And selecciona al usuario específico
		And desactiva la cuenta del usuario
		Then muestra el mensaje usuario desactivado correctamente

