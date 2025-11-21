package hospital.frontend.componentes;

import hospital.backend.servicios.UsuarioService;
import hospital.backend.usuarios.Usuario;
import hospital.backend.util.SeguridadUtil; // <--- NUEVO import para el hash

import javax.swing.*;
import java.awt.*;

public class RegistroUsuarioPanel extends JPanel {

    private JTextField txtIdUsuario, txtNombre, txtEmail, txtTipo;
    private JPasswordField txtContrasena;
    private JButton btnRegistrar, btnVolver;
    private UsuarioService usuarioService;
    private VentanaPrincipal ventanaPrincipal;

    public RegistroUsuarioPanel(UsuarioService usuarioService, VentanaPrincipal ventanaPrincipal) {
        this.usuarioService = usuarioService;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("ID usuario:"));
        txtIdUsuario = new JTextField();
        add(txtIdUsuario);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Tipo (medico, paciente, administrador):"));
        txtTipo = new JTextField();
        add(txtTipo);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        btnRegistrar = new JButton("Registrar");
        btnVolver = new JButton("Volver");
        add(btnRegistrar);
        add(btnVolver);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> ventanaPrincipal.mostrarLogin());
    }

    private void registrarUsuario() {
        // 1. Obtener los datos desde los JTextField
        String idText = txtIdUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String tipo = txtTipo.getText().trim().toLowerCase();
        String contrasenaPlano = new String(txtContrasena.getPassword()).trim();

        // 2. Validación: No permitir campos vacíos
        if (idText.isEmpty() || nombre.isEmpty() || email.isEmpty() || tipo.isEmpty() || contrasenaPlano.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        // 3. Validación: El tipo debe ser uno de los permitidos
        if (!(tipo.equals("medico") || tipo.equals("paciente") || tipo.equals("administrador"))) {
            JOptionPane.showMessageDialog(this, "El tipo debe ser 'medico', 'paciente' o 'administrador'.");
            return;
        }

        // 4. Validación y conversión de id a entero
        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.");
            return;
        }

        // 5. (Opcional) Hashear la contraseña si usas SeguridadUtil
        String contrasenaHasheada = SeguridadUtil.hashPassword(contrasenaPlano);

        // 6. Creación del nuevo Usuario y agregarlo
        Usuario nuevoUsuario = new Usuario(idUsuario, nombre, email, tipo, contrasenaHasheada);
        usuarioService.agregarUsuario(nuevoUsuario);

        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
        limpiarCampos();
        ventanaPrincipal.mostrarLogin();
    }

    private void limpiarCampos() {
        txtIdUsuario.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtTipo.setText("");
        txtContrasena.setText("");
    }
}


