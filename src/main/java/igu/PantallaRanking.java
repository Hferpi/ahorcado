package igu;

import logica.BaseDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class PantallaRanking extends JFrame {
    private JTable tablaRanking;
    private DefaultTableModel modeloTabla;

    public PantallaRanking() {
        // Configuración básica de la ventana
        setTitle("Ranking de Jugadores");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(240, 240, 255));

        // Título
        JLabel titulo = new JLabel("Ranking de Jugadores");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Tabla de ranking
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Posición");
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Puntuación");
        modeloTabla.addColumn("Fecha de Registro");

        tablaRanking = new JTable(modeloTabla);
        tablaRanking.setFont(new Font("Arial", Font.PLAIN, 16));
        tablaRanking.setRowHeight(25);
        tablaRanking.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tablaRanking.setSelectionBackground(new Color(184, 207, 229));

        JScrollPane scrollTabla = new JScrollPane(tablaRanking);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        // Botón volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 18));
        btnVolver.setPreferredSize(new Dimension(120, 40));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(240, 240, 255));
        panelBoton.add(btnVolver);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        // Cargar datos del ranking
        cargarRanking();

        // Añadir panel a la ventana
        add(panelPrincipal);
    }

    private void cargarRanking() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Obtener datos del ranking desde la base de datos
        List<Map<String, Object>> ranking = BaseDatos.obtenerRanking();

        int posicion = 1;
        for (Map<String, Object> jugador : ranking) {
            modeloTabla.addRow(new Object[]{
                    posicion++,
                    jugador.get("nombre"),
                    jugador.get("puntuacion"),
                    jugador.get("fecha_registro")
            });
        }
    }
}