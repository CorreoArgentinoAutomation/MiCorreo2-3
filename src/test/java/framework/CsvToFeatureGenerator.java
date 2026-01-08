package framework;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Utilidad para generar/actualizar un feature file con Scenario Outline desde un CSV
 * Genera automáticamente los Examples basándose en el número de filas del CSV
 */
public class CsvToFeatureGenerator {
    
    /**
     * Actualiza el feature file generando automáticamente los Examples basándose en las filas del CSV
     * 
     * @param rutaCSV Ruta del archivo CSV con los datos
     * @param rutaFeature Ruta del feature file a actualizar
     */
    public static void actualizarExamplesDesdeCSV(String rutaCSV, String rutaFeature) {
        // Leer el CSV para contar las filas
        List<Map<String, String>> datos = CsvDataLoader.cargarDatosDesdeCSV(rutaCSV);
        
        if (datos.isEmpty()) {
            throw new RuntimeException("El CSV está vacío, no se puede generar los Examples");
        }
        
        int numeroFilas = datos.size();
        System.out.println("✓ Se encontraron " + numeroFilas + " filas en el CSV");
        
        // Leer el feature file existente
        StringBuilder featureContent = new StringBuilder();
        boolean saltarHastaFinExamples = false;
        boolean examplesAgregados = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaFeature))) {
            String linea;
            
            while ((linea = reader.readLine()) != null) {
                String lineaTrim = linea.trim();
                
                // Detectar el inicio de la sección Examples
                if (lineaTrim.equals("Examples:") || lineaTrim.startsWith("Examples:")) {
                    if (!examplesAgregados) {
                        // Primera vez que encontramos Examples - agregar la nueva sección
                        saltarHastaFinExamples = true;
                        featureContent.append(linea).append("\n");
                        // Agregar el encabezado de la tabla
                        featureContent.append("      | indiceFila |\n");
                        // Generar los índices automáticamente
                        for (int i = 0; i < numeroFilas; i++) {
                            featureContent.append("      | ").append(i).append("          |\n");
                        }
                        examplesAgregados = true;
                    } else {
                        // Si ya agregamos los Examples y encontramos otra sección Examples duplicada, 
                        // activamos el flag para saltar todo hasta el final
                        saltarHastaFinExamples = true;
                    }
                    continue;
                }
                
                // Si estamos saltando (dentro de la sección Examples antigua o duplicada)
                if (saltarHastaFinExamples) {
                    // Si encontramos una línea vacía, terminamos de saltar
                    if (lineaTrim.isEmpty()) {
                        saltarHastaFinExamples = false;
                        // Solo agregar la línea vacía si ya agregamos nuestros Examples
                        if (examplesAgregados) {
                            featureContent.append(linea).append("\n");
                        }
                    }
                    // Si encontramos una línea que no es parte de Examples (no empieza con |, no está vacía, 
                    // y no es otra sección Examples), terminamos de saltar
                    else if (!lineaTrim.startsWith("|") && 
                             !lineaTrim.equals("Examples:") && 
                             !lineaTrim.startsWith("Examples:")) {
                        saltarHastaFinExamples = false;
                        // Agregar esta línea (es el siguiente contenido después de Examples)
                        if (examplesAgregados) {
                            featureContent.append(linea).append("\n");
                        }
                    }
                    // Si es una línea de la tabla Examples (empieza con |), la saltamos completamente
                    // No hacemos nada, simplemente continuamos sin agregarla
                    continue;
                }
                
                // Agregar la línea normalmente (fuera de la sección Examples)
                featureContent.append(linea).append("\n");
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el feature file: " + e.getMessage(), e);
        }
        
        // Escribir el feature file actualizado
        try (FileWriter writer = new FileWriter(rutaFeature)) {
            writer.write(featureContent.toString());
            System.out.println("✓ Feature file actualizado exitosamente: " + rutaFeature);
            System.out.println("✓ Se generaron " + numeroFilas + " Examples (índices 0-" + (numeroFilas - 1) + ")");
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el feature file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Genera un feature file completo con Scenario Outline basado en los datos del CSV
     */
    public static void generarFeatureCompletoDesdeCSV(String rutaCSV, String rutaFeatureOutput) {
        List<Map<String, String>> datos = CsvDataLoader.cargarDatosDesdeCSV(rutaCSV);
        
        if (datos.isEmpty()) {
            throw new RuntimeException("El CSV está vacío, no se puede generar el feature");
        }
        
        int numeroFilas = datos.size();
        
        StringBuilder featureContent = new StringBuilder();
        featureContent.append("@nuevoEnvioEDNormal\n");
        featureContent.append("Feature: Nuevo envio individual (con datos desde CSV)\n\n");
        featureContent.append("  # Este Scenario Outline se ejecuta una vez por cada fila en el archivo CSV\n");
        featureContent.append("  # El parámetro <indiceFila> indica qué fila del CSV usar (basado en 0)\n");
        featureContent.append("  # El hook Before carga automáticamente la fila correspondiente del CSV\n");
        featureContent.append("  # Los Examples se generan automáticamente desde el CSV\n\n");
        featureContent.append("  Scenario Outline: Intento de login con diferentes combinaciones usando datos desde CSV\n");
        featureContent.append("    Given que el usuario carga los datos de la fila <indiceFila> desde CSV y está en la página de login\n");
        featureContent.append("    And el usuario ingresa el correo desde CSV\n");
        featureContent.append("    And el usuario ingresa la contraseña desde CSV\n");
        featureContent.append("    When el usuario hace clic en el botón Ingresar desde CSV\n");
        featureContent.append("    When ingresa en nuevo envío individual desde CSV\n");
        featureContent.append("    And editar el origen del envío individual desde CSV\n");
        featureContent.append("    And llena los campos de paquete desde CSV\n");
        featureContent.append("    And selecciona el tipo de entrega desde CSV y completa el formulario de destino\n");
        featureContent.append("    And selecciona el tipo de producto desde CSV y procede a pagar\n");
        featureContent.append("    And se muestra la grilla de checkout desde CSV\n");
        featureContent.append("    Then realiza el pago con el medio de pago desde CSV\n");
        featureContent.append("    And se confirma que el pago se ha realizado con exito desde CSV\n\n");
        featureContent.append("    Examples:\n");
        featureContent.append("      | indiceFila |\n");
        
        // Generar los índices automáticamente
        for (int i = 0; i < numeroFilas; i++) {
            featureContent.append("      | ").append(i).append("          |\n");
        }
        
        // Escribir el archivo
        try (FileWriter writer = new FileWriter(rutaFeatureOutput)) {
            writer.write(featureContent.toString());
            System.out.println("✓ Feature file generado exitosamente: " + rutaFeatureOutput);
            System.out.println("✓ Se generaron " + numeroFilas + " Examples (índices 0-" + (numeroFilas - 1) + ")");
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el feature file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Método main para ejecutar el generador desde la línea de comandos
     * Uso: java CsvToFeatureGenerator [rutaCSV] [rutaFeature]
     */
    public static void main(String[] args) {
        String rutaCSV = "src/test/resources/datos_nuevo_envio_ED.csv";
        String rutaFeature = "src/main/resources/features/EcosistemaDigital/nuevoEnvioED_normal.feature";
        
        // Permitir pasar rutas como argumentos
        if (args.length >= 1) {
            rutaCSV = args[0];
        }
        if (args.length >= 2) {
            rutaFeature = args[1];
        }
        
        System.out.println("========================================");
        System.out.println("Generador de Examples desde CSV");
        System.out.println("========================================");
        System.out.println("CSV: " + rutaCSV);
        System.out.println("Feature: " + rutaFeature);
        System.out.println("========================================\n");
        
        try {
            actualizarExamplesDesdeCSV(rutaCSV, rutaFeature);
            System.out.println("\n✓ Proceso completado exitosamente");
        } catch (Exception e) {
            System.err.println("\n✗ Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}

