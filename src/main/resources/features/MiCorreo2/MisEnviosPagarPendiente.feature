@MiCorreo2
Feature: Mis envios

Scenario Outline: Nuevo envío individual

  Given el usuario "<tipoUsuario>" se situa en los campos email y password
    And el usuario se dirige a los envios pendientes


  Examples:
    | tipoUsuario      |
    | Consumidor final |




