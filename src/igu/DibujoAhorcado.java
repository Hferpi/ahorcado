package igu;

// DibujoAhorcado.java
import javax.swing.*;
import java.awt.*;

public class DibujoAhorcado extends JPanel {

    private int errores;

    public DibujoAhorcado() {
        errores = 0;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Mejorar la calidad del dibujo
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2));

        // Dibujar la base del ahorcado (siempre visible)
        g2d.drawLine(20, 180, 100, 180); // base horizontal
        g2d.drawLine(60, 180, 60, 20);   // poste vertical
        g2d.drawLine(60, 20, 120, 20);   // parte superior horizontal
        g2d.drawLine(120, 20, 120, 40);  // cuerda

        // Dibujar partes según el número de errores
        if (errores >= 1) {
            // Cabeza
            g2d.drawOval(105, 40, 30, 30);
        }

        if (errores >= 2) {
            // Cuerpo
            g2d.drawLine(120, 70, 120, 120);
        }

        if (errores >= 3) {
            // Brazo izquierdo
            g2d.drawLine(120, 80, 100, 100);
        }

        if (errores >= 4) {
            // Brazo derecho
            g2d.drawLine(120, 80, 140, 100);
        }

        if (errores >= 5) {
            // Pierna izquierda
            g2d.drawLine(120, 120, 100, 150);
        }

        if (errores >= 6) {
            // Pierna derecha
            g2d.drawLine(120, 120, 140, 150);
        }
    }

    public void incrementarErrores() {
        errores++;
        repaint();
    }

    public void resetear() {
        errores = 0;
        repaint();
    }

    public int getErrores() {
        return errores;
    }
}