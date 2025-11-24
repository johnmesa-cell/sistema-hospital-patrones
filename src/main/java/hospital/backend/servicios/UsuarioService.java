package hospital.backend.servicios;

import hospital.backend.usuarios.Usuario;
import hospital.backend.auditoria.ReporteAuditoria;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final ReporteAuditoria reporteAuditoria;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        // Aquí configuras la ruta donde quieres guardar los reportes (ajusta según entorno)
        this.reporteAuditoria = new ReporteAuditoria("C:/Users/ao184/Documents/GitHub/sistema-hospital-patrones/src/main/resources/mis_reportes");
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioDAO.agregarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuarioEditado) {
        // Obtener usuario original desde BD para comparar cambios
        Usuario usuarioOriginal = usuarioDAO.buscarPorId(usuarioEditado.getId_Usuario());

        // Actualizar usuario en la base de datos
        usuarioDAO.actualizarUsuario(usuarioEditado);

        // Registrar cambios detectados en reporte y archivo TXT
        reporteAuditoria.registrarCambiosUsuario(usuarioOriginal, usuarioEditado);
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarioDAO.buscarPorNombre(nombre);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }
}

