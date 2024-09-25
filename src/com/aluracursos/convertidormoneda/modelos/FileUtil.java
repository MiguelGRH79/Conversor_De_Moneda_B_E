package com.aluracursos.convertidormoneda.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {
    // Método para guardar los datos en un archivo de texto
    public static void guardarDatosEnArchivo(String nombreArchivo, List<String> datos) {
        // Utilizamos try-with-resources para asegurar que el archivo se cierre automáticamente
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Escribir cada línea en el archivo
            for (String linea : datos) {
                writer.write(linea + System.lineSeparator());
            }
            System.out.println("Datos guardados exitosamente en " + nombreArchivo);
        } catch (IOException e) {
            // Manejo de errores al intentar guardar el archivo
            System.out.println("Error al guardar los datos en el archivo: " + e.getMessage());
        }
    }
}
