@Oficios

Feature: Gestión de Oficios Judiciales

Scenario Outline: Registrar un oficio judicial

  Given el usuario "<tipoUsuario>" se situa en los campos email y password
    And el usuario se dirige a servicios y oficios judiciales
    And el usuario rellena el formulario y guarda el oficio




  Examples:
    | tipoUsuario      |
    | Consumidor final |
