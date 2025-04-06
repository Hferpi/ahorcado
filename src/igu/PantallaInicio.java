package igu;

import igu.PantallaJuego;
import igu.PantallaLogin;
import logica.BaseDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicio extends JFrame {
    private String usuario;
    private int puntuacion;

    public PantallaInicio(String usuario) {
        this.usuario = usuario;
        this.puntuacion = BaseDatos.obtenerPuntuacion(usuario);

        // Configuración básica de la ventana
        setTitle("Juego del Ahorcado");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal con layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setBackground(new Color(240, 240, 255));

        // Información del usuario
        JLabel infoUsuario = new JLabel("Usuario: " + usuario + " | Puntuación: " + puntuacion);
        infoUsuario.setFont(new Font("Arial", Font.BOLD, 22));
        infoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título del juego
        JLabel titulo = new JLabel("¿Serás el próximo ahorcado?");
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botones
        JButton btnJugar = new JButton("Jugar");
        btnJugar.setFont(new Font("Arial", Font.BOLD, 24));
        btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnJugar.setMaximumSize(new Dimension(300, 80));

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 24));
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.setMaximumSize(new Dimension(300, 80));

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 24));
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setMaximumSize(new Dimension(300, 80));

        // Añadir componentes al panel
        panel.add(Box.createVerticalGlue());
        panel.add(infoUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(btnJugar);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(btnCerrarSesion);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(btnSalir);
        panel.add(Box.createVerticalGlue());

        // Acciones de los botones
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new PantallaJuego(usuario).setVisible(true);
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PantallaLogin().setVisible(true);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Añadir panel a la ventana
        add(panel);
    }
}