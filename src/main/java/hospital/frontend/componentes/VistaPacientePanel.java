package hospital.frontend.componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.historialclinico.Tratamiento;
import hospital.backend.historialclinico.NotaMedica;
import hospital.backend.servicios.DiagnosticoDAO;
import hospital.backend.servicios.TratamientoDAO;
import hospital.backend.servicios.NotaMedicaDAO;
import hospital.backend.usuarios.Usuario;

public class VistaPacientePanel extends JPanel {

    // Referencia a la ventana principal para poder controlar la navegación (ej. volver al login)
    private VentanaPrincipal ventanaPrincipal;

    // Usuario actual paciente
    private Usuario pacienteActual;

    // DAOs necesarios para obtener diagnósticos, tratamientos y notas médicas
    private DiagnosticoDAO diagnosticoDAO;
    private TratamientoDAO tratamientoDAO;
    private NotaMedicaDAO notaMedicaDAO;

    // Tablas para mostrar la información del paciente
    private JTable tablaDiagnosticos;
    private JTable tablaTratamientos;
    private JTable tablaNotas;

    // Botón para cerrar sesión
    private JButton btnCerrarSesion;

    /**
     * Constructor del panel paciente.
     * Recibe la ventana principal (para control de navegación),
     * el usuario paciente y los DAOs necesarios para mostrar datos.
     */
    public VistaPacientePanel(VentanaPrincipal ventanaPrincipal,
                              Usuario paciente,
                              DiagnosticoDAO dDao,
                              TratamientoDAO tDao,
                              NotaMedicaDAO nDao) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.pacienteActual = paciente;
        this.diagnosticoDAO = dDao;
        this.tratamientoDAO = tDao;
        this.notaMedicaDAO = nDao;

        // Establecer layout principal del panel
        setLayout(new BorderLayout());

        // Crear un título centrado grande para el panel paciente
        JLabel titulo = new JLabel("Panel Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Panel superior para contener título y botón cerrar sesión alineados en un BorderLayout
        JPanel panelSuperior = new JPanel(new BorderLayout());

        // Añadir el título centrado en el panel superior
        panelSuperior.add(titulo, BorderLayout.CENTER);

        // Crear botón "Cerrar sesión"
        btnCerrarSesion = new JButton("Cerrar sesión");
        // Añadir el botón a la derecha en el panel superior
        panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);

        // Añadir el panel superior a la parte norte (arriba) del panel principal
        add(panelSuperior, BorderLayout.NORTH);

        // Crear pestañas (JTabbedPane) para dividir las secciones de datos del paciente
        JTabbedPane tabs = new JTabbedPane();

        // Crear tabla Diagnósticos con su modelo y columnas
        DefaultTableModel modeloDiagnosticos = new DefaultTableModel(
                new Object[]{"ID", "Descripción", "CIE10", "Estado"}, 0
        );
        tablaDiagnosticos = new JTable(modeloDiagnosticos);
        // Añadir tabla diagnósticos dentro de un scroll a la pestaña correspondiente
        tabs.addTab("Diagnósticos", new JScrollPane(tablaDiagnosticos));

        // Crear tabla Tratamientos con su modelo y columnas
        DefaultTableModel modeloTratamientos = new DefaultTableModel(
                new Object[]{"ID", "Diagnóstico", "Descripción", "Estado"}, 0
        );
        tablaTratamientos = new JTable(modeloTratamientos);
        // Añadir tabla tratamientos dentro de un scroll a la pestaña correspondiente
        tabs.addTab("Tratamientos", new JScrollPane(tablaTratamientos));

        // Crear tabla Notas Médicas con su modelo y columnas
        DefaultTableModel modeloNotas = new DefaultTableModel(
                new Object[]{"Tipo", "Contenido", "Fecha"}, 0
        );
        tablaNotas = new JTable(modeloNotas);
        // Añadir tabla notas médicas dentro de un scroll a la pestaña correspondiente
        tabs.addTab("Notas médicas", new JScrollPane(tablaNotas));

        // Añadir el componente pestañas al centro del panel
        add(tabs, BorderLayout.CENTER);

        // Cargar los datos del paciente en las tablas correspondientes
        cargarDatosPaciente();

        // Agregar acción al botón "Cerrar sesión"
        // Cuando el usuario hace clic, ejecuta el método cerrarSesion()
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    /**
     * Método para cargar los datos del paciente desde las fuentes (DAOs)
     * y mostrarlos en las tablas del panel.
     */
    private void cargarDatosPaciente() {
        // Obtener la lista de diagnósticos asociados al paciente
        List<Diagnostico> lDiagnosticos = diagnosticoDAO.listarDiagnosticosPorPaciente(String.valueOf(pacienteActual.getId_Usuario()));
        // Obtener el modelo de la tabla diagnósticos para añadir filas
        DefaultTableModel mDiagnosticos = (DefaultTableModel) tablaDiagnosticos.getModel();

        // Añadir cada diagnóstico a la tabla diagnósticos
        for (Diagnostico d : lDiagnosticos) {
            mDiagnosticos.addRow(new Object[]{
                    d.getIdDiagnostico(),
                    d.getDescripcion(),
                    d.getCodigoCIE10(),
                    d.getEstado()
            });
        }

        // Obtener el modelo de la tabla tratamientos para añadir filas
        DefaultTableModel mTratamientos = (DefaultTableModel) tablaTratamientos.getModel();

        // Por cada diagnóstico, obtener tratamientos asociados y añadirlos a la tabla tratamientos
        for (Diagnostico d : lDiagnosticos) {
            List<Tratamiento> lTratamientos = tratamientoDAO.listarTratamientosPorDiagnostico(d.getIdDiagnostico());
            for (Tratamiento t : lTratamientos) {
                mTratamientos.addRow(new Object[]{
                        t.getIdTratamiento(),
                        d.getIdDiagnostico(),
                        t.getDescripcion(),
                        t.getEstado()
                });
            }
        }

        // Obtener el modelo de la tabla notas médicas para añadir filas
        DefaultTableModel mNotas = (DefaultTableModel) tablaNotas.getModel();

        // Obtener las notas médicas visibles para el paciente y añadirlas a la tabla notas
        List<NotaMedica> lNotas = notaMedicaDAO.listarNotasPorPaciente(String.valueOf(pacienteActual.getId_Usuario()));
        for (NotaMedica n : lNotas) {
            if (n.isVisibleParaPaciente()) {
                mNotas.addRow(new Object[]{
                        n.getTipoNota(),
                        n.getContenidoPaciente(),
                        n.getFechaCreacion()
                });
            }
        }
    }

    /**
     * Método para cerrar sesión.
     * Este método llama a mostrarLogin() en la ventana principal para
     * volver a la pantalla de login.
     */
    private void cerrarSesion() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarLogin();
        } else {
            // Mensaje de error si ventanaPrincipal no está configurada
            JOptionPane.showMessageDialog(this,
                    "Sesión cerrada. Configura VentanaPrincipal para cambiar a la vista de login.");
        }
    }
}


