package hospital.frontend.servicios;

import hospital.backend.servicios.UsuarioDAO;
import hospital.backend.usuarios.Usuario;

public class AutenticacionService {
    private final UsuarioDAO usuarioDAO;

    public AutenticacionService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String nombreUsuario, String contrasena) {
        Usuario user = usuarioDAO.buscarPorNombre(nombreUsuario);
        if (user != null && user.getContraseña().equals(contrasena)) {
            return user;
        }
        return null;
    }
}

