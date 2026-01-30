@Jenkins
@MantenerSesion
Feature: Jenkins - Mantener sesión en ventana del navegador

  Como usuario con Jenkins portable en localhost
  Quiero que el navegador abra Jenkins, inicie sesión y recargue la página 1 vez cada minuto durante 2 horas
  Para mantener la sesión activa en una ventana del navegador

  Scenario: Login en Jenkins y recarga 1 vez cada minuto durante 2 horas
    Given abro el navegador en la URL de Jenkins "http://localhost:8080"
    And inicio sesión en Jenkins con usuario "Rodrigo" y contraseña "Puerto12+33"
    And recargo la página 1 vez cada minuto durante 2 horas

  # Tiempo variable: mismo comportamiento pero durante los minutos indicados
  Scenario Outline: Login y recarga 1 vez cada minuto durante tiempo variable
    Given abro el navegador en la URL de Jenkins "http://localhost:8080"
    And inicio sesión en Jenkins con usuario "Rodrigo" y contraseña "Puerto12+33"
    And recargo la página 1 vez cada minuto durante <minutos> minutos

    Examples:
      | minutos |
      | 120     |
      | 2       |
