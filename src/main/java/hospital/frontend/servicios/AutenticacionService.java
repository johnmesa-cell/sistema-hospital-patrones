package hospital.frontend.servicios;

import hospital.backend.servicios.UsuarioDAO;
import hospital.backend.usuarios.Usuario;
import hospital.backend.util.SeguridadUtil;   // <-- NUEVO: utilidad para hacer hash

/**
 * Servicio encargado de la lógica de autenticación de usuarios.
 * Se apoya en UsuarioDAO para consultar usuarios y en SeguridadUtil para hashear contraseñas.
 */
public class AutenticacionService {

    private final UsuarioDAO usuarioDAO;

    // Constructor: recibe el DAO que ya usas en el resto del backend
    public AutenticacionService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Autentica un usuario por nombre y contraseña en texto plano.
     * IMPORTANTE: aquí se hace el hash de la contraseña ingresada y se compara
     * con el hash almacenado en la base de datos.
     *
     * @param nombreUsuario Nombre de usuario (o login) ingresado en el formulario.
     * @param contrasenaPlano Contraseña tal como la escribió el usuario.
     * @return el Usuario autenticado si las credenciales son correctas; null en caso contrario.
     */
    public Usuario autenticar(String nombreUsuario, String contrasenaPlano) {
        // 1) Buscar el usuario en BD por nombre
        Usuario user = usuarioDAO.buscarPorNombre(nombreUsuario);
        if (user == null) {
            return null; // usuario no existe
        }

        // 2) Hashear la contraseña que escribió el usuario en el login
        //    Esto DEBE ser el mismo algoritmo que usas en RegistroUsuarioPanel (SeguridadUtil.hashPassword)
        String hashIngresado = SeguridadUtil.hashPassword(contrasenaPlano);  // <-- NUEVO: uso de hash

        // 3) Comparar hash ingresado con el hash guardado en la BD
        if (hashIngresado.equals(user.getContraseña())) {
            return user; // credenciales válidas
        }

        return null; // contraseña incorrecta
    }
}


