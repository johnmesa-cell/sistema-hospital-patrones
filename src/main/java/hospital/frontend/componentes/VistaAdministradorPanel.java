package hospital.frontend.componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import hospital.backend.servicios.DiagnosticoDAO;
import hospital.backend.servicios.NotaMedicaDAO;
import hospital.backend.servicios.TratamientoDAO;
import hospital.backend.usuarios.Usuario;
import hospital.backend.servicios.UsuarioService;

public class VistaAdministradorPanel extends JPanel {

    private VentanaPrincipal ventanaPrincipal;
    private Usuario usuarioActual;
    private UsuarioService usuarioService;
    private JButton btnCerrarSesion;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloUsuarios;
    private DiagnosticoDAO diagnosticoDAO;
    private TratamientoDAO tratamientoDAO;
    private NotaMedicaDAO notaMedicaDAO;

    public VistaAdministradorPanel(VentanaPrincipal ventanaPrincipal, Usuario usuarioActual, UsuarioService usuarioService,
                                   DiagnosticoDAO diagnosticoDAO, TratamientoDAO tratamientoDAO, NotaMedicaDAO notaMedicaDAO) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.usuarioActual = usuarioActual;
        this.usuarioService = usuarioService;
        this.diagnosticoDAO = diagnosticoDAO;
        this.tratamientoDAO = tratamientoDAO;
        this.notaMedicaDAO = notaMedicaDAO;

        inicializarComponentes();
        cargarUsuarios();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Panel superior con título y botón cerrar sesión
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Panel de Administrador - Usuario: " + usuarioActual.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla para mostrar los usuarios del sistema
        modeloUsuarios = new DefaultTableModel(new String[]{"ID", "Nombre", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaUsuarios = new JTable(modeloUsuarios);
        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);
        add(scrollTabla, BorderLayout.CENTER);

        // Panel de botones para acciones administrativas
        JPanel panelBotones = new JPanel();

        JButton btnVerHistorial = new JButton("Ver Historial");
        btnVerHistorial.addActionListener(e -> verHistorialUsuario());
        panelBotones.add(btnVerHistorial);

        JButton btnGestionUsuarios = new JButton("Gestionar Usuarios");
        btnGestionUsuarios.addActionListener(e -> gestionarUsuarios());
        panelBotones.add(btnGestionUsuarios);

        // Nuevo: Botón que llama a un dialog para generar el reporte
        JButton btnGenerarReporte = new JButton("Generar Reporte");
        btnGenerarReporte.addActionListener(e -> abrirVentanaGenerarReporte());
        panelBotones.add(btnGenerarReporte);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarUsuarios() {
        modeloUsuarios.setRowCount(0);
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        for (Usuario usuario : usuarios) {
            modeloUsuarios.addRow(new Object[]{
                    usuario.getId_Usuario(),
                    usuario.getNombre(),
                    usuario.getTipo()
            });
        }
    }

    private void verHistorialUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para ver el historial.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int idUsuario = (int) modeloUsuarios.getValueAt(filaSeleccionada, 0);
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        HistorialUsuarioDialog dialog = new HistorialUsuarioDialog(
                parentFrame,
                idUsuario,
                diagnosticoDAO,
                tratamientoDAO,
                notaMedicaDAO
        );
        dialog.setVisible(true);
    }

    private void gestionarUsuarios() {
        GestionUsuariosDialog dialog = new GestionUsuariosDialog((JFrame) SwingUtilities.getWindowAncestor(this), usuarioService);
        dialog.setVisible(true);
    }

    // Aquí simplemente invoca la ventana/interface encargada del reporte
    private void abrirVentanaGenerarReporte() {
        GenerarReporteDialog reporteDialog = new GenerarReporteDialog((JFrame) SwingUtilities.getWindowAncestor(this), usuarioService);
        reporteDialog.setVisible(true);
    }

    private void cerrarSesion() {
        ventanaPrincipal.mostrarLogin();
    }
}



