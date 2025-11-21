package hospital.frontend.componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import hospital.backend.servicios.DiagnosticoDAO;
import hospital.backend.servicios.NotaMedicaDAO;
import hospital.backend.servicios.TratamientoDAO;
import hospital.backend.usuarios.Usuario;
import hospital.backend.servicios.UsuarioService;

// Panel de administrador centralizado para gestión de historiales y usuarios
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

    // Se recibe el UsuarioService desde VentanaPrincipal para no crear uno nuevo
    public VistaAdministradorPanel(VentanaPrincipal ventanaPrincipal, Usuario usuarioActual, UsuarioService usuarioService) {
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

        JButton btnGenerarReporte = new JButton("Generar Reporte");
        btnGenerarReporte.addActionListener(e -> generarReporte());
        panelBotones.add(btnGenerarReporte);

        add(panelBotones, BorderLayout.SOUTH);
    }

    // Cargar todos los usuarios usando el método real disponible en UsuarioService
    private void cargarUsuarios() {
        modeloUsuarios.setRowCount(0);
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        for (Usuario usuario : usuarios) {
            modeloUsuarios.addRow(new Object[]{
                    usuario.getIdUsuario(), // Nombre y rol según getters
                    usuario.getNombre(),
                    usuario.getTipo() // Si tu clase Usuario no tiene getRol(), añade el método
            });
        }
    }

// Asegúrate de pasar los DAOs al constructor y guardar las referencias

    private void verHistorialUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para ver el historial.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String idUsuario = (String) modeloUsuarios.getValueAt(filaSeleccionada, 0);

        // Abre el historial médico en una ventana
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
        JOptionPane.showMessageDialog(this, "Función para gestión de usuarios (pendiente implementar)");
    }

    private void generarReporte() {
        JOptionPane.showMessageDialog(this, "Función para generación de reportes (pendiente implementar)");
    }

    // Botón cerrar sesión funcional
    private void cerrarSesion() {
        ventanaPrincipal.mostrarLogin();
    }
}

