package hospital.backend.servicios;

import hospital.backend.historialclinico.Tratamiento;
import java.util.List;

public interface TratamientoDAO {
    void agregarTratamiento(Tratamiento tratamiento);
    void actualizarTratamiento(Tratamiento tratamiento);
    void eliminarTratamiento(String id);
    Tratamiento buscarPorId(String id);
    List<Tratamiento> listarTratamientosPorDiagnostico(String diagnosticoId);
}

