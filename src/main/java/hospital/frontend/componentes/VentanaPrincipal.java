package hospital.frontend.componentes;

import hospital.backend.servicios.*;
import hospital.backend.usuarios.Usuario;
import hospital.frontend.servicios.AutenticacionService;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private AutenticacionService autenticacionService;
    private UsuarioService usuarioService;
    private Usuario usuarioActual; // << Para guardar el usuario logueado
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

    /**
     * Permite guardar el usuario que acaba de iniciar sesión, para acceso en los paneles.
     */
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

    /**
     * Muestra el panel de login.
     */
    public void mostrarLogin() {
        if (panelActual != null) remove(panelActual);
        panelActual = new LoginPanel(autenticacionService, this);
        add(panelActual);
        revalidate();
        repaint();
    }

    /**
     * Muestra el panel de registro de usuario.
     */
    public void mostrarRegistro() {
        if (panelActual != null) remove(panelActual);
        panelActual = new RegistroUsuarioPanel(usuarioService, this);
        add(panelActual);
        revalidate();
        repaint();
    }

    /**
     * Cambia la vista principal según el rol que acaba de iniciar sesión.
     * El usuarioActual debe estar previamente establecido por LoginPanel.
     */
    public void cambiarVistaPorRol(String rol) {
        if (panelActual != null) remove(panelActual);

        switch (rol.toLowerCase()) {
            case "medico":
                DiagnosticoDAO diagDAO = new DiagnosticoDAOImpl();
                TratamientoDAO tratDAO = new TratamientoDAOImpl();
                CitaDAO citaDAO = new CitaDAOImpl(); // Si existe tu CitaDAO
                panelActual = new VistaMedicoPanel(this, usuarioActual, diagDAO, tratDAO, citaDAO);
                break;

            case "paciente":
                DiagnosticoDAO dDao = new DiagnosticoDAOImpl();
                TratamientoDAO tDao = new TratamientoDAOImpl();
                NotaMedicaDAO nDao = new NotaMedicaDAOImpl();
                panelActual = new VistaPacientePanel(usuarioActual, dDao, tDao, nDao);
                break;

            case "administrador":
                panelActual = new VistaAdministradorPanel(); // Si tienes este panel
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

