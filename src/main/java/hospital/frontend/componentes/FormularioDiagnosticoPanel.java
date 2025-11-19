package hospital.frontend.componentes;

import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.servicios.DiagnosticoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioDiagnosticoPanel extends JPanel {
    private JTextField txtId, txtDescripcion, txtCodigoCIE10, txtDescripcionTecnica, txtExplicacionPaciente, txtEstado;
    private JButton btnAgregar, btnLimpiar;
    private DefaultListModel<Diagnostico> modeloDiagnosticos;
    private JList<Diagnostico> listaDiagnosticos;
    private DiagnosticoDAO diagnosticoDAO;

    public FormularioDiagnosticoPanel(DiagnosticoDAO diagnosticoDAO) {
        this.diagnosticoDAO = diagnosticoDAO;
        setLayout(new BorderLayout());
        JPanel panelCampos = new JPanel(new GridLayout(7, 2, 5, 5));

        panelCampos.add(new JLabel("ID Diagnóstico:"));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelCampos.add(txtDescripcion);

        panelCampos.add(new JLabel("Código CIE10:"));
        txtCodigoCIE10 = new JTextField();
        panelCampos.add(txtCodigoCIE10);

        panelCampos.add(new JLabel("Descripción técnica:"));
        txtDescripcionTecnica = new JTextField();
        panelCampos.add(txtDescripcionTecnica);

        panelCampos.add(new JLabel("Explicación para paciente:"));
        txtExplicacionPaciente = new JTextField();
        panelCampos.add(txtExplicacionPaciente);

        panelCampos.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panelCampos.add(txtEstado);

        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        panelCampos.add(btnAgregar);
        panelCampos.add(btnLimpiar);

        add(panelCampos, BorderLayout.NORTH);

        modeloDiagnosticos = new DefaultListModel<>();
        listaDiagnosticos = new JList<>(modeloDiagnosticos);
        add(new JScrollPane(listaDiagnosticos), BorderLayout.CENTER);

        // Evento para agregar diagnóstico
        btnAgregar.addActionListener(e -> agregarDiagnostico());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarDiagnostico() {
        Diagnostico d = new Diagnostico();
        d.setIdDiagnostico(txtId.getText().trim());
        d.setDescripcion(txtDescripcion.getText().trim());
        d.setCodigoCIE10(txtCodigoCIE10.getText().trim());
        d.setDescripcionTecnica(txtDescripcionTecnica.getText().trim());
        d.setExplicacionPaciente(txtExplicacionPaciente.getText().trim());
        d.setEstado(txtEstado.getText().trim());

        diagnosticoDAO.agregarDiagnostico(d);
        cargarDiagnosticos();
        limpiarCampos();
    }

    private void cargarDiagnosticos() {
        modeloDiagnosticos.clear();
        // Si tienes el método listarDiagnosticosPorPaciente, pásale el id del paciente correspondiente:
        List<Diagnostico> diagnosticos = diagnosticoDAO.listarDiagnosticosPorPaciente(""); // Pon aquí el id adecuado
        for (Diagnostico d : diagnosticos) {
            modeloDiagnosticos.addElement(d);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtDescripcion.setText("");
        txtCodigoCIE10.setText("");
        txtDescripcionTecnica.setText("");
        txtExplicacionPaciente.setText("");
        txtEstado.setText("");
    }
}

