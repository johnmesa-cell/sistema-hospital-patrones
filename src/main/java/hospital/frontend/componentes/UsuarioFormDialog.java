package hospital.frontend.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import hospital.backend.usuarios.Usuario;
import hospital.backend.util.SeguridadUtil; // <-- igual que en RegistroUsuarioPanel

public class UsuarioFormDialog extends JDialog {
    private JTextField txtId_Usuario, txtNombre, txtCorreo;
    private JPasswordField txtContrasena;           // <-- nuevo campo contraseña
    private JComboBox<String> comboRol;
    private JButton btnGuardar, btnCancelar;
    private Usuario usuario; // Usuario que se va a crear o editar
    private boolean esEdicion;

    public UsuarioFormDialog(Window parent, String titulo, Usuario usuario) {
        super(parent, titulo, ModalityType.APPLICATION_MODAL);
        this.usuario = usuario;
        this.esEdicion = usuario != null;

        setSize(400, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Panel de campos: 5 filas (ID, Nombre, Correo, Contraseña, Rol)
        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelCampos.add(new JLabel("ID:"));
        txtId_Usuario = new JTextField();
        panelCampos.add(txtId_Usuario);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelCampos.add(txtCorreo);

        panelCampos.add(new JLabel("Contraseña:"));      // <-- etiqueta contraseña
        txtContrasena = new JPasswordField();            // <-- campo contraseña
        panelCampos.add(txtContrasena);

        panelCampos.add(new JLabel("Rol:"));
        comboRol = new JComboBox<>(new String[]{"administrador", "medico", "paciente"});
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
        txtId_Usuario.setText(String.valueOf(usuario.getId_Usuario()));
        txtNombre.setText(usuario.getNombre());
        txtCorreo.setText(usuario.getEmail());
        comboRol.setSelectedItem(usuario.getTipo());
        // Por seguridad, no rellenamos la contraseña existente
        txtContrasena.setText("");
    }

    private void guardarUsuario() {
        String idText = txtId_Usuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String rol = (String) comboRol.getSelectedItem();
        String contrasenaPlano = new String(txtContrasena.getPassword()).trim(); // igual que en RegistroUsuarioPanel

        if (idText.isEmpty() || nombre.isEmpty() || correo.isEmpty() || rol == null || contrasenaPlano.isEmpty()) {
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

        // Aplicar el mismo hash que en RegistroUsuarioPanel
        String contrasenaHasheada = SeguridadUtil.hashPassword(contrasenaPlano);

        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario.setId_Usuario(id);
        usuario.setNombre(nombre);
        usuario.setEmail(correo);
        usuario.setTipo(rol.toLowerCase());
        usuario.setContraseña(contrasenaHasheada); // aquí ya va hasheada, igual que en el panel de registro

        dispose();
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
