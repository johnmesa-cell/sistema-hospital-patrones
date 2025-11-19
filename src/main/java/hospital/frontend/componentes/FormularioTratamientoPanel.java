package hospital.frontend.componentes;

import hospital.backend.historialclinico.Tratamiento;
import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.servicios.TratamientoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioTratamientoPanel extends JPanel {
    private JTextField txtId, txtDescripcion, txtDiagnosticoId, txtFechaInicio, txtFechaFin, txtEstado;
    private JButton btnAgregar, btnLimpiar;
    private DefaultListModel<Tratamiento> modeloTratamientos;
    private JList<Tratamiento> listaTratamientos;
    private TratamientoDAO tratamientoDAO;

    public FormularioTratamientoPanel(TratamientoDAO tratamientoDAO) {
        this.tratamientoDAO = tratamientoDAO;
        setLayout(new BorderLayout());
        JPanel panelCampos = new JPanel(new GridLayout(7, 2, 5, 5));

        panelCampos.add(new JLabel("ID Tratamiento:"));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(new JLabel("ID Diagnóstico:"));
        txtDiagnosticoId = new JTextField();
        panelCampos.add(txtDiagnosticoId);

        panelCampos.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelCampos.add(txtDescripcion);

        panelCampos.add(new JLabel("Fecha Inicio:"));
        txtFechaInicio = new JTextField();
        panelCampos.add(txtFechaInicio);

        panelCampos.add(new JLabel("Fecha Fin:"));
        txtFechaFin = new JTextField();
        panelCampos.add(txtFechaFin);

        panelCampos.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panelCampos.add(txtEstado);

        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        panelCampos.add(btnAgregar);
        panelCampos.add(btnLimpiar);

        add(panelCampos, BorderLayout.NORTH);

        modeloTratamientos = new DefaultListModel<>();
        listaTratamientos = new JList<>(modeloTratamientos);
        add(new JScrollPane(listaTratamientos), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarTratamiento());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarTratamiento() {
        Tratamiento t = new Tratamiento();
        t.setIdTratamiento(txtId.getText().trim());
        t.setIdDiagnostico(txtDiagnosticoId.getText().trim());
        t.setDescripcion(txtDescripcion.getText().trim());
        t.setFechaInicio(txtFechaInicio.getText().trim());
        t.setFechaFin(txtFechaFin.getText().trim());
        t.setEstado(txtEstado.getText().trim());

        tratamientoDAO.agregarTratamiento(t);
        cargarTratamientos();
        limpiarCampos();
    }

    private void cargarTratamientos() {
        modeloTratamientos.clear();
        // Si deseas filtrar por diagnóstico, pásale el diagnósticoId adecuado:
        List<Tratamiento> tratamientos = tratamientoDAO.listarTratamientosPorDiagnostico(""); // Pon aquí el ID correspondiente
        for (Tratamiento t : tratamientos) {
            modeloTratamientos.addElement(t);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtDiagnosticoId.setText("");
        txtDescripcion.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtEstado.setText("");
    }
}

