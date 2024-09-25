package com.aluracursos.convertidormoneda.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderUtil {
    // Método para leer los datos de un archivo
    public static void leerDatosDeArchivo(String nombreArchivo) {
        // Utilizamos un escáner para leer el archivo línea por línea
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            // Leer e imprimir cada línea del archivo
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            // Manejo de error si el archivo no existe
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }
}
