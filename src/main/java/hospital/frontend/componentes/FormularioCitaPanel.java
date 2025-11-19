package hospital.frontend.componentes;

import hospital.backend.citas.Cita;
import hospital.backend.servicios.CitaDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioCitaPanel extends JPanel {
    private JTextField txtId, txtPacienteId, txtFecha, txtMedico, txtEstado;
    private JButton btnAgregar, btnLimpiar;
    private DefaultListModel<Cita> modeloCitas;
    private JList<Cita> listaCitas;
    private CitaDAO citaDAO;

    public FormularioCitaPanel(CitaDAO citaDAO) {
        this.citaDAO = citaDAO;
        setLayout(new BorderLayout());
        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));

        panelCampos.add(new JLabel("ID Cita:"));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(new JLabel("ID Paciente:"));
        txtPacienteId = new JTextField();
        panelCampos.add(txtPacienteId);

        panelCampos.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        panelCampos.add(txtFecha);

        panelCampos.add(new JLabel("Médico:"));
        txtMedico = new JTextField();
        panelCampos.add(txtMedico);

        panelCampos.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panelCampos.add(txtEstado);

        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        panelCampos.add(btnAgregar);
        panelCampos.add(btnLimpiar);

        add(panelCampos, BorderLayout.NORTH);

        modeloCitas = new DefaultListModel<>();
        listaCitas = new JList<>(modeloCitas);
        add(new JScrollPane(listaCitas), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarCita());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarCita() {
        Cita c = new Cita();
        c.setIdCita(txtId.getText().trim());
        c.setIdPaciente(txtPacienteId.getText().trim());
        c.setFechaHora(txtFecha.getText().trim());
        c.setIdMedico(txtMedico.getText().trim());
        c.setEstado(txtEstado.getText().trim());

        citaDAO.agregarCita(c);
        cargarCitas();
        limpiarCampos();
    }

    private void cargarCitas() {
        modeloCitas.clear();
        // Puedes filtrar por paciente usando su id, si lo deseas:
        List<Cita> citas = citaDAO.listarCitasPorPaciente(""); // Pon aquí el id del paciente si aplica
        for (Cita c : citas) {
            modeloCitas.addElement(c);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtPacienteId.setText("");
        txtFecha.setText("");
        txtMedico.setText("");
        txtEstado.setText("");
    }
}
