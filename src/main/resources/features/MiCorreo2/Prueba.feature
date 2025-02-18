@pruebalis
Feature: Configuracion de medidas frecuentes

Scenario Outline: Configuracion de medidas frecuentes

  Given el usuario "<tipoUsuario>" se situa en los campos email y password
    And el usuario se dirige a las configuraciones de la cuenta
    And el usuario agrega una medida frecuente


  Examples:
    | tipoUsuario      |
    | Consumidor final |


