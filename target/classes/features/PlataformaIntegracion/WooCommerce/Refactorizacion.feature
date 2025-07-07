@PPDIPI-3232
Feature: Compra de productos en la tienda a través de WordPress y Correo Argentino
  Como usuario de la tienda
  Quiero comprar productos y seleccionar opciones de envío
  Para poder completar mi compra y verificar el número de pedido

  Scenario Outline: Compra exitosa con envío a sucursal y verificación del numero de pedido
    Given que estoy en el Dashboard de WordPress
    #And hago clic en el boton de plugin
    #And desactivo el plugin
    #And Activo el plugin
    #When hago clic en el boton Conexion Api
    #And hago clic en el menu desplegable de Servicio
    #And selecciono el servicio Mi Correo
    #And hago clic en el boton Guardar Cambios
    #And ingreso el usuario y la contrasena con el "<tipoUsuario>"
    #And completo los datos del negocio en el formulario
    #Then valido que se guarden los a justes

    And Me dirijo a la tienda de WooCommerce
    #And Me dirijo al catalogo de la tienda
    #And selecciono los productos que quiero segun el "<caso>"

    #And Me dirijo al carrito
    #And ir a calcular el envio

    And Me dirijo al checkout

    And selecciono una opcion de "<envio>"

    Examples:
      | tipoUsuario      | caso          | envio               |
      | Consumidor final | limte de peso | expreso a domicilio |

