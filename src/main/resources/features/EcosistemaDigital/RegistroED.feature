@RegistroEcosistemaDigital
Feature: Registro de usuario

  Scenario: Registro exitoso de un nuevo usuario
    Given el usuario crea un email temporal
    When que el usuario ingresa al sitio de ecosistema digital
    And el usuario hace clic en el boton Registrarme
    And el usuario llena el formulario de registro
    And el usuario abre el servicio de correo electronico
    And el usuario hace clic en el boton Activa tu usuario
    Then el usuario deberia ver el mensaje de bienvenida

  Scenario Outline: Validación de campos del formulario de registro
    Given que el usuario ingresa al sitio de ecosistema digital
    And el usuario hace clic en el boton Registrarme
    When el usuario ingresa "<campo>" con el valor "<valor>"
    And el usuario hace clic en el boton Siguiente
    Then el sistema debe mostrar el mensaje de validación "<mensajeEsperado>"

    Examples:
      | campo               | valor                            | mensajeEsperado                                                      |
      | Nombre              |                                  | Debe completar el nombre.                                            |
      | Nombre              | a                                | El nombre debe contener entre 3 y 30 caracteres.                     |
      | Nombre              | ab                               | El nombre debe contener entre 3 y 30 caracteres.                     |
      | Nombre              | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa | El nombre debe contener entre 3 y 30 caracteres.                     |
      | Apellido            |                                  | Debe completar el apellido.                                          |
      | Apellido            | a                                | El apellido debe contener entre 3 y 30 caracteres.                   |
      | Apellido            | ab                               | El apellido debe contener entre 3 y 30 caracteres.                   |
      | Apellido            | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa | El apellido debe contener entre 3 y 30 caracteres.                   |
      | Tipo                |                                  | Debe seleccionar un tipo de documento.                               |
      | Documento           |                                  | Debe completar el documento de identidad.                            |
      | Documento           | 1                                | El número de documento debe contener entre 6 y 10 caracteres.        |
      | Documento           | 12                               | El número de documento debe contener entre 6 y 10 caracteres.        |
      | Documento           | 123                              | El número de documento debe contener entre 6 y 10 caracteres.        |
      | Documento           | 1234                             | El número de documento debe contener entre 6 y 10 caracteres.        |
      | Documento           | 12345                            | El número de documento debe contener entre 6 y 10 caracteres.        |
      | Documento           | 12345678901                      | El número de documento debe contener entre 6 y 10 caracteres.        |
      | Celular             |                                  | Debe completar el teléfono.                                          |
      | Celular             | 1                                | El número de teléfono debe contener entre 6 y 15 caracteres.         |
      | Celular             | 12                               | El número de teléfono debe contener entre 6 y 15 caracteres.         |
      | Celular             | 123                              | El número de teléfono debe contener entre 6 y 15 caracteres.         |
      | Celular             | 1234                             | El número de teléfono debe contener entre 6 y 15 caracteres.         |
      | Celular             | 12345                            | El número de teléfono debe contener entre 6 y 15 caracteres.         |
      | Celular             | 1234567890123456                 | El número de teléfono debe contener entre 6 y 15 caracteres.         |
      | Email               |                                  | Debe completar el email.                                             |
      | Email               | 2                                | El email ingresado tiene formato inválido.                           |
      | Email               | test                             | El email ingresado tiene formato inválido.                           |
      | Email               | test@                            | El email ingresado tiene formato inválido.                           |
      | Email               | @test.com                        | El email ingresado tiene formato inválido.                           |
      | Email               | test@test                        | El email ingresado tiene formato inválido.                           |
      | Contraseña          |                                  | Debe completar la contraseña.                                        |
      | Contraseña          | 1                                | La contraseña debe contener entre 6 y 20 caracteres.                 |
      | Contraseña          | 12                               | La contraseña debe contener entre 6 y 20 caracteres.                 |
      | Contraseña          | 123                              | La contraseña debe contener entre 6 y 20 caracteres.                 |
      | Contraseña          | 1234                             | La contraseña debe contener entre 6 y 20 caracteres.                 |
      | Contraseña          | 12345                            | La contraseña debe contener entre 6 y 20 caracteres.                 |
      | Contraseña          | 123456789012345678901            | La contraseña debe contener entre 6 y 20 caracteres.                 |
      | ConfirmarContraseña |                                  | Debe completar la confirmación de contraseña.                        |
      | ConfirmarContraseña | 1                                | La confirmación de contraseña debe contener entre 6 y 20 caracteres. |
      | ConfirmarContraseña | 12                               | La confirmación de contraseña debe contener entre 6 y 20 caracteres. |
      | ConfirmarContraseña | 123                              | La confirmación de contraseña debe contener entre 6 y 20 caracteres. |
      | ConfirmarContraseña | 1234                             | La confirmación de contraseña debe contener entre 6 y 20 caracteres. |
      | ConfirmarContraseña | 12345                            | La confirmación de contraseña debe contener entre 6 y 20 caracteres. |
      | ConfirmarContraseña | 123456789012345678901            | La confirmación de contraseña debe contener entre 6 y 20 caracteres. |

