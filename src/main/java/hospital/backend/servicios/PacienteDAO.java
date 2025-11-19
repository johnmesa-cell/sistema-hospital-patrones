package hospital.backend.servicios;

import hospital.backend.usuarios.Paciente;
import java.util.List;

public interface PacienteDAO {
    // Guarda un paciente en la base de datos
    void agregarPaciente(Paciente paciente);

    // Actualiza los datos de un paciente
    void actualizarPaciente(Paciente paciente);

    // Elimina un paciente por su ID
    void eliminarPaciente(String id);

    // Busca un paciente por su ID
    Paciente buscarPorId(String id);

    // Lista todos los pacientes registrados
    List<Paciente> listarPacientes();
}

