package igu;

// PantallaLogin.java

import logica.BaseDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        JButton btnOlvidarContraseña = new JButton("Olvidar contraseña");
        btnOlvidarContraseña.setFont(new Font("Arial", Font.BOLD, 20));
        btnOlvidarContraseña.setPreferredSize(new Dimension(240, 50));

        JButton btnRanking = new JButton("Ranking");
        btnRanking.setFont(new Font("Arial", Font.BOLD, 20));
        btnRanking.setPreferredSize(new Dimension(150, 50));

        panelBotones.add(btnEntrar);
        panelBotones.add(btnRegistrarse);
        panelBotones.add(btnOlvidarContraseña);
        panelBotones.add(btnRanking);
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
        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRanking();
            }
        });

        btnOlvidarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoRecuperarContrasena();
            }
        });
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

    private void mostrarDialogoRecuperarContrasena() {
        // Crear un JDialog personalizado
        JDialog dialogo = new JDialog(this, "Recuperar Contraseña", true);
        dialogo.setSize(400, 200);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());

        // Panel con contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 255));

        // Componentes
        JLabel instruccion = new JLabel("Introduce tu nombre de usuario:");
        instruccion.setFont(new Font("Arial", Font.PLAIN, 16));
        instruccion.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtNombreUsuario = new JTextField(20);
        txtNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNombreUsuario.setMaximumSize(new Dimension(300, 30));
        txtNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblResultado = new JLabel(" ");
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 16));
        lblResultado.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 16));
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = txtNombreUsuario.getText().trim();

                if (nombreUsuario.isEmpty()) {
                    lblResultado.setText("Por favor, introduce un nombre de usuario");
                    lblResultado.setForeground(Color.RED);
                    return;
                }

                if (!BaseDatos.existeUsuario(nombreUsuario)) {
                    lblResultado.setText("El usuario no existe");
                    lblResultado.setForeground(Color.RED);
                    return;
                }

                String contrasena = BaseDatos.obtenerContrasena(nombreUsuario);
                if (contrasena != null) {
                    lblResultado.setText("Tu contraseña es: " + contrasena);
                    lblResultado.setForeground(new Color(0, 100, 0));
                } else {
                    lblResultado.setText("Error al recuperar la contraseña");
                    lblResultado.setForeground(Color.RED);
                }
            }
        });

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });

        // Añadir componentes al panel
        panel.add(instruccion);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtNombreUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnBuscar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblResultado);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnCerrar);

        dialogo.add(panel, BorderLayout.CENTER);
        dialogo.setVisible(true);
    }

    private void mostrarRanking() {
        new PantallaRanking().setVisible(true);
    }



}