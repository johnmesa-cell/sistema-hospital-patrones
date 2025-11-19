package hospital.frontend.servicios;

import hospital.backend.historialclinico.Tratamiento;
import hospital.backend.servicios.TratamientoDAO;

import java.util.List;

public class TratamientoService {
    private final TratamientoDAO tratamientoDAO;

    public TratamientoService(TratamientoDAO tratamientoDAO) {
        this.tratamientoDAO = tratamientoDAO;
    }

    public void agregarTratamiento(Tratamiento tratamiento) {
        // Validaciones o lógica adicional antes de guardar
        tratamientoDAO.agregarTratamiento(tratamiento);
    }

    public void actualizarTratamiento(Tratamiento tratamiento) {
        tratamientoDAO.actualizarTratamiento(tratamiento);
    }

    public void eliminarTratamiento(String id) {
        tratamientoDAO.eliminarTratamiento(id);
    }

    public Tratamiento buscarPorId(String id) {
        return tratamientoDAO.buscarPorId(id);
    }

    public List<Tratamiento> listarTratamientosPorDiagnostico(String diagnosticoId) {
        return tratamientoDAO.listarTratamientosPorDiagnostico(diagnosticoId);
    }
}
