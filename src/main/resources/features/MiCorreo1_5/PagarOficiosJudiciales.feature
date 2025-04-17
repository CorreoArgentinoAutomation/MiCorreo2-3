@PagoOficioJudicial
	@RegresionRequerimientoEvolutivo
Feature: Pagar oficio judicial

	Scenario Outline: Pagar oficio judicial
		Given el usuario se situa en los campos email y password
			When el usuario '<tipoUsuario>' está logueado y en la page home
			And el usuario ingresa la pantalla de Oficios Judiciales
			And el usuario rellena el formulario de oficios
			Then el usuario visualiza el message de oficio creado correctamente
			And el usuario realiza el pago con el '<medioPago>' seleccionado
			Then el usuario visualiza el mensaje de pago correctamente

		Examples:
			| tipoUsuario      | medioPago |
			| Consumidor final | Saldo     |
			| Monotributista   | Saldo     |
			| Empresa 		   | Saldo     |
			| Usuario Invitado | Saldo     |


