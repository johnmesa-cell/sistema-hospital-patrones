package hospital.backend.servicios;

import hospital.backend.usuarios.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(String id);
    Usuario buscarPorId(String id);
    Usuario buscarPorNombre(String nombre);
    List<Usuario> listarUsuarios();
}
