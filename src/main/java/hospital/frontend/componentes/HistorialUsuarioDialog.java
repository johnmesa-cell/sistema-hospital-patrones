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

public class HistorialUsuarioDialog extends JDialog {
    public HistorialUsuarioDialog(JFrame parent, int idUsuario,
                                  DiagnosticoDAO diagnosticoDAO,
                                  TratamientoDAO tratamientoDAO,
                                  NotaMedicaDAO notaMedicaDAO) {
        super(parent, "Historial médico de usuario: " + idUsuario, true);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Pestañas
        JTabbedPane tabs = new JTabbedPane();

        // Diagnósticos
        DefaultTableModel modeloDiagnosticos = new DefaultTableModel(
                new Object[]{"ID", "Descripción", "CIE10", "Estado"}, 0);
        JTable tablaDiagnosticos = new JTable(modeloDiagnosticos);
        tabs.addTab("Diagnósticos", new JScrollPane(tablaDiagnosticos));

        List<Diagnostico> diagnosticos = diagnosticoDAO.listarDiagnosticosPorPaciente(String.valueOf(idUsuario));
        for (Diagnostico d : diagnosticos) {
            modeloDiagnosticos.addRow(new Object[]{
                    d.getIdDiagnostico(), d.getDescripcion(), d.getCodigoCIE10(), d.getEstado()
            });
        }

        // Tratamientos
        DefaultTableModel modeloTratamientos = new DefaultTableModel(
                new Object[]{"ID", "Diagnóstico", "Descripción", "Estado"}, 0);
        JTable tablaTratamientos = new JTable(modeloTratamientos);
        tabs.addTab("Tratamientos", new JScrollPane(tablaTratamientos));

        for (Diagnostico d : diagnosticos) {
            List<Tratamiento> tratamientos = tratamientoDAO.listarTratamientosPorDiagnostico(d.getIdDiagnostico());
            for (Tratamiento t : tratamientos) {
                modeloTratamientos.addRow(new Object[]{
                        t.getIdTratamiento(), d.getIdDiagnostico(),
                        t.getDescripcion(), t.getEstado()
                });
            }
        }

        // Notas médicas
        DefaultTableModel modeloNotas = new DefaultTableModel(
                new Object[]{"Tipo", "Contenido", "Fecha"}, 0);
        JTable tablaNotas = new JTable(modeloNotas);
        tabs.addTab("Notas médicas", new JScrollPane(tablaNotas));

        List<NotaMedica> notas = notaMedicaDAO.listarNotasPorPaciente(String.valueOf(idUsuario));
        for (NotaMedica n : notas) {
            modeloNotas.addRow(new Object[]{
                    n.getTipoNota(), n.getContenidoPaciente(), n.getFechaCreacion()
            });
        }

        add(tabs, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }
}
