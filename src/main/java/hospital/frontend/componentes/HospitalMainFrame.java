package hospital.frontend.componentes;

import hospital.backend.servicios.*;

import javax.swing.*;

public class HospitalMainFrame extends JFrame {
    public HospitalMainFrame() {
        setTitle("Sistema de Gestión Hospitalaria");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Instancias de DAOs simulados (puedes cambiar por versiones con BD real)
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAOImpl();
        TratamientoDAO tratamientoDAO = new TratamientoDAOImpl();
        CitaDAO citaDAO = new CitaDAOImpl();

        // Paneles principales
        FormularioPacientePanel pacientePanel = new FormularioPacientePanel(pacienteDAO);
        FormularioDiagnosticoPanel diagnosticoPanel = new FormularioDiagnosticoPanel(diagnosticoDAO);
        FormularioTratamientoPanel tratamientoPanel = new FormularioTratamientoPanel(tratamientoDAO);
        FormularioCitaPanel citaPanel = new FormularioCitaPanel(citaDAO);

        // Pestañas para cada sección
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Pacientes", pacientePanel);
        tabs.addTab("Diagnósticos", diagnosticoPanel);
        tabs.addTab("Tratamientos", tratamientoPanel);
        tabs.addTab("Citas", citaPanel);

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HospitalMainFrame().setVisible(true);
        });
    }
}

