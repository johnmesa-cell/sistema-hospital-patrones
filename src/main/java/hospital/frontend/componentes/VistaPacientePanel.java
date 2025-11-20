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

    private Usuario pacienteActual;
    private DiagnosticoDAO diagnosticoDAO;
    private TratamientoDAO tratamientoDAO;
    private NotaMedicaDAO notaMedicaDAO;

    private JTable tablaDiagnosticos;
    private JTable tablaTratamientos;
    private JTable tablaNotas;

    public VistaPacientePanel(Usuario paciente, DiagnosticoDAO dDao, TratamientoDAO tDao, NotaMedicaDAO nDao) {
        this.pacienteActual = paciente;
        this.diagnosticoDAO = dDao;
        this.tratamientoDAO = tDao;
        this.notaMedicaDAO = nDao;

        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Panel Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        // Diagnósticos
        DefaultTableModel modeloDiagnosticos = new DefaultTableModel(
                new Object[]{"ID", "Descripción", "CIE10", "Estado"}, 0
        );
        tablaDiagnosticos = new JTable(modeloDiagnosticos);
        tabs.addTab("Diagnósticos", new JScrollPane(tablaDiagnosticos));

        // Tratamientos
        DefaultTableModel modeloTratamientos = new DefaultTableModel(
                new Object[]{"ID", "Diagnóstico", "Descripción", "Estado"}, 0
        );
        tablaTratamientos = new JTable(modeloTratamientos);
        tabs.addTab("Tratamientos", new JScrollPane(tablaTratamientos));

        // Notas médicas
        DefaultTableModel modeloNotas = new DefaultTableModel(
                new Object[]{"Tipo", "Contenido", "Fecha"}, 0
        );
        tablaNotas = new JTable(modeloNotas);
        tabs.addTab("Notas médicas", new JScrollPane(tablaNotas));

        add(tabs, BorderLayout.CENTER);

        cargarDatosPaciente();
    }

    private void cargarDatosPaciente() {
        // Diagnósticos
        List<Diagnostico> lDiagnosticos = diagnosticoDAO.listarDiagnosticosPorPaciente(pacienteActual.getIdUsuario());
        DefaultTableModel mDiagnosticos = (DefaultTableModel) tablaDiagnosticos.getModel();
        for (Diagnostico d : lDiagnosticos) {
            mDiagnosticos.addRow(new Object[]{d.getIdDiagnostico(), d.getDescripcion(), d.getCodigoCIE10(), d.getEstado()});
        }
        // Tratamientos, por cada diagnóstico asociado al paciente
        DefaultTableModel mTratamientos = (DefaultTableModel) tablaTratamientos.getModel();
        for (Diagnostico d : lDiagnosticos) {
            List<Tratamiento> lTratamientos = tratamientoDAO.listarTratamientosPorDiagnostico(d.getIdDiagnostico());
            for (Tratamiento t : lTratamientos) {
                mTratamientos.addRow(new Object[]{t.getIdTratamiento(), d.getIdDiagnostico(), t.getDescripcion(), t.getEstado()});
            }
        }
        // Notas médicas (opcional: solo visibles para paciente)
        DefaultTableModel mNotas = (DefaultTableModel) tablaNotas.getModel();
        List<NotaMedica> lNotas = notaMedicaDAO.listarNotasPorPaciente(pacienteActual.getIdUsuario());
        for (NotaMedica n : lNotas) {
            if (n.isVisibleParaPaciente()) {
                mNotas.addRow(new Object[]{n.getTipoNota(), n.getContenidoPaciente(), n.getFechaCreacion()});
            }
        }
    }
}


