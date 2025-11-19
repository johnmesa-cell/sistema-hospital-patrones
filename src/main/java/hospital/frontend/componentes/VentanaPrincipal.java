package hospital.frontend.componentes;

import hospital.frontend.servicios.AutenticacionService;
import hospital.backend.servicios.UsuarioService;
import hospital.backend.servicios.UsuarioDAO;
import hospital.backend.servicios.UsuarioDAOImpl;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private AutenticacionService autenticacionService;
    private UsuarioService usuarioService;
    private JPanel panelActual;

    public VentanaPrincipal() {
        setTitle("Sistema Hospitalario - Login");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialización servicios y DAO
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioService = new UsuarioService(usuarioDAO);
        autenticacionService = new AutenticacionService(usuarioDAO);

        mostrarLogin();
    }

    public void mostrarLogin() {
        if (panelActual != null) remove(panelActual);
        panelActual = new LoginPanel(autenticacionService, this);
        add(panelActual);
        revalidate();
        repaint();
    }

    public void mostrarRegistro() {
        if (panelActual != null) remove(panelActual);
        panelActual = new RegistroUsuarioPanel(usuarioService, this);
        add(panelActual);
        revalidate();
        repaint();
    }

    public void cambiarVistaPorRol(String rol) {
        if (panelActual != null) remove(panelActual);
        switch (rol.toLowerCase()) {
            case "medico":
                panelActual = new VistaMedicoPanel();
                break;
            case "paciente":
                panelActual = new VistaPacientePanel();
                break;
            case "administrador":
                panelActual = new VistaAdministradorPanel();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Rol no reconocido: " + rol);
                mostrarLogin();
                return;
        }
        add(panelActual);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}

