package igu;

// PantallaRegistro.java

import logica.BaseDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaRegistro extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtDemuestralo;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmar;
    private JLabel mensajeLabel;

    public PantallaRegistro() {
        // Configuración básica de la ventana
        setTitle("Ahorcado - Registro de Usuario");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setBackground(new Color(240, 240, 255));

        // Título
        JLabel titulo = new JLabel("Registro de Usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Admin
        JPanel panelAdmin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAdmin.setBackground(new Color(240, 240, 255));
        JLabel lblAdmin= new JLabel("¿Eres admin?  ");
        lblAdmin.setFont(new Font("Arial", Font.PLAIN, 24));
        JRadioButton opcion1 = new JRadioButton("Si");
        JRadioButton opcion2 = new JRadioButton("No");
        opcion1.setFont(new Font("Arial", Font.PLAIN, 24));
        opcion2.setFont(new Font("Arial", Font.PLAIN, 24));
        opcion1.setBackground(new Color(240, 240, 255));
        opcion2.setBackground(new Color(240, 240, 255));
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcion1);
        grupo.add(opcion2);
        panelAdmin.add(lblAdmin);
        panelAdmin.add(opcion1);
        panelAdmin.add(opcion2);


        // Campo demuestralo
        JPanel panelDemuestralo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelDemuestralo.setBackground(new Color(240, 240, 255));
        JLabel lblDemuestralo = new JLabel("Demuestralo:");
        lblDemuestralo.setFont(new Font("Arial", Font.PLAIN, 24));
        txtDemuestralo = new JPasswordField(20);
        txtDemuestralo.setFont(new Font("Arial", Font.PLAIN, 20));
        panelDemuestralo.add(lblDemuestralo);
        panelDemuestralo.add(txtDemuestralo);
        panelDemuestralo.setVisible(false);

        opcion1.addActionListener(e -> panelDemuestralo.setVisible(true));
        opcion2.addActionListener(e -> panelDemuestralo.setVisible(false));


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

        // Campo confirmar contraseña
        JPanel panelConfirmar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelConfirmar.setBackground(new Color(240, 240, 255));
        JLabel lblConfirmar = new JLabel("Confirmar Contraseña:");
        lblConfirmar.setFont(new Font("Arial", Font.PLAIN, 24));
        txtConfirmar = new JPasswordField(20);
        txtConfirmar.setFont(new Font("Arial", Font.PLAIN, 20));
        panelConfirmar.add(lblConfirmar);
        panelConfirmar.add(txtConfirmar);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(240, 240, 255));

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 20));
        btnRegistrar.setPreferredSize(new Dimension(150, 50));

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
        btnVolver.setPreferredSize(new Dimension(150, 50));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);

        // Mensaje de error/éxito
        mensajeLabel = new JLabel(" ");
        mensajeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        mensajeLabel.setForeground(Color.RED);
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir componentes al panel principal
        panel.add(Box.createVerticalGlue());
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(panelAdmin);
        panel.add(panelDemuestralo);
        panel.add(panelUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(panelContrasena);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(panelConfirmar);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(panelBotones);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(mensajeLabel);
        panel.add(Box.createVerticalGlue());

        // Acciones de los botones
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverALogin();
            }
        });

        // Añadir panel a la ventana
        add(panel);
    }

    private void registrarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String confirmar = new String(txtConfirmar.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
            mensajeLabel.setText("Por favor, completa todos los campos");
            return;
        }

        if (!contrasena.equals(confirmar)) {
            mensajeLabel.setText("Las contraseñas no coinciden");
            return;
        }

        if (contrasena.length() < 4) {
            mensajeLabel.setText("La contraseña debe tener al menos 4 caracteres");
            return;
        }

        // Registrar usuario en la base de datos

        if (BaseDatos.registrarUsuario(usuario, contrasena)) {
            mensajeLabel.setText("Usuario registrado correctamente");
            mensajeLabel.setForeground(Color.GREEN);

            // Después de unos segundos, volver a la pantalla de login
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    volverALogin();
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            mensajeLabel.setText("El nombre de usuario ya existe");
            mensajeLabel.setForeground(Color.RED);
        }
    }

    private void volverALogin() {
        dispose();
        new PantallaLogin().setVisible(true);
    }
}