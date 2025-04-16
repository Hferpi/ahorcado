package logica;

import java.util.HashSet;
import java.util.Set;

public class LogicaJuego {

    private Palabras diccionario;
    private String palabraSeleccionada;
    private Set<Character> letrasAdivinadas;
    private static final String RUTA_DICCIONARIO = "/media/hecferpiq/KODAK/Grado superior DAW/programacion/3trimestre/ahorcado/info/palabras.txt";

    public LogicaJuego() {
        // Inicializar el diccionario con la ruta del archivo
        diccionario = new Palabras(RUTA_DICCIONARIO);
        nuevaPartida();
    }

    public void nuevaPartida() {
        // Seleccionar una palabra aleatoria del diccionario
        palabraSeleccionada = diccionario.obtenerPalabraAleatoria();

        // Inicializar conjunto de letras adivinadas
        letrasAdivinadas = new HashSet<>();
    }

    public boolean probarLetra(char letra) {
        // Convertir a mayúscula para evitar problemas de case
        letra = Character.toUpperCase(letra);

        // Añadir la letra a las intentadas
        letrasAdivinadas.add(letra);

        // Verificar si la letra está en la palabra
        return palabraSeleccionada.contains(String.valueOf(letra));
    }

    public String getPalabraActual() {
        StringBuilder resultado = new StringBuilder();

        for (char letra : palabraSeleccionada.toCharArray()) {
            if (letrasAdivinadas.contains(letra)) {
                resultado.append(letra).append(" ");
            } else {
                resultado.append("_ ");
            }
        }

        return resultado.toString();
    }

    public boolean haGanado() {
        for (char letra : palabraSeleccionada.toCharArray()) {
            if (!letrasAdivinadas.contains(letra)) {
                return false;
            }
        }
        return true;
    }

    public String getPalabraCompleta() {
        return palabraSeleccionada;
    }

    public int getTotalPalabrasDisponibles() {
        return diccionario.getTotalPalabras();
    }
}