package igu;
// PantallaJuego.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logica.*;

public class PantallaJuego extends JFrame {
    private String usuario;
    private DibujoAhorcado dibujoAhorcado;
    private LogicaJuego logica;
    private JLabel palabraLabel;
    private JTextField inputLetra;
    private JButton btnEnviar;
    private JLabel mensajeLabel;

    public PantallaJuego(String usuario) {
        this.usuario = usuario;
        // Rest of initialization
        logica = new LogicaJuego();

        // Configuración básica de la ventana
        setTitle("Juego del Ahorcado");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel para el dibujo del ahorcado
        dibujoAhorcado = new DibujoAhorcado();
        dibujoAhorcado.setPreferredSize(new Dimension(200, 200));
        panelPrincipal.add(dibujoAhorcado, BorderLayout.WEST);

        // Panel para la palabra y entrada de letras
        JPanel panelJuego = new JPanel();
        panelJuego.setLayout(new BoxLayout(panelJuego, BoxLayout.Y_AXIS));

        // Mostrar palabra con guiones
        palabraLabel = new JLabel(logica.getPalabraActual());
        palabraLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        palabraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mensaje para el usuario
        mensajeLabel = new JLabel("Adivina la palabra!");
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para entrada de letra
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputLetra = new JTextField(5);
        btnEnviar = new JButton("Enviar");
        inputPanel.add(new JLabel("Letra: "));
        inputPanel.add(inputLetra);
        inputPanel.add(btnEnviar);

        // Añadir componentes al panel de juego
        panelJuego.add(Box.createVerticalGlue());
        panelJuego.add(palabraLabel);
        panelJuego.add(Box.createRigidArea(new Dimension(0, 20)));
        panelJuego.add(mensajeLabel);
        panelJuego.add(Box.createRigidArea(new Dimension(0, 20)));
        panelJuego.add(inputPanel);
        panelJuego.add(Box.createVerticalGlue());

        panelPrincipal.add(panelJuego, BorderLayout.CENTER);

        // Botón para volver al inicio
        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PantallaInicio(usuario).setVisible(true);
            }
        });
        panelPrincipal.add(btnVolver, BorderLayout.SOUTH);

        // Añadir acción al botón enviar
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarLetra();
            }
        });

        // Añadir panel a la ventana
        add(panelPrincipal);
    }

    private void procesarLetra() {
        // Obtener la letra ingresada
        String texto = inputLetra.getText().trim();

        if (texto.length() == 1) {
            char letra = texto.charAt(0);

            // Procesar la letra en la lógica del juego
            boolean acierto = logica.probarLetra(letra);

            // Actualizar palabra mostrada
            palabraLabel.setText(logica.getPalabraActual());

            // Actualizar mensaje
            if (acierto) {
                mensajeLabel.setText("¡Bien hecho! La letra está en la palabra.");
            } else {
                mensajeLabel.setText("¡Oh no! La letra no está en la palabra.");
                // Incrementar errores y actualizar dibujo
                dibujoAhorcado.incrementarErrores();
            }

            // Verificar si ha ganado o perdido
            if (logica.haGanado()) {
                // Add points when user wins
                BaseDatos.actualizarPuntuacion(usuario, 10);
                JOptionPane.showMessageDialog(this, "¡Felicidades! Has ganado. +10 puntos");
                reiniciarJuego();
            } else if (dibujoAhorcado.getErrores() >= 6) {
                JOptionPane.showMessageDialog(this, "¡Has perdido! La palabra era: " + logica.getPalabraCompleta());
                reiniciarJuego();
            }
        } else {
            mensajeLabel.setText("Por favor, introduce una sola letra.");
        }

        // Limpiar campo de texto y darle foco
        inputLetra.setText("");
        inputLetra.requestFocus();
    }

    private void reiniciarJuego() {
        logica.nuevaPartida();
        dibujoAhorcado.resetear();
        palabraLabel.setText(logica.getPalabraActual());
        mensajeLabel.setText("¡Nueva partida! Adivina la palabra.");
    }
}


