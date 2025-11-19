package hospital.backend.servicios;

import hospital.backend.citas.Cita;
import java.util.*;

public class CitaDAOImpl implements CitaDAO {
    private Map<String, Cita> citas = new HashMap<>();

    public void agregarCita(Cita c) {
        citas.put(c.getIdCita(), c);
    }

    public void actualizarCita(Cita c) {
        citas.put(c.getIdCita(), c);
    }

    public void eliminarCita(String id) {
        citas.remove(id);
    }

    public Cita buscarPorId(String id) {
        return citas.get(id);
    }

    public List<Cita> listarCitasPorPaciente(String pacienteId) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas.values()) {
            if (c.getIdCita().equals(pacienteId)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

