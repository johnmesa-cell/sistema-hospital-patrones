package hospital.backend.servicios;

import hospital.backend.historialclinico.Diagnostico;
import java.util.List;

public interface DiagnosticoDAO {
    void agregarDiagnostico(Diagnostico diagnostico);
    void actualizarDiagnostico(Diagnostico diagnostico);
    void eliminarDiagnostico(String id);
    Diagnostico buscarPorId(String id);
    List<Diagnostico> listarDiagnosticosPorPaciente(String pacienteId);
}

