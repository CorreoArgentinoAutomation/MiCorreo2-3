@MiCorreo2
Feature: Mis envios

Scenario Outline: Configuracion de datos del usuario

  Given el usuario "<tipoUsuario>" se situa en los campos email y password
    And el usuario se dirige a las configuraciones de la cuenta
    And el usuario configura un remitente


  Examples:
    | tipoUsuario      |
    | Consumidor final |
