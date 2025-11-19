package hospital.backend.servicios;

import hospital.backend.historialclinico.Tratamiento;
import java.util.*;

public class TratamientoDAOImpl implements TratamientoDAO {
    private Map<String, Tratamiento> tratamientos = new HashMap<>();

    public void agregarTratamiento(Tratamiento t) {
        tratamientos.put(t.getIdTratamiento(), t);
    }

    public void actualizarTratamiento(Tratamiento t) {
        tratamientos.put(t.getIdTratamiento(), t);
    }

    public void eliminarTratamiento(String id) {
        tratamientos.remove(id);
    }

    public Tratamiento buscarPorId(String id) {
        return tratamientos.get(id);
    }

    public List<Tratamiento> listarTratamientosPorDiagnostico(String diagnosticoId) {
        List<Tratamiento> resultado = new ArrayList<>();
        for (Tratamiento t : tratamientos.values()) {
            if (t.getIdTratamiento().equals(diagnosticoId)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}

