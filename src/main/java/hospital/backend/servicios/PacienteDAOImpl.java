package hospital.backend.servicios;

import hospital.backend.usuarios.Paciente;
import java.util.*;

public class PacienteDAOImpl implements PacienteDAO {
    private Map<String, Paciente> pacientes = new HashMap<>();

    public void agregarPaciente(Paciente paciente) {
        pacientes.put(paciente.getIdPaciente(), paciente);
    }

    public void actualizarPaciente(Paciente paciente) {
        pacientes.put(paciente.getIdPaciente(), paciente);
    }

    public void eliminarPaciente(String id) {
        pacientes.remove(id);
    }

    public Paciente buscarPorId(String id) {
        return pacientes.get(id);
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes.values());
    }
}

