package hospital.frontend.componentes;

import javax.swing.*;
import java.awt.*;

public class VistaAdministradorPanel extends JPanel {

    public VistaAdministradorPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Panel Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea mensaje = new JTextArea("Aquí podrás gestionar usuarios, configuraciones y auditorías del sistema.");
        mensaje.setEditable(false);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        mensaje.setLineWrap(true);
        mensaje.setWrapStyleWord(true);

        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(mensaje), BorderLayout.CENTER);
    }
}

