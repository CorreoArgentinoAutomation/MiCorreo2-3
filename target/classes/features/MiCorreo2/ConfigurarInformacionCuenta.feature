@MiCorreo2
  @prueba
Feature: Mis envios

Scenario Outline: Configuracion de datos del usuario

  Given el usuario "<tipoUsuario>" se situa en los campos email y password
    And el usuario se dirige a las configuraciones de la cuenta
    And el usuario cambia la informacion de la cuenta
    When el usuario valida el visualiza el popup de confirmacion


  Examples:
    | tipoUsuario      |
    | Consumidor final |
