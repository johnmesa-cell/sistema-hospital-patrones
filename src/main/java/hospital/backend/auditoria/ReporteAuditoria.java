package hospital.backend.auditoria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import hospital.backend.usuarios.Usuario;

public class ReporteAuditoria {

    private String rutaCarpeta;

    public ReporteAuditoria(String rutaCarpeta) {
        this.rutaCarpeta = rutaCarpeta;
    }

    public void registrarCambiosUsuario(Usuario usuarioOriginal, Usuario usuarioEditado) {
        List<String> cambios = new java.util.ArrayList<>();

        if (!usuarioOriginal.getNombre().equals(usuarioEditado.getNombre())) {
            cambios.add("Cambio Nombre de Usuario");
        }
        if (!usuarioOriginal.getContraseña().equals(usuarioEditado.getContraseña())) {
            cambios.add("Cambio Contraseña");
        }
        // Añade aquí más campos si quieres

        if (cambios.isEmpty()) {
            return; // No hay cambios, no se registra nada
        }

        String mensaje = "Usuario ID " + usuarioEditado.getId_Usuario() + ": " + String.join("; ", cambios);

        // Registrar en consola o log de auditoria
        hospital.backend.auditoria.AuditoriaSingleton.getInstance().registrarAccion(mensaje);

        // Guardar en archivo
        guardarEnArchivo(mensaje);
    }

    public void guardarEnArchivo(String textoReporte) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nombreArchivo = rutaCarpeta + "/mis_reportes_" + timestamp + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write(textoReporte);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

