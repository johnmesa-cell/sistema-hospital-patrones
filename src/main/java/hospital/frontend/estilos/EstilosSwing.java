package hospital.frontend.estilos;

import javax.swing.*;
import java.awt.*;

/**
 * Clase utilitaria para aplicar estilos comunes en componentes Swing.
 */
public class EstilosSwing {

    /**
     * Aplica estilo básico a un botón.
     *
     * @param button JButton a estilizar.
     */
    public static void estilizarBoton(JButton button) {
        button.setBackground(new Color(33, 150, 243)); // Azul primario
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorderPainted(false);
    }

    /**
     * Aplica estilo básico a un JLabel.
     *
     * @param label JLabel a estilizar.
     */
    public static void estilizarEtiqueta(JLabel label) {
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(33, 33, 33)); // Gris oscuro
    }

    /**
     * Aplica estilo básico a JTextField.
     *
     * @param textField JTextField a estilizar.
     */
    public static void estilizarCampoTexto(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(189, 189, 189)));
        textField.setForeground(new Color(33, 33, 33));
    }

    /**
     * Configura estilo para JFrame, como color de fondo.
     *
     * @param frame JFrame a estilizar.
     */
    public static void estilizarFrame(JFrame frame) {
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setFont(new Font("Arial", Font.PLAIN, 14));
    }
}

