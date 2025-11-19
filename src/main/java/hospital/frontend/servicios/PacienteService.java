package hospital.frontend.servicios;

import hospital.backend.usuarios.Paciente;
import hospital.backend.servicios.PacienteDAO;

import java.util.List;

public class PacienteService {
    private final PacienteDAO pacienteDAO;

    public PacienteService(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public void agregarPaciente(Paciente paciente) {
        // Aquí puedes poner validaciones antes de agregar
        pacienteDAO.agregarPaciente(paciente);
    }

    public void actualizarPaciente(Paciente paciente) {
        pacienteDAO.actualizarPaciente(paciente);
    }

    public void eliminarPaciente(String id) {
        pacienteDAO.eliminarPaciente(id);
    }

    public Paciente buscarPorId(String id) {
        return pacienteDAO.buscarPorId(id);
    }

    public List<Paciente> listarPacientes() {
        return pacienteDAO.listarPacientes();
    }
}

