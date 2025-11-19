package hospital.frontend.componentes;

import javax.swing.*;
import java.awt.*;

public class VistaPacientePanel extends JPanel {

    public VistaPacientePanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Panel Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea mensaje = new JTextArea("Aquí podrás ver tu historial médico, citas, tratamientos y notas.");
        mensaje.setEditable(false);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        mensaje.setLineWrap(true);
        mensaje.setWrapStyleWord(true);

        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(mensaje), BorderLayout.CENTER);
    }
}

