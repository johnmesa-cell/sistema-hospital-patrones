package hospital.backend.servicios;

import hospital.backend.historialclinico.HistorialClinico;
import java.util.List;

public interface HistorialClinicoDAO {
    void agregarHistorialClinico(HistorialClinico historial);
    void actualizarHistorialClinico(HistorialClinico historial);
    void eliminarHistorialClinico(String id);
    HistorialClinico buscarPorId(String id);
    HistorialClinico buscarPorPaciente(String pacienteId);
    List<HistorialClinico> listarTodos();
}

