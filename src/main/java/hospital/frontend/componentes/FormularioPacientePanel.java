package hospital.frontend.componentes;

import hospital.backend.servicios.PacienteDAO;
import hospital.backend.usuarios.Paciente;

import javax.swing.*;
import java.awt.*;

public class FormularioPacientePanel extends JPanel {
    private JTextField txtId, txtNombre, txtIdentificacion, txtFechaNacimiento;
    private JButton btnAgregar, btnLimpiar;
    private DefaultListModel<Paciente> modeloPacientes;
    private JList<Paciente> listaPacientes;

    public FormularioPacientePanel(PacienteDAO pacienteDAO) {
        setLayout(new BorderLayout());
        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 5, 5));

        panelCampos.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Identificación:"));
        txtIdentificacion = new JTextField();
        panelCampos.add(txtIdentificacion);

        panelCampos.add(new JLabel("Fecha Nacimiento:"));
        txtFechaNacimiento = new JTextField();
        panelCampos.add(txtFechaNacimiento);

        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        panelCampos.add(btnAgregar);
        panelCampos.add(btnLimpiar);

        add(panelCampos, BorderLayout.NORTH);

        modeloPacientes = new DefaultListModel<>();
        listaPacientes = new JList<>(modeloPacientes);
        add(new JScrollPane(listaPacientes), BorderLayout.CENTER);

        // Evento para agregar paciente
        btnAgregar.addActionListener(e -> agregarPaciente());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarPaciente() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String identificacion = txtIdentificacion.getText().trim();
        String fechaNacimiento = txtFechaNacimiento.getText().trim();

        if (nombre.isEmpty() || identificacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y identificación son obligatorios.");
            return;
        }

        Paciente p = new Paciente(id, nombre, identificacion, fechaNacimiento);
        modeloPacientes.addElement(p);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtIdentificacion.setText("");
        txtFechaNacimiento.setText("");
    }
}

