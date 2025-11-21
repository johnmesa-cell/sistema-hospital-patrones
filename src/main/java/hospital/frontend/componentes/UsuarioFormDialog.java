package hospital.frontend.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import hospital.backend.usuarios.Usuario;

public class UsuarioFormDialog extends JDialog {
    private JTextField txtIdUsuario, txtNombre, txtCorreo;
    private JComboBox<String> comboRol;
    private JButton btnGuardar, btnCancelar;
    private Usuario usuario; // Usuario que se va a crear o editar
    private boolean esEdicion;

    public UsuarioFormDialog(Window parent, String titulo, Usuario usuario) {
        super(parent, titulo, ModalityType.APPLICATION_MODAL);
        this.usuario = usuario;
        this.esEdicion = usuario != null;

        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelCampos.add(new JLabel("ID:"));
        txtIdUsuario = new JTextField();
        panelCampos.add(txtIdUsuario);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelCampos.add(txtCorreo);

        panelCampos.add(new JLabel("Rol:"));
        comboRol = new JComboBox<>(new String[]{"Administrador", "Médico", "Enfermero"});
        panelCampos.add(comboRol);

        add(panelCampos, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        // Si es edición, cargar datos
        if (esEdicion) {
            cargarDatosUsuario();
        }

        // Listeners
        btnGuardar.addActionListener(e -> guardarUsuario());
        btnCancelar.addActionListener(e -> {
            this.usuario = null;
            dispose();
        });
    }

    private void cargarDatosUsuario() {
        txtNombre.setText(usuario.getNombre());
        txtCorreo.setText(usuario.getEmail());
        comboRol.setSelectedItem(usuario.getTipo());
    }

    private void guardarUsuario() {
        String idText = txtIdUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String rol = (String) comboRol.getSelectedItem();

        if (idText.isEmpty() || nombre.isEmpty() || correo.isEmpty() || rol == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario.setIdUsuario(id); // aquí asignas el ID manualmente
        usuario.setNombre(nombre);
        usuario.setEmail(correo);
        usuario.setTipo(rol);

        dispose();
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
