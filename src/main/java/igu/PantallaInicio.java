package igu;

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

        //Barra con menu
        JMenuBar barraMenu = new JMenuBar();
        ButtonGroup grupoJugadores = new ButtonGroup();
        JMenu menu = new JMenu("Opciones juego");
        Font fuente = new Font("Arial", Font.PLAIN, 14);

        JMenu subMenuIdioma = new JMenu("Idioma");
        JMenuItem itemEspanol = new JMenuItem("Español");
        JMenuItem itemIngles = new JMenuItem("Inglés");
        JMenuItem itemFrances = new JMenuItem("Francés");
        JMenu subJugadores = new JMenu("Jugadores");
        JRadioButtonMenuItem uno = new JRadioButtonMenuItem("1 Jugador");
        JRadioButtonMenuItem dos = new JRadioButtonMenuItem("2 Jugadores");
        JRadioButtonMenuItem tres = new JRadioButtonMenuItem("3 Jugadores");
        JRadioButtonMenuItem multiPlayer = new JRadioButtonMenuItem("Multijugador");
        uno.setFont(fuente);
        dos.setFont(fuente);
        tres.setFont(fuente);
        grupoJugadores.add(uno);
        grupoJugadores.add(dos);
        grupoJugadores.add(tres);
        grupoJugadores.add(multiPlayer);
        subJugadores.add(uno);
        subJugadores.add(dos);
        subJugadores.add(tres);
        subJugadores.add(multiPlayer);
        multiPlayer.setFont(fuente);
        JMenuItem item3 = new JMenuItem("Estadisticas");
        itemEspanol.setFont(new Font("Arial", Font.PLAIN, 14));
        itemIngles.setFont(new Font("Arial", Font.PLAIN, 14));
        itemFrances.setFont(new Font("Arial", Font.PLAIN, 14));
       subMenuIdioma.setFont(new Font("Arial", Font.PLAIN, 16));
        subJugadores.setFont(new Font("Arial", Font.PLAIN, 16));
        item3.setFont(new Font("Arial", Font.PLAIN, 16));
        subMenuIdioma.add(itemIngles);
        subMenuIdioma.add(itemFrances);
        subMenuIdioma.add(itemEspanol);
        menu.add(subMenuIdioma);
        menu.add(subJugadores);
        menu.add(item3);
        menu.setFont(new Font("Arial", Font.PLAIN, 20));
        JMenu menuAdmin = new JMenu("Opciones admin");
        menuAdmin.setFont(new Font("Arial", Font.PLAIN, 20));
        JMenu subMenuUsuarios = new JMenu(("Gestionar usuarios"));
        JMenuItem eliminarUsuario = new JMenuItem("Eliminar Usuario");
        JMenuItem agregarUsuario = new JMenuItem("Agregar Usuario");
        JMenuItem bloquearUsuario = new JMenuItem("Bloquear Usuario");
        eliminarUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        agregarUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        bloquearUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        subMenuUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
        JMenu subMenuRanking= new JMenu(("Ranking"));
        subMenuRanking.setFont(new Font("Arial", Font.PLAIN, 16));
        JMenuItem eliminarRanking = new JMenuItem("Eliminar");
        eliminarRanking.setFont(new Font("Arial", Font.PLAIN, 14));
        subMenuUsuarios.add(eliminarUsuario);
        subMenuUsuarios.add(agregarUsuario);
        subMenuUsuarios.add(bloquearUsuario);
        subMenuRanking.add(eliminarRanking);
        menuAdmin.add(subMenuUsuarios);
        menuAdmin.add(subMenuRanking);

        barraMenu.add(menu);
        barraMenu.add(menuAdmin);


        setJMenuBar(barraMenu);


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