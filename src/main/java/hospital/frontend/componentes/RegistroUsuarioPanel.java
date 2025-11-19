package hospital.frontend.componentes;

import hospital.backend.servicios.UsuarioService;
import hospital.backend.usuarios.Usuario;

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
        String idUsuario = txtIdUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String tipo = txtTipo.getText().trim().toLowerCase();
        String contrasena = new String(txtContrasena.getPassword());

        if (idUsuario.isEmpty() || nombre.isEmpty() || email.isEmpty() || tipo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        if (!(tipo.equals("medico") || tipo.equals("paciente") || tipo.equals("administrador"))) {
            JOptionPane.showMessageDialog(this, "El tipo debe ser 'medico', 'paciente' o 'administrador'.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(idUsuario, nombre, email, tipo, contrasena);
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

