package hospital.backend.servicios;

import hospital.backend.usuarios.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int id);
    Usuario buscarPorId(int id);
    Usuario buscarPorNombre(String nombre);

    Usuario buscarPorEmail(String email);

    List<Usuario> listarUsuarios();
}
