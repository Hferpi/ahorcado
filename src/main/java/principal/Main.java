package principal;

import igu.PantallaLogin;
import logica.*;
import igu.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Intentar cargar el driver JDBC de SQLite
            Class.forName("org.sqlite.JDBC");

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Iniciar con la pantalla de login en lugar de PantallaInicio
                    // ya que el usuario debe autenticarse primero
                    new PantallaLogin().setVisible(true);
                }
            });
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: No se pudo cargar el driver de SQLite.\n" +
                            "Asegúrate de incluir sqlite-jdbc en tu proyecto.",
                    "Error de Configuración", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}