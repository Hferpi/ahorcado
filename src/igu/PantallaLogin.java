package igu;

// PantallaLogin.java
import logica.BaseDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JLabel mensajeLabel;

    public PantallaLogin() {
        // Inicializar la base de datos
        BaseDatos.inicializarBD();

        // Configuración básica de la ventana
        setTitle("Ahorcado - Iniciar Sesión");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setBackground(new Color(240, 240, 255));

        // Título
        JLabel titulo = new JLabel("Iniciar Sesión");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo usuario
        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelUsuario.setBackground(new Color(240, 240, 255));
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 24));
        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
        panelUsuario.add(lblUsuario);
        panelUsuario.add(txtUsuario);

        // Campo contraseña
        JPanel panelContrasena = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelContrasena.setBackground(new Color(240, 240, 255));
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 24));
        txtContrasena = new JPasswordField(20);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 20));
        panelContrasena.add(lblContrasena);
        panelContrasena.add(txtContrasena);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(240, 240, 255));

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 20));
        btnEntrar.setPreferredSize(new Dimension(150, 50));

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 20));
        btnRegistrarse.setPreferredSize(new Dimension(150, 50));

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 20));
        btnSalir.setPreferredSize(new Dimension(150, 50));

        panelBotones.add(btnEntrar);
        panelBotones.add(btnRegistrarse);
        panelBotones.add(btnSalir);

        // Mensaje de error/éxito
        mensajeLabel = new JLabel(" ");
        mensajeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        mensajeLabel.setForeground(Color.RED);
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir componentes al panel principal
        panel.add(Box.createVerticalGlue());
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(panelUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(panelContrasena);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(panelBotones);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(mensajeLabel);
        panel.add(Box.createVerticalGlue());

        // Acciones de los botones
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPantallaRegistro();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Acción al presionar Enter en el campo de contraseña
        txtContrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    iniciarSesion();
                }
            }
        });

        // Añadir panel a la ventana
        add(panel);
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeLabel.setText("Por favor, completa todos los campos");
            return;
        }

        if (BaseDatos.verificarLogin(usuario, contrasena)) {
            mensajeLabel.setText("Inicio de sesión exitoso");
            mensajeLabel.setForeground(Color.GREEN);

            // Abrir la pantalla de inicio del juego
            abrirPantallaInicio(usuario);
        } else {
            mensajeLabel.setText("Usuario o contraseña incorrectos");
            mensajeLabel.setForeground(Color.RED);
        }
    }

    private void abrirPantallaRegistro() {
        dispose();
        new PantallaRegistro().setVisible(true);
    }

    private void abrirPantallaInicio(String usuario) {
        dispose();
        new PantallaInicio(usuario).setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Intentar cargar el driver JDBC de SQLite
            Class.forName("org.sqlite.JDBC");

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
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