@Jenkins
@MantenerSesion
Feature: Jenkins - Mantener sesión en ventana del navegador

  Como usuario con Jenkins portable en localhost
  Quiero que el navegador abra Jenkins, inicie sesión y recargue la página cada aproximadamente 2 horas
  Para mantener la sesión activa en una ventana del navegador

  Scenario: Login en Jenkins y recarga periódica cada aproximadamente 2 horas
    Given abro el navegador en la URL de Jenkins "http://localhost:8080"
    And inicio sesión en Jenkins con usuario "Rodrigo" y contraseña "Puerto12+33"
    And recargo la página cada aproximadamente 2 horas durante 1 ciclo

  # Para pruebas rápidas: usar minutos pequeños (ej. 0.5 = 30 segundos)
  Scenario Outline: Login y recarga con intervalo configurable
    Given abro el navegador en la URL de Jenkins "http://localhost:8080"
    And inicio sesión en Jenkins con usuario "Rodrigo" y contraseña "Puerto12+33"
    And recargo la página cada aproximadamente <minutos> minutos durante <ciclos> ciclos

    Examples:
      | minutos | ciclos |
      | 120     | 1      |
      | 0.5     | 2      |
