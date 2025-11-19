package hospital.frontend.componentes;

import hospital.frontend.servicios.AutenticacionService;
import hospital.backend.usuarios.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar, btnRegistrar;
    private AutenticacionService authService;
    private VentanaPrincipal ventanaPrincipal;

    public LoginPanel(AutenticacionService authService, VentanaPrincipal ventanaPrincipal) {
        this.authService = authService;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        btnIngresar = new JButton("Ingresar");
        btnRegistrar = new JButton("Registrarse");
        add(btnIngresar);
        add(btnRegistrar);

        btnIngresar.addActionListener(e -> autenticar());
        btnRegistrar.addActionListener(e -> ventanaPrincipal.mostrarRegistro());
    }

    private void autenticar() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        Usuario usuarioAutenticado = authService.autenticar(usuario, contrasena);

        if (usuarioAutenticado != null) {
            JOptionPane.showMessageDialog(this, "Login exitoso");
            ventanaPrincipal.cambiarVistaPorRol(usuarioAutenticado.getTipo());
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}
