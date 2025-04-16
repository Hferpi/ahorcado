package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Palabras {
    private List<String> listaPalabras;
    private static final Random random = new Random();

    public Palabras(String rutaArchivo) {
        listaPalabras = new ArrayList<>();
        cargarPalabras(rutaArchivo);
    }

    private void cargarPalabras(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Añadir solo si la línea no está vacía y convertir a mayúsculas
                if (!linea.trim().isEmpty()) {
                    listaPalabras.add(linea.trim().toUpperCase());
                }
            }
            System.out.println("Diccionario cargado correctamente con " + listaPalabras.size() + " palabras.");
        } catch (IOException e) {
            System.err.println("Error al cargar el diccionario: " + e.getMessage());
            // Añadir algunas palabras por defecto en caso de error
            listaPalabras.add("PROGRAMACION");
            listaPalabras.add("JAVA");
            listaPalabras.add("AHORCADO");
        }
    }

    public String obtenerPalabraAleatoria() {
        if (listaPalabras.isEmpty()) {
            return "AHORCADO"; // Palabra por defecto si no hay palabras en el diccionario
        }

        int indice = random.nextInt(listaPalabras.size());
        return listaPalabras.get(indice);
    }

    public int getTotalPalabras() {
        return listaPalabras.size();
    }
}