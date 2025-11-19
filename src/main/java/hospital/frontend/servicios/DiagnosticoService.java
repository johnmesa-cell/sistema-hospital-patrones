package hospital.frontend.servicios;

import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.servicios.DiagnosticoDAO;

import java.util.List;

public class DiagnosticoService {
    private final DiagnosticoDAO diagnosticoDAO;

    public DiagnosticoService(DiagnosticoDAO diagnosticoDAO) {
        this.diagnosticoDAO = diagnosticoDAO;
    }

    public void agregarDiagnostico(Diagnostico diagnostico) {
        // Aquí puedes agregar validaciones o lógica adicional antes de guardar
        diagnosticoDAO.agregarDiagnostico(diagnostico);
    }

    public void actualizarDiagnostico(Diagnostico diagnostico) {
        diagnosticoDAO.actualizarDiagnostico(diagnostico);
    }

    public void eliminarDiagnostico(String id) {
        diagnosticoDAO.eliminarDiagnostico(id);
    }

    public Diagnostico buscarPorId(String id) {
        return diagnosticoDAO.buscarPorId(id);
    }

    public List<Diagnostico> listarDiagnosticosPorPaciente(String pacienteId) {
        return diagnosticoDAO.listarDiagnosticosPorPaciente(pacienteId);
    }
}

