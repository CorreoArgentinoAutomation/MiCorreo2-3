package page.Jenkins;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Page Object para la pantalla de login de Jenkins (localhost:8080).
 * Formulario estándar con j_username, j_password y envío a j_security_check.
 */
public class PageJenkinsLogin extends BasePage {

    public PageJenkinsLogin(WebDriver driver) {
        super(driver);
    }

    // Selectores del formulario de login de Jenkins
    private final By inputUsuario = By.name("j_username");
    private final By inputPassword = By.name("j_password");
    private final By btnSubmit = By.name("Submit");

    /**
     * Navega a la URL de Jenkins (ej. http://localhost:8080).
     */
    public void navegarA(String url) {
        getDriver().get(url);
    }

    /**
     * Completa usuario y contraseña y envía el formulario de login.
     */
    public void login(String usuario, String password) {
        waitUntilElementVisible(inputUsuario);
        writeText(inputUsuario, usuario);
        writeText(inputPassword, password);
        click(btnSubmit);
    }

    /**
     * Recarga la página actual.
     */
    public void recargarPagina() {
        getDriver().navigate().refresh();
    }

    /**
     * Espera un tiempo variable centrado en «minutos» (ej. 120 = ~2 horas).
     * Se aplica una variación de ±10% para que el intervalo no sea fijo.
     * Luego recarga la página.
     *
     * @param minutosDuracionMin du mínimo en minutos (ej. 108 para ~1h48m)
     * @param minutosDuracionMax du máximo en minutos (ej. 132 para ~2h12m)
     */
    public void esperarTiempoVariableMinutosYRecargar(double minutosDuracionMin, double minutosDuracionMax) {
        double minutos = ThreadLocalRandom.current().nextDouble(minutosDuracionMin, minutosDuracionMax);
        long milisegundos = (long) (minutos * 60 * 1000);
        System.out.println("[Jenkins] Esperando " + String.format("%.2f", minutos) + " minutos (" + (milisegundos / 1000) + " segundos) antes de recargar...");
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Espera interrumpida", e);
        }
        recargarPagina();
        System.out.println("[Jenkins] Página recargada.");
    }

    /**
     * Ejecuta «ciclos» veces: esperar intervalo variable (~minutosCentro) y recargar.
     * Intervalo con ±10% alrededor de minutosCentro.
     */
    public void recargarCadaAproximadamenteMinutosDuranteCiclos(double minutosCentro, int ciclos) {
        double factorMin = 0.9;
        double factorMax = 1.1;
        double minutosMin = minutosCentro * factorMin;
        double minutosMax = minutosCentro * factorMax;
        for (int i = 1; i <= ciclos; i++) {
            System.out.println("[Jenkins] Ciclo " + i + " de " + ciclos);
            esperarTiempoVariableMinutosYRecargar(minutosMin, minutosMax);
        }
    }

    /**
     * Recarga la página 1 vez cada minuto (intervalo variable ~60 s ±10%) durante el tiempo total indicado.
     * Ejemplo: 120 minutos = 2 horas → 120 recargas (una por minuto).
     *
     * @param minutosTotales duración total en minutos (ej. 120 para 2 horas)
     */
    public void recargarUnaVezCadaMinutoDuranteMinutos(double minutosTotales) {
        int totalRecargas = (int) Math.round(minutosTotales);
        if (totalRecargas < 1) {
            totalRecargas = 1;
        }
        double segundosPorMinutoMin = 54;  // ~1 min ±10%
        double segundosPorMinutoMax = 66;
        for (int i = 1; i <= totalRecargas; i++) {
            double segundos = ThreadLocalRandom.current().nextDouble(segundosPorMinutoMin, segundosPorMinutoMax);
            long ms = (long) (segundos * 1000);
            System.out.println("[Jenkins] Recarga " + i + "/" + totalRecargas + " — esperando " + String.format("%.1f", segundos) + " s...");
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Espera interrumpida", e);
            }
            recargarPagina();
        }
        System.out.println("[Jenkins] Finalizado: " + totalRecargas + " recargas en ~" + minutosTotales + " minutos.");
    }
}
