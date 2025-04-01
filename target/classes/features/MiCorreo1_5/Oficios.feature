@Registro
Feature: Creación de oficios judiciales

	Scenario Outline: Crear oficio judicial
		Given el usuario se situa en los campos email y password
			When el usuario '<tipoUsuario>' está logueado y en la page home
			And el usuario ingresa la pantalla de Oficios Judiciales
			#And el usuario selecciona y elimina todos los oficios
			And el usuario rellena el formulario de oficios
			Then el usuario visualiza el message de oficio creado correctamente
			And el usuario realiza el pago con el '<medioPago>' seleccionado
			Then el usuario visualiza el mensaje de pago correctamente

		Examples:
			| tipoUsuario      | medioPago |
			| Usuario Invitado | Saldo     |
		

