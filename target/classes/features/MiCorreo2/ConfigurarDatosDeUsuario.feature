@MiCorreo2
  @pruebalis
Feature: Mis envios

Scenario Outline: Configuracion de datos del usuario

  Given el usuario "<tipoUsuario>" se situa en los campos email y password
    And el usuario se dirige a las configuraciones de la cuenta
    And el usuario cambia los datos de la cuenta


  Examples:
    | tipoUsuario      |
    | Consumidor final |
