@UsuariosInvitados
Feature: Gestion activacion de usuarios

	Scenario Outline: Activacion de un usuario
		Given el usuario se situa en los campos email y password
		When el usuario '<tipoUsuario>' está logueado y en la page home
		And un administrador accede a la gestion de usuarios
		And selecciona al usuario '<tipoDeRol>'
		And activa la cuenta del usuario
		Then muestra un mensaje usuario activado correctamente

		Examples:
			| tipoUsuario|  tipoDeRol|
			| Empresa |     ConRolPago|



	#Scenario: Desactivación de un usuario
		#Given un usuario existente con estado "Activo"
		#When un administrador accede a la gestión de usuarios
		#And selecciona al usuario específico
		#And desactiva la cuenta del usuario
		#Then muestra el mensaje usuario desactivado correctamente

