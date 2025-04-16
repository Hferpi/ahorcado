package igu;

import logica.BaseDatos;
import logica.LogicaJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class PantallaJuego extends JFrame {
    private String usuario;
    private DibujoAhorcado dibujoAhorcado;
    private LogicaJuego logica;
    private JLabel palabraLabel;
    private JTextField inputLetra;
    private JButton btnEnviar;
    private JLabel mensajeLabel;
    private JLabel letrasUsadasLabel;
    private Set<Character> letrasUsadas;
    private Set<Character> letrasUsadasNoEstan;

    public PantallaJuego(String usuario) {
        this.usuario = usuario;
        this.letrasUsadas = new HashSet<>();
        this.letrasUsadasNoEstan = new HashSet<>();

        // Inicializar la lógica del juego
        logica = new LogicaJuego();

        // Configuración básica de la ventana
        setTitle("Juego del Ahorcado");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(20, 20));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(240, 240, 255));

        // Panel para el dibujo del ahorcado (ahora más grande)
        dibujoAhorcado = new DibujoAhorcado();
        dibujoAhorcado.setPreferredSize(new Dimension(400, 400));
        JPanel panelDibujo = new JPanel(new BorderLayout());
        panelDibujo.add(dibujoAhorcado, BorderLayout.CENTER);
        panelDibujo.setBackground(new Color(240, 240, 255));
        panelPrincipal.add(panelDibujo, BorderLayout.WEST);

        // Panel para la palabra y entrada de letras
        JPanel panelJuego = new JPanel();
        panelJuego.setLayout(new BoxLayout(panelJuego, BoxLayout.Y_AXIS));
        panelJuego.setBackground(new Color(240, 240, 255));

        // Información del usuario
        JLabel infoUsuario = new JLabel("Usuario: " + usuario);
        infoUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        infoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mostrar palabra con guiones
        palabraLabel = new JLabel(logica.getPalabraActual());
        palabraLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        palabraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mensaje para el usuario
        mensajeLabel = new JLabel("¡Adivina la palabra!");
        mensajeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Letras usadas
        letrasUsadasLabel = new JLabel("Letras usadas: ");
        letrasUsadasLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        letrasUsadasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para entrada de letra
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.setBackground(new Color(240, 240, 255));

        JLabel lblLetra = new JLabel("Letra: ");
        lblLetra.setFont(new Font("Arial", Font.PLAIN, 18));

        inputLetra = new JTextField(3);
        inputLetra.setFont(new Font("Arial", Font.PLAIN, 18));
        inputLetra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    procesarLetra();
                }
            }
        });

        btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 18));
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarLetra();
            }
        });

        inputPanel.add(lblLetra);
        inputPanel.add(inputLetra);
        inputPanel.add(btnEnviar);

        // Añadir componentes al panel de juego
        panelJuego.add(Box.createVerticalGlue());
        panelJuego.add(infoUsuario);
        panelJuego.add(Box.createRigidArea(new Dimension(0, 30)));
        panelJuego.add(palabraLabel);
        panelJuego.add(Box.createRigidArea(new Dimension(0, 20)));
        panelJuego.add(mensajeLabel);
        panelJuego.add(Box.createRigidArea(new Dimension(0, 20)));
        panelJuego.add(letrasUsadasLabel);
        panelJuego.add(Box.createRigidArea(new Dimension(0, 20)));
        panelJuego.add(inputPanel);
        panelJuego.add(Box.createVerticalGlue());

        panelPrincipal.add(panelJuego, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(240, 240, 255));

        JButton btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.setFont(new Font("Arial", Font.BOLD, 18));
        btnNuevaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });

        JButton btnVolver = new JButton("Volver al Inicio");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 18));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PantallaInicio(usuario).setVisible(true);
            }
        });

        panelBotones.add(btnNuevaPartida);
        panelBotones.add(btnVolver);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Añadir panel a la ventana
        add(panelPrincipal);

        // Dar foco al campo de texto al iniciar
        inputLetra.requestFocus();
    }

    private void procesarLetra() {
        // Obtener la letra ingresada
        String texto = inputLetra.getText().trim().toUpperCase();

        if (texto.length() == 1) {
            char letra = texto.charAt(0);

            // Verificar si la letra ya fue usada
            if (letrasUsadas.contains(letra)) {
                mensajeLabel.setText("¡Ya has usado esta letra antes!");
                inputLetra.setText("");
                inputLetra.requestFocus();
                return;
            }

            // Añadir a letras usadas
            letrasUsadas.add(letra);
            actualizarLetrasUsadas();



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
                letrasUsadasNoEstan.add(letra);
                actualizarLetrasUsadas();
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

    private void actualizarLetrasUsadas() {
        StringBuilder sb = new StringBuilder("Letras usadas: ");
        for (char letra: letrasUsadasNoEstan){
            sb.append(letra).append(" ");
        }
        letrasUsadasLabel.setText(sb.toString());
    }

    private void reiniciarJuego() {
        logica.nuevaPartida();
        dibujoAhorcado.resetear();
        letrasUsadas.clear();
        actualizarLetrasUsadas();
        palabraLabel.setText(logica.getPalabraActual());
        mensajeLabel.setText("¡Nueva partida! Adivina la palabra.");
        inputLetra.requestFocus();
    }
}