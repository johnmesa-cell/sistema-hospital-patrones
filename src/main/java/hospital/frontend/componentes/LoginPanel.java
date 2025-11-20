package hospital.frontend.componentes;

import hospital.frontend.servicios.AutenticacionService;  // NUEVO: usamos el servicio de autenticación
import hospital.backend.usuarios.Usuario;               // Para recibir el usuario autenticado

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JButton btnRegistrar;

    // Referencias a la lógica de negocio y a la ventana principal
    private final AutenticacionService autenticacionService; // NUEVO: se inyecta el servicio
    private final VentanaPrincipal ventanaPrincipal;

    public LoginPanel(AutenticacionService autenticacionService, VentanaPrincipal ventanaPrincipal) {
        this.autenticacionService = autenticacionService;   // guardamos la referencia del servicio
        this.ventanaPrincipal = ventanaPrincipal;           // para poder cambiar de vista o abrir registro

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

        // Acción de login: delega en el método privado autenticar()
        btnIngresar.addActionListener(e -> autenticar());

        // Acción para ir a la pantalla de registro
        btnRegistrar.addActionListener(e -> ventanaPrincipal.mostrarRegistro());
    }

    /**
     * Toma usuario y contraseña desde los campos de texto,
     * llama a AutenticacionService (que internamente hace el hash y compara)
     * y, si es correcto, redirige según el rol del usuario.
     */
    private void autenticar() {
        String nombreUsuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar usuario y contraseña.");
            return;
        }

        // Aquí NO hacemos hash; lo hace AutenticacionService por dentro.
        Usuario usuarioAutenticado = autenticacionService.autenticar(nombreUsuario, contrasena);

        if (usuarioAutenticado != null) {
            JOptionPane.showMessageDialog(this, "Login exitoso.");

            // Obtenemos el tipo/rol para decidir qué vista mostrar
            String rol = usuarioAutenticado.getTipo().toLowerCase();

            // VentanaPrincipal se encarga de cambiar el panel según el rol
            ventanaPrincipal.setUsuarioActual(usuarioAutenticado); // ← PRIMERO GUARDAR EL USUARIO
            ventanaPrincipal.cambiarVistaPorRol(rol);              // ← LUEGO MOSTRAR EL PANEL
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}

