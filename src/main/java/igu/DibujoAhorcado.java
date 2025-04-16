package igu;

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
        g2d.setStroke(new BasicStroke(3)); // Línea más gruesa para mejor visibilidad

        // Calcular dimensiones relativas al tamaño del panel
        int width = getWidth();
        int height = getHeight();

        int baseX = width / 10;
        int baseWidth = width / 2;
        int posteX = width / 5;
        int posteHeight = height * 3 / 4;
        int topY = height / 8;
        int topWidth = width / 3;
        int cuerda = height / 8;

        // Dibujar la base del ahorcado (siempre visible)
        g2d.drawLine(baseX, height - 50, baseX + baseWidth, height - 50); // base horizontal
        g2d.drawLine(posteX, height - 50, posteX, topY);   // poste vertical
        g2d.drawLine(posteX, topY, posteX + topWidth, topY);   // parte superior horizontal
        g2d.drawLine(posteX + topWidth, topY, posteX + topWidth, topY + cuerda);  // cuerda

        // Centro del muñeco
        int personaX = posteX + topWidth;
        int cabezaY = topY + cuerda;
        int radioCabeza = height / 12;

        // Dibujar partes según el número de errores
        if (errores >= 1) {
            // Cabeza
            g2d.drawOval(personaX - radioCabeza, cabezaY, radioCabeza * 2, radioCabeza * 2);
        }

        if (errores >= 2) {
            // Cuerpo (longitud proporcional a la altura)
            int cuerpoLargo = height / 3;
            g2d.drawLine(personaX, cabezaY + radioCabeza * 2, personaX, cabezaY + radioCabeza * 2 + cuerpoLargo);
        }

        if (errores >= 3) {
            // Brazo izquierdo
            int brazoLargo = width / 6;
            g2d.drawLine(personaX, cabezaY + radioCabeza * 2 + height / 12,
                    personaX - brazoLargo, cabezaY + radioCabeza * 2 + height / 6);
        }

        if (errores >= 4) {
            // Brazo derecho
            int brazoLargo = width / 6;
            g2d.drawLine(personaX, cabezaY + radioCabeza * 2 + height / 12,
                    personaX + brazoLargo, cabezaY + radioCabeza * 2 + height / 6);
        }

        if (errores >= 5) {
            // Pierna izquierda
            int cuerpoLargo = height / 3;
            int piernaLargo = height / 6;
            g2d.drawLine(personaX, cabezaY + radioCabeza * 2 + cuerpoLargo,
                    personaX - width / 8, cabezaY + radioCabeza * 2 + cuerpoLargo + piernaLargo);
        }

        if (errores >= 6) {
            // Pierna derecha
            int cuerpoLargo = height / 3;
            int piernaLargo = height / 6;
            g2d.drawLine(personaX, cabezaY + radioCabeza * 2 + cuerpoLargo,
                    personaX + width / 8, cabezaY + radioCabeza * 2 + cuerpoLargo + piernaLargo);

            // Cara triste cuando pierde
            int ojosY = cabezaY + radioCabeza;
            g2d.drawLine(personaX - radioCabeza/2, ojosY, personaX - radioCabeza/4, ojosY);
            g2d.drawLine(personaX + radioCabeza/4, ojosY, personaX + radioCabeza/2, ojosY);
            g2d.drawArc(personaX - radioCabeza/2, ojosY + radioCabeza/2, radioCabeza, radioCabeza/2, 0, 180);
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}