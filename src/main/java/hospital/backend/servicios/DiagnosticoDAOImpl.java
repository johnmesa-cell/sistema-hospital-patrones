package hospital.backend.servicios;

import hospital.backend.historialclinico.Diagnostico;
import java.util.*;

public class DiagnosticoDAOImpl implements DiagnosticoDAO {
    private Map<String, Diagnostico> diagnosticos = new HashMap<>();

    public void agregarDiagnostico(Diagnostico d) {
        diagnosticos.put(d.getIdDiagnostico(), d);
    }

    public void actualizarDiagnostico(Diagnostico d) {
        diagnosticos.put(d.getIdDiagnostico(), d);
    }

    public void eliminarDiagnostico(String id) {
        diagnosticos.remove(id);
    }

    public Diagnostico buscarPorId(String id) {
        return diagnosticos.get(id);
    }

    public List<Diagnostico> listarDiagnosticosPorPaciente(String pacienteId) {
        List<Diagnostico> resultado = new ArrayList<>();
        for (Diagnostico d : diagnosticos.values()) {
            if (d.getIdDiagnostico().equals(pacienteId)) {
                resultado.add(d);
            }
        }
        return resultado;
    }
}

