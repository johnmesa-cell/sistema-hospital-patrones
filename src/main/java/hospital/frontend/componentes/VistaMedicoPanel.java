package hospital.frontend.componentes;

import hospital.backend.citas.Cita;
import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.historialclinico.Tratamiento;
import hospital.backend.servicios.CitaDAO;
import hospital.backend.servicios.DiagnosticoDAO;
import hospital.backend.servicios.TratamientoDAO;
import hospital.backend.usuarios.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VistaMedicoPanel extends JPanel {

    private Usuario medicoActual;
    private DiagnosticoDAO diagnosticoDAO;
    private TratamientoDAO tratamientoDAO;
    private CitaDAO citaDAO;

    private VentanaPrincipal ventanaPrincipal;        // NUEVO: para poder volver al login

    private JTextField txtPacienteId;
    private JButton btnBuscar;

    private DefaultTableModel modeloDiagnosticos;
    private JTable tablaDiagnosticos;
    private JButton btnNuevoDiagnostico;

    private DefaultTableModel modeloTratamientos;
    private JTable tablaTratamientos;
    private JButton btnNuevoTratamiento;

    private DefaultTableModel modeloCitas;
    private JTable tablaCitas;
    private JButton btnNuevaCita;

    private JButton btnCerrarSesion;                  // NUEVO: botón de logout

    // Constructor sin parámetros (compatibilidad)
    public VistaMedicoPanel() {
        this(null, null, null, null, null);
    }

    // Constructor recomendado: recibe ventana principal, médico y DAOs
    public VistaMedicoPanel(VentanaPrincipal ventanaPrincipal,
                            Usuario medicoActual,
                            DiagnosticoDAO diagnosticoDAO,
                            TratamientoDAO tratamientoDAO,
                            CitaDAO citaDAO) {
        this.ventanaPrincipal = ventanaPrincipal;     // NUEVO: guardamos referencia
        this.medicoActual = medicoActual;
        this.diagnosticoDAO = diagnosticoDAO;
        this.tratamientoDAO = tratamientoDAO;
        this.citaDAO = citaDAO;

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        String nombreMedico = (medicoActual != null) ? medicoActual.getNombre() : "Médico";

        JLabel titulo = new JLabel("Panel Médico - " + nombreMedico, SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        // Panel superior: título al centro y botón de cerrar sesión a la derecha
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(titulo, BorderLayout.CENTER);

        btnCerrarSesion = new JButton("Cerrar sesión");          // NUEVO
        panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);   // NUEVO

        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("ID Paciente:"));
        txtPacienteId = new JTextField(15);
        panelBusqueda.add(txtPacienteId);
        btnBuscar = new JButton("Buscar historial");
        panelBusqueda.add(btnBuscar);
        add(panelBusqueda, BorderLayout.SOUTH);

        JTabbedPane pestañas = new JTabbedPane();

        // ----- Pestaña Diagnósticos -----
        JPanel panelDiag = new JPanel(new BorderLayout(5, 5));
        modeloDiagnosticos = new DefaultTableModel(
                new Object[]{"ID", "Descripción", "CIE10", "Estado"}, 0
        );
        tablaDiagnosticos = new JTable(modeloDiagnosticos);
        panelDiag.add(new JScrollPane(tablaDiagnosticos), BorderLayout.CENTER);

        JPanel panelDiagBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnNuevoDiagnostico = new JButton("Nuevo diagnóstico");
        panelDiagBotones.add(btnNuevoDiagnostico);
        panelDiag.add(panelDiagBotones, BorderLayout.SOUTH);

        pestañas.addTab("Diagnósticos", panelDiag);

        // ----- Pestaña Tratamientos -----
        JPanel panelTrat = new JPanel(new BorderLayout(5, 5));
        modeloTratamientos = new DefaultTableModel(
                new Object[]{"ID", "Diagnóstico", "Descripción", "Estado"}, 0
        );
        tablaTratamientos = new JTable(modeloTratamientos);
        panelTrat.add(new JScrollPane(tablaTratamientos), BorderLayout.CENTER);

        JPanel panelTratBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnNuevoTratamiento = new JButton("Nuevo tratamiento");
        panelTratBotones.add(btnNuevoTratamiento);
        panelTrat.add(panelTratBotones, BorderLayout.SOUTH);

        pestañas.addTab("Tratamientos", panelTrat);

        // ----- Pestaña Citas -----
        JPanel panelCitas = new JPanel(new BorderLayout(5, 5));
        modeloCitas = new DefaultTableModel(
                new Object[]{"ID", "Fecha/hora", "Paciente", "Estado"}, 0
        );
        tablaCitas = new JTable(modeloCitas);
        panelCitas.add(new JScrollPane(tablaCitas), BorderLayout.CENTER);

        JPanel panelCitasBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnNuevaCita = new JButton("Nueva cita");
        panelCitasBotones.add(btnNuevaCita);
        panelCitas.add(panelCitasBotones, BorderLayout.SOUTH);

        pestañas.addTab("Citas", panelCitas);

        add(pestañas, BorderLayout.CENTER);

        // Acciones
        btnBuscar.addActionListener(e -> cargarHistorialPaciente());
        btnNuevoDiagnostico.addActionListener(e -> crearDiagnostico());
        btnNuevoTratamiento.addActionListener(e -> crearTratamiento());
        btnNuevaCita.addActionListener(e -> crearCita());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());  // NUEVO
    }

    private void cerrarSesion() {
        // Reutilizamos el mismo patrón que en RegistroUsuarioPanel: volver al login
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarLogin();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Sesión cerrada. Configura VentanaPrincipal para cambiar a la vista de login.");
        }
    }

    private void cargarHistorialPaciente() {
        String pacienteId = txtPacienteId.getText().trim();
        if (pacienteId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el ID del paciente.");
            return;
        }

        if (diagnosticoDAO == null || tratamientoDAO == null || citaDAO == null) {
            JOptionPane.showMessageDialog(this,
                    "DAOs no configurados en VistaMedicoPanel. Revisa la creación del panel.");
            return;
        }

        limpiarModelo(modeloDiagnosticos);
        limpiarModelo(modeloTratamientos);
        limpiarModelo(modeloCitas);

        List<Diagnostico> diagnos = diagnosticoDAO.listarDiagnosticosPorPaciente(pacienteId);
        for (Diagnostico d : diagnos) {
            modeloDiagnosticos.addRow(new Object[]{
                    d.getIdDiagnostico(),
                    d.getDescripcion(),
                    d.getCodigoCIE10(),
                    d.getEstado()
            });
        }

        for (Diagnostico d : diagnos) {
            List<Tratamiento> trats = tratamientoDAO.listarTratamientosPorDiagnostico(d.getIdDiagnostico());
            for (Tratamiento t : trats) {
                modeloTratamientos.addRow(new Object[]{
                        t.getIdTratamiento(),
                        d.getIdDiagnostico(),
                        t.getDescripcion(),
                        t.getEstado()
                });
            }
        }

        List<Cita> citas = citaDAO.listarCitasPorPaciente(pacienteId);
        for (Cita c : citas) {
            modeloCitas.addRow(new Object[]{
                    c.getIdCita(),
                    c.getFechaHora(),
                    c.getIdPaciente(),
                    c.getEstado()
            });
        }
    }

    private void crearDiagnostico() {
        String pacienteId = txtPacienteId.getText().trim();
        if (pacienteId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero ingrese el ID del paciente.");
            return;
        }
        if (diagnosticoDAO == null) {
            JOptionPane.showMessageDialog(this, "DiagnosticoDAO no configurado.");
            return;
        }

        String id = JOptionPane.showInputDialog(this, "ID diagnóstico:");
        if (id == null || id.isBlank()) return;

        String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
        if (descripcion == null || descripcion.isBlank()) return;

        String cie10 = JOptionPane.showInputDialog(this, "Código CIE10:");
        if (cie10 == null) cie10 = "";

        String estado = JOptionPane.showInputDialog(this, "Estado (activo, resuelto, etc.):");
        if (estado == null || estado.isBlank()) estado = "activo";

        Diagnostico d = new Diagnostico();
        d.setIdDiagnostico(id);
        d.setPacienteId(pacienteId);
        d.setDescripcion(descripcion);
        d.setCodigoCIE10(cie10);
        d.setEstado(estado);

        diagnosticoDAO.agregarDiagnostico(d);
        cargarHistorialPaciente();
    }

    private void crearTratamiento() {
        if (tratamientoDAO == null) {
            JOptionPane.showMessageDialog(this, "TratamientoDAO no configurado.");
            return;
        }

        int fila = tablaDiagnosticos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione primero un diagnóstico en la pestaña Diagnósticos.");
            return;
        }

        String diagnosticoId = (String) modeloDiagnosticos.getValueAt(fila, 0);

        String id = JOptionPane.showInputDialog(this, "ID tratamiento:");
        if (id == null || id.isBlank()) return;

        String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
        if (descripcion == null || descripcion.isBlank()) return;

        String estado = JOptionPane.showInputDialog(this, "Estado (activo, finalizado, etc.):");
        if (estado == null || estado.isBlank()) estado = "activo";

        Tratamiento t = new Tratamiento();
        t.setIdTratamiento(id);
        t.setIdDiagnostico(diagnosticoId);
        t.setDescripcion(descripcion);
        t.setEstado(estado);

        tratamientoDAO.agregarTratamiento(t);
        cargarHistorialPaciente();
    }

    private void crearCita() {
        String pacienteId = txtPacienteId.getText().trim();
        if (pacienteId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero ingrese el ID del paciente.");
            return;
        }
        if (citaDAO == null) {
            JOptionPane.showMessageDialog(this, "CitaDAO no configurado.");
            return;
        }

        String id = JOptionPane.showInputDialog(this, "ID cita:");
        if (id == null || id.isBlank()) return;

        String fechaTexto = JOptionPane.showInputDialog(this,
                "Fecha/hora (formato: yyyy-MM-dd HH:mm):");
        if (fechaTexto == null || fechaTexto.isBlank()) return;

        LocalDateTime fechaHora;
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            fechaHora = LocalDateTime.parse(fechaTexto, fmt);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido.");
            return;
        }

        String estado = JOptionPane.showInputDialog(this, "Estado (programada, confirmada, etc.):");
        if (estado == null || estado.isBlank()) estado = "programada";

        Cita c = new Cita();
        c.setIdCita(id);
        c.setIdPaciente(pacienteId);
        if (medicoActual != null) {
            c.setIdMedico(medicoActual.getIdUsuario());
        }
        c.setFechaHora(fechaHora);
        c.setEstado(estado);

        citaDAO.agregarCita(c);
        cargarHistorialPaciente();
    }

    private void limpiarModelo(DefaultTableModel modelo) {
        modelo.setRowCount(0);
    }
}
