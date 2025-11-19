package hospital.backend.servicios;

import hospital.backend.servicios.UsuarioDAO;
import hospital.backend.usuarios.Usuario;

import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioDAO.agregarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(String id) {
        usuarioDAO.eliminarUsuario(id);
    }

    public Usuario buscarPorId(String id) {
        return usuarioDAO.buscarPorId(id);
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarioDAO.buscarPorNombre(nombre);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }
}

