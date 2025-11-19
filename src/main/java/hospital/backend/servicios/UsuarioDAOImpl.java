package hospital.backend.servicios;

import hospital.backend.usuarios.Usuario;

import java.util.*;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Map<String, Usuario> usuarios = new HashMap<>();

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getIdUsuario(), usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarios.put(usuario.getIdUsuario(), usuario);
    }

    @Override
    public void eliminarUsuario(String id) {
        usuarios.remove(id);
    }

    @Override
    public Usuario buscarPorId(String id) {
        return usuarios.get(id);
    }

    @Override
    public Usuario buscarPorNombre(String nombre) {
        for (Usuario u : usuarios.values()) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}

