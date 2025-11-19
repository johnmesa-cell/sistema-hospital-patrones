package hospital.backend.servicios;

import hospital.backend.citas.Cita;
import java.util.List;

public interface CitaDAO {
    void agregarCita(Cita cita);
    void actualizarCita(Cita cita);
    void eliminarCita(String id);
    Cita buscarPorId(String id);
    List<Cita> listarCitasPorPaciente(String pacienteId);
}

