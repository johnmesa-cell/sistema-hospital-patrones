package hospital.frontend.componentes;

import javax.swing.*;
import java.awt.*;

public class VistaMedicoPanel extends JPanel {

    public VistaMedicoPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Panel Médico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea mensaje = new JTextArea("Aquí podrás gestionar tus diagnósticos, tratamientos, citas y notas médicas.");
        mensaje.setEditable(false);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        mensaje.setLineWrap(true);
        mensaje.setWrapStyleWord(true);

        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(mensaje), BorderLayout.CENTER);
    }
}

