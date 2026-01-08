package framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase utilitaria para cargar datos desde archivos CSV
 */
public class CsvDataLoader {
    
    /**
     * Lee un archivo CSV y retorna una lista de mapas con los datos
     * La primera fila debe contener los encabezados
     * 
     * @param rutaArchivo Ruta del archivo CSV
     * @return Lista de mapas, donde cada mapa representa una fila con sus columnas
     */
    public static List<Map<String, String>> cargarDatosDesdeCSV(String rutaArchivo) {
        List<Map<String, String>> datos = new ArrayList<>();
        List<String> encabezados = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                
                // Ignorar líneas vacías
                if (linea.isEmpty()) {
                    continue;
                }
                
                // Leer encabezados de la primera línea
                if (primeraLinea) {
                    encabezados = parsearLineaCSV(linea);
                    primeraLinea = false;
                    continue;
                }
                
                // Parsear datos de las siguientes líneas
                List<String> valores = parsearLineaCSV(linea);
                
                // Crear mapa con los datos
                Map<String, String> fila = new HashMap<>();
                for (int i = 0; i < encabezados.size() && i < valores.size(); i++) {
                    fila.put(encabezados.get(i).trim(), valores.get(i).trim());
                }
                
                datos.add(fila);
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo CSV: " + rutaArchivo + " - " + e.getMessage(), e);
        }
        
        return datos;
    }
    
    /**
     * Parsea una línea CSV, manejando valores entre comillas
     */
    private static List<String> parsearLineaCSV(String linea) {
        List<String> valores = new ArrayList<>();
        StringBuilder valorActual = new StringBuilder();
        boolean entreComillas = false;
        
        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            
            if (c == '"') {
                entreComillas = !entreComillas;
            } else if (c == ',' && !entreComillas) {
                valores.add(valorActual.toString());
                valorActual = new StringBuilder();
            } else {
                valorActual.append(c);
            }
        }
        
        // Agregar el último valor
        valores.add(valorActual.toString());
        
        return valores;
    }
    
    /**
     * Obtiene la primera fila de datos del CSV
     */
    public static Map<String, String> obtenerPrimeraFila(String rutaArchivo) {
        List<Map<String, String>> datos = cargarDatosDesdeCSV(rutaArchivo);
        if (datos.isEmpty()) {
            throw new RuntimeException("El archivo CSV está vacío o no tiene datos: " + rutaArchivo);
        }
        return datos.get(0);
    }
}

