package hospital.frontend.servicios;

import hospital.backend.citas.Cita;
import hospital.backend.servicios.CitaDAO;

import java.util.List;

public class CitaService {
    private final CitaDAO citaDAO;

    public CitaService(CitaDAO citaDAO) {
        this.citaDAO = citaDAO;
    }

    public void agregarCita(Cita cita) {
        // Validación o lógica adicional antes de guardar una cita
        citaDAO.agregarCita(cita);
    }

    public void actualizarCita(Cita cita) {
        citaDAO.actualizarCita(cita);
    }

    public void eliminarCita(String id) {
        citaDAO.eliminarCita(id);
    }

    public Cita buscarPorId(String id) {
        return citaDAO.buscarPorId(id);
    }

    public List<Cita> listarCitasPorPaciente(String pacienteId) {
        return citaDAO.listarCitasPorPaciente(pacienteId);
    }
}

