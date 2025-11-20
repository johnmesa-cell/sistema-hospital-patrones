package hospital.backend.servicios;

import hospital.backend.historialclinico.NotaMedica;
import java.util.List;

public interface NotaMedicaDAO {
    void agregarNota(NotaMedica nota);
    void actualizarNota(NotaMedica nota);
    void eliminarNota(String idNota);
    NotaMedica buscarPorId(String idNota);
    List<NotaMedica> listarNotasPorHistorial(String idHistorial);
    List<NotaMedica> listarNotasPorPaciente(String idPaciente);
}
