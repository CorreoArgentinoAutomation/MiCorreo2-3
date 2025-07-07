package page.PlataformaIntegracion.Woocommerce;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends BasePage {

    private By btncheckup = By.xpath("(//a[@class='wp-block-pages-list__item__link wp-block-navigation-item__content'])[2]");
    private By btnCarrito = By.xpath("(//a[@class='wp-block-pages-list__item__link wp-block-navigation-item__content'])[1]");
    private By btnVerCarrito = By.xpath("(//a[@class='added_to_cart wc_forward'])[1]");
    private By menuShop = By.xpath("(//a[@class='wp-block-pages-list__item__link wp-block-navigation-item__content'])[5]");
    private By btnAgregarAlcarrito = By.xpath("(//span[@data-wc-text='state.addToCartText'])[1]");
    private By menuGroceries = By.xpath("(//a[@class='menu-link'])[2]");

    private By btnAgregarAcarrito = By.xpath("//button[@type='submit']");

    private By btnVerCarrito2 = By.xpath("(//a[contains(.,'Ver carrito')])[1]");
    private By MenuCarrito = By.xpath("//a[.='Carrito']");
    private By actualizarCarrito = By.xpath("//button[@name='update_cart']");
    private By irCheckOut = By.xpath("//a[.='Finalizar compra']");
    private By btnAgregarAlcarritoR = By.xpath("//button[@name='add-to-cart']");
    public Home(WebDriver driver) {
        super(driver);
    }

//    public void agregarAlCarrito() {
//        for (int i = 1; i <= 1; i++) {
//            String productoLocator = "(//a[@class='woocommerce-LoopProduct-link woocommerce-loop-product__link'])[" + i + "]";
//            click(menuGroceries);
//            click(By.xpath(productoLocator));
//            click(btnAgregarAcarrito);
//        }
//
//        System.out.println("Se agregaron todos los productos al carrito");
//    }

    public void agregarAlCarrito() {

            click(menuShop);
            waitForSeconds(1);
            click(btnAgregarAlcarrito);
            System.out.println("Se agregaron todos los productos al carrito");
            scrollPageUpDown(3,0);
            waitForSeconds(25);

    }


    //1
    public void irAlTienda() {
        click(menuShop);
    }
    //2
    public void agregarAlProductoCarrito() {
        waitForSeconds(1);
        click(btnAgregarAlcarrito);
        scrollPageUpDown(3,0);
        waitForSeconds(25);
    }
    //3
    public void irAlCarrito() {
        click(btnCarrito);
    }
    public void irAlCheckup() {
        click(btncheckup);
    }


    //Flujo a seguir en el Home
    //1 - Dirigirse a la tienda que contiene los productos
    //2 - Agregar algun producto al carrito  esperar que se cargue en el carrito
    //3 - Dirigirse al Carrito

    public void generarPedidoHome(){
        irAlTienda();
        agregarAlProductoCarrito();
        irAlCheckup();
    }

    //seleccionar varios productos para el algoritmo
    public void agregarAlProductosAValidar(String repetir) {
        waitForSeconds(1);
        //click(btnAgregarAlcarrito);
        int numero = Integer.parseInt(repetir);

        for (int i = 1; i <= numero; i++) { // Cambia 10 por el número máximo de elementos que esperas
            By btnAgregarAlcarrito = By.xpath("(//span[@data-wc-text='state.addToCartText'])[" + i + "]");
            // Aquí puedes agregar el código para interactuar con el elemento, por ejemplo:
             //driver.findElement(btnAgregarAlcarrito).click();

            waitForSeconds(10);
            click(btnAgregarAlcarrito);
            waitForSeconds(20);
        }
        //scrollPageUpDown(3,0);
        //irAlCarrito();

        waitForSeconds(15);
        //click(btnCarrito);
        //click(btnVerCarrito2);

    }


    public void agregarProductosAlCarrito(String repetir) {
        waitForSeconds(1);
        //click(btnAgregarAlcarrito);
        int numero = Integer.parseInt(repetir);

        for (int i = 1; i <= numero; i++) { // Cambia 10 por el número máximo de elementos que esperas
            By aumentarProductos = By.xpath("(//input[@class='input-text qty text'])[" + i + "]");
            // Aquí puedes agregar el código para interactuar con el elemento, por ejemplo:
            //driver.findElement(btnAgregarAlcarrito).click();
            waitForSeconds(1);
            click(aumentarProductos);
            writeText(aumentarProductos, "2");
            //waitForSeconds(1);
        }
        scrollPageUpDown(0,1);
        click(actualizarCarrito);
        waitForSeconds(10);
        scrollPageUpDown(2,0);

    }


    public void seleccionarLosProductosNecesarios(String nombreProducto) {

        switch (nombreProducto){
            case "paquete1","paquete2":
                agregarAlProductosAValidar("1");
                break;
            case "paquete3","paquete4","paquete11","paquete12":
                agregarAlProductosAValidar("2");
                break;
            case "paquete5","paquete6","paquete7","paquete8":
                agregarAlProductosAValidar("3");
                break;
            case "paquete9":
                agregarAlProductosAValidar("4");
                break;
            case "paquete10":
                agregarAlProductosAValidar("5");
                agregarAlProductosAValidar("5");
                //agregarProductosAlCarrito("5");
                break;
            default:
                System.out.println("No Esta definido ese producto: " + nombreProducto + "no existe");
        }
        click(MenuCarrito);
        click(irCheckOut);

    }


    public void seleccionarLosProductosNecesarios2(String caso) {

        switch (caso){
            case "limte de peso":
                xpathDinamicoProducto("producto1-100-80-10");
                //System.out.println("Limite de peso");
                break;
            case "paquete3","paquete4","paquete11","paquete12":
                agregarAlProductosAValidar("2");
                break;
            case "paquete5","paquete6","paquete7","paquete8":
                agregarAlProductosAValidar("3");
                break;
            case "paquete9":
                agregarAlProductosAValidar("4");
                break;
            case "paquete10":
                agregarAlProductosAValidar("5");
                agregarAlProductosAValidar("5");
                //agregarProductosAlCarrito("5");
                break;
            default:
                System.out.println("No Esta definido ese producto: " + caso + "no existe");
        }

    }

    public void xpathDinamicoProducto(String textoProducto) {
        //String xpath = String.format("//*[text()='%s'] and .//button//span[text()='Agregar al carrito']]", textoProducto);
        String xpath = String.format("//*[text()='%s']", textoProducto);
        click(By.xpath(xpath));
        waitForSeconds(1);
        click(btnAgregarAlcarritoR);
        click(menuShop);
        //System.out.println("El Xpath generado es: "+xpath);
        System.out.println("El producto agregado al carrito es: "+textoProducto);
    }

    public void irAlCarrito(String textoProducto) {
        click(MenuCarrito);
    }

}
